import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int[][] map;
    int[][] map2;
    String[] strs;

    // ←, ↖,↑,↗,→,↘,↓,↙,
    // 1  2  3  4   5  6  7 8
    int[][] dClouds = {
            {},
            {0, -1},    // 1번 LT
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1}, // 5번 RT
            {1, 1},
            {1, 0},
            {1, -1}
    };

    //  ↖,↗,↘,↙,
    int[][] dBugs = {
            {-1, -1},
            {-1, 1},
            {1, 1},
            {1, -1}
    };

    ArrayList<int[]> alDirSpd;
    ArrayList<int[]> alClouds;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        map = new int[N + 1][N + 1];
        map2 = new int[N + 1][N + 1];

        alDirSpd = new ArrayList<>();
        alClouds = new ArrayList<>();

        alClouds.add(new int[]{N, 1});
        alClouds.add(new int[]{N, 2});
        alClouds.add(new int[]{N - 1, 1});
        alClouds.add(new int[]{N - 1, 2});

        for (int r = 1; r <= N; ++r) {
            strs = br.readLine().split(" ");

            for (int c = 1; c <= N; ++c) {
                map[r][c] = Integer.parseInt(strs[c - 1]);
            }
        }

        for (int m = 0; m < M; ++m) {
            strs = br.readLine().split(" ");
            int dir = Integer.parseInt(strs[0]);
            int spd = Integer.parseInt(strs[1]);

            alDirSpd.add(new int[]{dir, spd});
        }

        for (int[] dirSpd : alDirSpd) {
            int dir = dirSpd[0];
            int spd = dirSpd[1];

            for (int[] cloud : alClouds) {
                int r = cloud[0];
                int c = cloud[1];

                int dstR = r + (dClouds[dir][0] * spd);
                int dstC = c + (dClouds[dir][1] * spd);

                dstR = calRC(dstR);
                dstC = calRC(dstC);

                ++map[dstR][dstC];
                cloud[0] = dstR;
                cloud[1] = dstC;
                map2[dstR][dstC] = 1;
            }

            for (int[] cloud : alClouds) {
                int r = cloud[0];
                int c = cloud[1];

                int cnt = 0;

                for (int[] dbug : dBugs) {
                    int dr = dbug[0];
                    int dc = dbug[1];

                    int dstR = r + dr;
                    int dstC = c + dc;

                    if ((1 <= dstR) && (dstR <= N)
                            && (1 <= dstC) && (dstC <= N)) {
                        if (map[dstR][dstC] > 0)
                            ++cnt;
                    }
                }

                map[r][c] += cnt;
            }

            alClouds.clear();

            for (int r = 1; r <= N; ++r) {
                for (int c = 1; c <= N; ++c) {
                    if (map2[r][c] == 0) {
                        if (map[r][c] >= 2) {
                            map[r][c] -= 2;
                            alClouds.add(new int[]{r, c});
                        }
                    }
                    else { // map2[r][c] == 1
                        map2[r][c] = 0;
                    }
                }
            }
        }

        int ans = 0;

        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                ans += map[r][c];
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    int calRC(int v) {
        int dN = N;

        if (v > 0) {
            dN = N * -1;
        }

        while (!((1 <= v) && (v <= N))) {
            v += dN;
        }

        if (v == 0)
            v = N;

        return v;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }
}