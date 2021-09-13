import java.io.*;

public class Main {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int HOR = 0;
    static int VER = 1;
    static int DIA = 2;

    int N;
    String[] strs;
    int[][] map;
    int[][][] dp;   // [0]row, [1]column, [2](horizontal, vertical, diagonal)

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1][3];

        for (int i = 1; i <= N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                map[i][j + 1] = Integer.parseInt(strs[j]);
            }
        }

        for (int j = 0; j < N + 1; ++j) {
            map[0][j] = 1;
        }

        for (int i = 0; i < N + 1; ++i) {
            map[i][0] = 1;
        }

        dp[1][2][0] = 1;

        int[] dis = {0, -1, -1};
        int[] djs = {-1, 0, -1};

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (map[i][j] == 1)
                    continue;

                for (int hvd = 0; hvd < 3; ++hvd) {
                    int di = dis[hvd];
                    int dj = djs[hvd];

                    int fri = i + di;
                    int frj = j + dj;

                    if (map[fri][frj] == 1)
                        continue;

                    if (hvd == HOR) {
                        dp[i][j][HOR] += dp[fri][frj][HOR];
                        dp[i][j][HOR] += dp[fri][frj][DIA];
                    }
                    else if (hvd == VER) {
                        dp[i][j][VER] += dp[fri][frj][VER];
                        dp[i][j][VER] += dp[fri][frj][DIA];
                    }
                    else {  // DIA
                        if (map[i][j-1] == 1)
                            continue;
                        if (map[i-1][j] == 1)
                            continue;

                        dp[i][j][DIA] += dp[fri][frj][DIA];
                        dp[i][j][DIA] += dp[fri][frj][HOR];
                        dp[i][j][DIA] += dp[fri][frj][VER];
                    }
                }
            }
        }

        int r = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }


}