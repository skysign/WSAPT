import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int a, b, c;
    int[][] map;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        fill2D(map, Integer.MAX_VALUE);

        for (int i=1; i<=M; ++i) {
            String[] strs = br.readLine().split(" ");

            a = Integer.parseInt(strs[0]);
            b = Integer.parseInt(strs[1]);
            c = Integer.parseInt(strs[2]);

            if (map[a][b] == Integer.MAX_VALUE)
                map[a][b] = c;
            else
                map[a][b] = Math.min(map[a][b], c);
        }

        for (int m=1; m<=N; ++m) {
            for (int fr=1; fr<=N; ++fr) {
                for (int to=1; to<=N; ++to) {
                    if ((map[fr][m] == Integer.MAX_VALUE) || (map[m][to] == Integer.MAX_VALUE))
                        continue;

                    if ((fr == m) || (m == to) || fr == to)
                        continue;

                    map[fr][to] = Math.min(map[fr][to], map[fr][m] + map[m][to]);
                }
            }
        }


        for (int i=1; i<=N; ++i) {
            for (int j=1; j<=N; ++j) {
                int r = (map[i][j] == Integer.MAX_VALUE)? 0: map[i][j];
                bw.write(String.valueOf(r) + " ");
            }
            bw.newLine();
        }

        bw.close();
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}