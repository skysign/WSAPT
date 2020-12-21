import java.io.*;
import java.util.*;
/**
 * BOJ 1707번 이분 그래프
 *
 * 유튜브 문제 풀이
 * https://youtu.be/5UxWEw6CTxQ
 *
 * 문제링크 : https://www.acmicpc.net/problem/1707
 *
 * 자바소스 : https://bit.ly/3mF7qXE
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    enum status {
        NOT_VISITED, RED, BLACK
    };

    int TCs, N, Edges;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        TCs = Integer.parseInt(st.nextToken());

        for (int i = 0; i < TCs; ++i) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Edges = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> al = new ArrayList<>();
            // Vertex번호와 al의 index를 맞추기 위해서, 0 번을 넣어 주고,
            al.add(new ArrayList<Integer>());

            // 1부터 N(inclusive)까지 넣기
            for (int v = 1; v <= N; ++v)
                al.add(new ArrayList<Integer>());

            // Edge 개수 만큼 looping
            for (int e = 0; e < Edges; ++e) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                // al 에 add 하는 것으로, edge를 저장함
                al.get(v1).add(v2);
                al.get(v2).add(v1);
            }

            status[] ss = new status[N +1];
            Arrays.fill(ss, status.NOT_VISITED);
            ss[1] = status.BLACK;
            String r = "YES";

            for (int idx = 1; idx <= N; ++idx) {
                r = solve2(idx, al, ss);

                if (r.equals("NO"))
                    break;
            }

            bw.write(r);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    String solve2(int vertex, ArrayList<ArrayList<Integer>> al, status[] ss) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(vertex);

        while (que.size() > 0) {
            vertex = que.pop();
            for (int adjVertex: al.get(vertex)) {
                if (ss[adjVertex] == status.NOT_VISITED) {
                    ss[adjVertex] = swithColor(ss[vertex]);
                    que.add(adjVertex);
                }
                else if (ss[adjVertex] == ss[vertex]) {
                    return "NO";
                }
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    status swithColor(status color) {
        if (color == status.BLACK)
            return status.RED;

        return status.BLACK;
    }
}