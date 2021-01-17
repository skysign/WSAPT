import java.io.*;
import java.util.*;
/**
 * BOJ 10265번 MT
 *
 * 유튜브 문제 풀이 : https://youtu.be/DBgcB9Se5S4
 *
 * 문제링크 : https://www.acmicpc.net/problem/10265
 *
 * 자바소스 : https://bit.ly/2LDGA5N
 */
public class Main {
    BufferedReader br;
    BufferedWriter bw;

    int N, K;
    int[] edge;
    boolean[] visited;
    ArrayList[] reverseEdge;
    int[] root;

    int[][] dp;

    ArrayList<ArrayList<Integer>> allSCC;
    ArrayList<HashSet<Integer>> allPouch;

    public void solve(InputStream in, PrintStream out) throws IOException {
        br = new BufferedReader(new InputStreamReader(in));
        bw = new BufferedWriter(new OutputStreamWriter(out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edge = new int[N+1];
        visited = new boolean[N+1];
        reverseEdge = new ArrayList[N+1];
        root = new int[N+1];
        allSCC = new ArrayList();

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
        Stack<Integer> stk = new Stack();

        for (int n = 1; n <=N; ++n) {
            if (!visited[n]) {
                SCC_Tarjan(n, stk);
            }
        }

        for (ArrayList<Integer> scc: allSCC) {
            HashSet<Integer> pouch = new HashSet();
            allPouch.add(pouch);
            pouch.add(scc.size());

            ArrayDeque<Integer> que = new ArrayDeque<>();
            que.addAll(scc);
            int length = scc.size();

            while (que.size() > 0) {
                int v = que.pop();
                if (null == reverseEdge[v])
                    continue;

                for (Object obj: reverseEdge[v]) {
                    Integer toV = (Integer)obj;

                    if (root[toV] == 0) {
                        root[toV] = v;
                        length++;
                        que.add(toV);
                        pouch.add(length);
                    }
                }
            }
        }

        dp = new int[allPouch.size()][K +1];
        fill2D(dp, -1);

        return knapsack(0, K, allPouch);
    }

    void SCC_Tarjan(int v, Stack<Integer> stk) {
        if (visited[v]) {
            if ((0 == root[v]) && (0 == root[edge[v]])) {
                ArrayList<Integer> scc = new ArrayList();
                allSCC.add(scc);
                scc.add(v);

                // I'm root
                root[v] = v;

                // SCC is found
                while ((v != stk.peek()) && (stk.size() > 0)) {
                    int vInSCC = stk.pop();
                    root[vInSCC] = v;
                    scc.add(vInSCC);
                }

                /// remove v itself in stk
                stk.pop();
            }

            return;
        }

        visited[v] = true;
        stk.push(v);

        SCC_Tarjan(edge[v], stk);
    }

    int knapsack(int idxPouch, int k, ArrayList<HashSet<Integer>> pouches) {
        if (idxPouch >= pouches.size())
            return 0;

        if (k <= 0)
            return 0;

        if (dp[idxPouch][k] != -1)
            return dp[idxPouch][k];

        int r1 = knapsack(idxPouch +1, k, pouches);

        int r2 = -1;
        HashSet<Integer> pouch = pouches.get(idxPouch);
        Integer[] balls = pouch.toArray(new Integer[0]);
        Arrays.sort(balls);

        for (int ball: balls) {
            if (ball <= k) {
                r2 = Math.max(r2, knapsack(idxPouch +1, k -ball, pouches) +ball);
            }
            else {
                break;
            }
        }

        dp[idxPouch][k] = Math.max(r1, r2);

        return dp[idxPouch][k];
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve(System.in, System.out);
    }
}