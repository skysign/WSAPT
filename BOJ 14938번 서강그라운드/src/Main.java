import java.io.*;
/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 14938번 서강그라운드
 *
 * 유튜브 문제 풀이: https://youtu.be/0_aRf1y3q-c
 *
 * 문제링크: https://www.acmicpc.net/problem/14938
 *
 * 자바소스: https://bit.ly/3fU64as
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, R;
    String[] strs;
    int[][] map;
    int[]   item;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        R = Integer.parseInt(strs[2]);

        map = new int[N+1][N+1];
        item = new int[N+1];

        strs = br.readLine().split(" ");

        for (int i=0; i<N; ++i) {
            item[i+1] = Integer.parseInt(strs[i]);
        }

        for (int i=0; i<R; ++i) {
            strs = br.readLine().split(" ");
            int fr = Integer.parseInt(strs[0]);
            int to = Integer.parseInt(strs[1]);
            int v  = Integer.parseInt(strs[2]);

            map[fr][to] = v;
            map[to][fr] = v;
        }

        for (int mid=1; mid<N+1; ++mid) {
            for (int fr=1; fr<N+1; ++fr) {
                for (int to=1; to<N+1; ++to) {
                    if ((fr == mid) || (mid == to) || (fr == to))
                        continue;

                    if ((0 == map[fr][mid]) || (0 == map[mid][to]))
                        continue;

                    if (0 == map[fr][to])
                        map[fr][to] = map[fr][mid] + map[mid][to];
                    else
                        map[fr][to] = Math.min(map[fr][to], map[fr][mid] + map[mid][to]);
                }
            }
        }

        int maxItems = 0;

        for (int fr=1; fr<N+1; ++fr) {
            int sumOfItems = item[fr];

            for (int to=1; to<N+1; ++to) {
                if ((map[fr][to] != 0) && (map[fr][to] <= M))
                    sumOfItems += item[to];
            }

            maxItems = Math.max(maxItems, sumOfItems);
        }

        bw.write(String.valueOf(maxItems));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}