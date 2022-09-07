import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;
    int[][] map;
    int[][] map2;
    int ans;

    public void solve() throws IOException {
        ans = Integer.MAX_VALUE;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        map2 = new int[N + 1][N + 1];

        for (int i = 1; i <= N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 1; j <= N; ++j) {
                int idx = j - 1;
                map[i][j] = Integer.parseInt(strs[idx]);
            }
        }

        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                for (int d1 = 1; d1 <= N; ++d1) {
                    for (int d2 = 1; d2 <= N; ++d2) {
                        if (r + d1 + d2 <= N) {
                            if ((1 <= c - d1) && (c + d2 <= N)) {
                                int t = divideSeonGeoGu(r, c, d1, d2);
                                ans = Math.min(ans, t);
                            }
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public int divideSeonGeoGu(int sr, int sc, int d1, int d2) {
        int[] population = new int[5 + 1];

        // 모두 5번 선거구로 초기화
        fill2D(map2, 0);

        for (int idx = 0; idx <= N; ++idx) {
            map2[0][idx] = -1;
            map2[idx][0] = -1;
        }

        // 시작점 5번으로 색칠
        map2[sr][sc] = 5;

        // 1번 선
        // 4번 선 선의 길이 d1
        for (int l = 1; l <= d1; ++l) {
            int dr = 1;
            int dc = -1;

            int r = sr + (l * dr);
            int c = sc + (l * dc);

            map2[r][c] = 5;

            r = sr + d2 + (l * dr);
            c = sc + d2 + (l * dc);

            map2[r][c] = 5;
        }

        // 2번 선
        // 3번 선 선의 길이는 d2
        for (int l = 1; l <= d2; ++l) {
            int dy = 1;
            int dx = 1;

            int r = sr + (l * dy);
            int c = sc + (l * dx);

            map2[r][c] = 5;

            r = sr + d1 + (l * dy);
            c = sc - d1 + (l * dx);

            map2[r][c] = 5;
        }

        // 1번 선거구
        for (int r = 1; r < sr + d1; ++r) {
            for (int c = 1; c <= sc; ++c) {
                if (map2[r][c] == 5) {
                    break;
                }

                if (r >= (-1) * c + (sr + sc)) {
                    continue;
                }

                map2[r][c] = 1;
            }
        }

        // 2번 선거구
        for (int r = 1; r <= sr + d2; ++r) {
            for (int c = sc + 1; c <= N; ++c) {
                if (map2[r][c] == 5) {
                    continue;
                }

                if (r >= c + (sr - sc)) {
                    continue;
                }

                map2[r][c] = 2;
            }
        }

        // 3번 선거구
        for (int r = sr + d1; r <= N; ++r) {
            for (int c = 1; c < sc - d1 + d2; ++c) {
                if (map2[r][c] == 5) {
                    break;
                }

                if (r <= c + ((sr + d1) - (sc + d1))) {
                    continue;
                }

                map2[r][c] = 3;
            }
        }

        // 4번 선거구
        for (int r = sr + d2 + 1; r <= N; ++r) {
            for (int c = sc - d1 + d2; c <= N; ++c) {
                if (map2[r][c] == 5) {
                    continue;
                }

                if (r <= (-1) * c + ((sr + d2) + (sc + d2))) {
                    continue;
                }

                map2[r][c] = 4;
            }
        }

        for (int r = 1; r <=N; ++r) {
            for (int c = 1; c<=N; ++c) {
                int idx = map2[r][c];

                if (idx == 0)
                    idx = 5;

                population[idx] += map[r][c];
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int idx = 1; idx <= 5; ++idx) {
            min = Math.min(min, population[idx]);
            max = Math.max(max, population[idx]);
        }

        return (max - min);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }
}