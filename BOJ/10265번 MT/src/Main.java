import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N, K;
    int[] dt;
    ArrayList<List<Integer>> SCC;
    ArrayList<Set<Integer>> allPouch;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dt = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; ++i) {
            dt[i] = Integer.parseInt(st.nextToken());
        }

        SCC = new ArrayList();
        allPouch = new ArrayList();

        int r = solve2();

        bw.write(Integer.toString(r));
        bw.write('\n');
        bw.flush();
        bw.close();
    }

    int solve2() {
        for (int n = 1; n <=N; ++n) {
            DFS_SCC(n, new ArrayList<Integer>());
        }

        return knapsack(0, K, allPouch);
    }

    void DFS_SCC(int v, ArrayList<Integer> visited) {
        int toV = dt[v];

        visited.add(v);

        // 같이 타고 싶은 사람이 있어요
        if (toV > 0) {
            // SCC 를 발견
            if (visited.contains(toV)) {
                int idxScc = -1;

                // 이전에 발견한 SCC 인가?
                for (int idx = 0; idx < SCC.size(); ++idx) {
                    List<Integer> sccOrEnd0 = SCC.get(idx);

                    if (sccOrEnd0.contains(dt[v])) {
                        idxScc = idx;
                        break;
                    }
                }

                // 아니면, 처음 발견한 SCC 인가?
                if (idxScc == -1) {
                    // scc 리스트에, 처음 발견한 scc를 넣고
                    List<Integer> scc = visited.subList(visited.indexOf(toV), visited.size());
                    SCC.add(scc);
                    // 해당 scc에 연결된 path의 길이를 저장함
                    HashSet<Integer> pouch = new HashSet();
                    pouch.add(visited.size());
                    allPouch.add(pouch);
                }
                else {
                    // 기존에 발견했던 SCC
                    // 길이를 추가함
                    allPouch.get(idxScc).add(visited.size());
                }
            }
            else {
                DFS_SCC(toV, visited);
            }
        }
//        else {
//            // 0 으로 끝나는 경우, 없음
//            ArrayList<Integer> end0 = new ArrayList<>();
//            end0.add(toV);
//            SCC.add(end0);
//            HashSet<Integer> pouch = new HashSet();
//            pouch.add(visited.size());
//            allPouch.add(pouch);
//        }
    }

    int knapsack(int idxPouch, int k, ArrayList<Set<Integer>> pouches) {
        if (idxPouch >= pouches.size())
            return 0;

        if (k <= 0)
            return 0;

        // idxPouch에서 공을 꺼내지 않고, 최대값
        int r1 = knapsack(idxPouch +1, k, pouches);

        int r2 = 0;
        Set<Integer> pouch = pouches.get(idxPouch);
        for (int ball: pouch) {
            if (ball <= k) {
                r2 = Math.max(r2, knapsack(idxPouch +1, k -ball, pouches) +ball);
            }
        }

        return Math.max(r1, r2);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}