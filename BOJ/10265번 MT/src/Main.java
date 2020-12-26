import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N, K;
    int[] edge;
    ArrayList[] reverseEdge;
    int[] root;

    ArrayList<HashSet<Integer>> allPouch;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edge = new int[N+1];
        reverseEdge = new ArrayList[N+1];
        root = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; ++i) {
            // from i, to edge[i]
            edge[i] = Integer.parseInt(st.nextToken());

            if (reverseEdge[edge[i]] == null)
                reverseEdge[edge[i]] = new ArrayList<Integer>();

            reverseEdge[edge[i]].add(i);
        }

        allPouch = new ArrayList();

        int r = solve2();

        bw.write(Integer.toString(r));
        bw.write('\n');
        bw.flush();
        bw.close();
    }

    int solve2() {
        for (int n = 1; n <=N; ++n) {
            if (edge[n] != 0) {
                DFS_SCC(n, new ArrayList<Integer>());
            }
        }

        return knapsack(0, K, allPouch);
    }

    void DFS_reverse(int toV, int depth, HashSet<Integer> pouch) {
        pouch.add(depth +1);
        edge[toV] = 0;

        if (reverseEdge[toV] != null) {
            for (Object obj: reverseEdge[toV]) {
                Integer reverseToV = (Integer)obj;
                DFS_reverse(reverseToV, depth +1, pouch);
            }
        }
    }

    void DFS_SCC(int v, ArrayList<Integer> visitedVertex) {
        visitedVertex.add(v);

        int toV = edge[v];

        if (toV == 0) {
            return;
        }

        // SCC를 찾았다
        // 따라서, toV는 visitedVertex 에 반드시 존재한다.
        if (visitedVertex.contains(toV)) {
            // scc 에는 scc 구성하는 vertex가 모두 들어 있음
            List<Integer> scc = visitedVertex.subList(visitedVertex.indexOf(toV), visitedVertex.size());

            for (int vInSCC: scc) {
                root[vInSCC] = toV;
                edge[vInSCC] = 0;
            }

            int lengthOfSCC = scc.size();

            HashSet<Integer> pouch = new HashSet();
            pouch.add(lengthOfSCC);
            allPouch.add(pouch);
            int sccRoot = toV;

            for (int vInSCC: scc) {
                if (reverseEdge[vInSCC] != null) {
                    for (Object obj: reverseEdge[vInSCC]) {
                        Integer reverseToV = (Integer)obj;

                        // scc를 구성하고 있는 vertex사이에 존재하는 edge가 아니라,
                        // scc 밖으로 향하는 reverseEdge일 때만
                        if (root[vInSCC] != root[reverseToV]) {
                            DFS_reverse(reverseToV, lengthOfSCC, pouch);
                        }
                    }
                }
            }
        }
        else {
            DFS_SCC(toV, visitedVertex);
        }
    }

    int knapsack(int idxPouch, int k, ArrayList<HashSet<Integer>> pouches) {
        if (idxPouch >= pouches.size())
            return 0;

        if (k <= 0)
            return 0;

        // idxPouch에서 공을 꺼내지 않고, 최대값
        int r1 = knapsack(idxPouch +1, k, pouches);

        int r2 = 0;
        HashSet<Integer> pouch = pouches.get(idxPouch);
        Integer[] balls = pouch.toArray(new Integer[0]);
        Arrays.sort(balls);

        for (int ball: pouch) {
            if (ball <= k) {
                r2 = Math.max(r2, knapsack(idxPouch +1, k -ball, pouches) +ball);
            }
            else {
                break;
            }
        }

        return Math.max(r1, r2);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}