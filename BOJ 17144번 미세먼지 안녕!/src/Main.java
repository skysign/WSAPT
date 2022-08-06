import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int R, C, T;
    String[] strs;
    int[][] map;
    int[][] map2;
    int[][] airPuri;

    int[] d4i = new int[]{-1, 0, 1, 0};
    int[] d4j = new int[]{0, 1, 0, -1};

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        R = Integer.parseInt(strs[0]);
        C = Integer.parseInt(strs[1]);
        T = Integer.parseInt(strs[2]);

        map = new int[R][C];
        map2 = new int[R][C];
        airPuri = new int[2][2];

        for (int i = 0; i < R; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 0; j < C; ++j) {
                int v = Integer.parseInt(strs[j]);
                map[i][j] = v;

                if (v == -1) {
                    if (airPuri[0][0] == 0) {
                        airPuri[0][0] = i;
                        airPuri[0][1] = j;
                    } else {
                        airPuri[1][0] = i;
                        airPuri[1][1] = j;
                    }
                }
            }
        }



        for (int t = 0; t < T; ++t) {
            hwaksan();
            clean0();
            clean1();
        }

        int r = 0;
        for (int y = 0; y < R; ++y) {
            for (int x = 0; x < C; ++x) {
                r += map[y][x];
            }
        }

        r += 2;

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public void hwaksan() {
        fill2D(map2, 0);

        for (int y = 0; y < R; ++y) {
            for (int x = 0; x < C; ++x) {
                int remain = map[y][x];
                int hawk = map[y][x] / 5;

                for (int idx = 0; idx < d4i.length; ++idx) {
                    int dy = d4j[idx];
                    int dx = d4i[idx];

                    int ty = y + dy;
                    int tx = x + dx;

                    if ((0 <= ty) && (ty < R)) {
                        if ((0 <= tx) && (tx < C)) {
                            if (map[ty][tx] != -1) {
                                map2[ty][tx] += hawk;
                                remain -= hawk;
                            }
                        }
                    }
                }

                map[y][x] = remain;
            }
        }

        for (int y = 0; y < R; ++y) {
            for (int x = 0; x < C; ++x) {
                map[y][x] += map2[y][x];
            }
        }
    }

    void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void clean0() {
        int sy = airPuri[0][0];
        int sx = airPuri[0][1];

        /**
         *  위 -> 아래
         */
        for (int y = sy - 1; y >= 0; --y) {
            int ty = y + 1;
            int tx = 0;
            int x = 0;

            if (map[ty][tx] != -1) {
                map[ty][tx] = map[y][x];
            }
        }

        /**
         * 오른쪽 -> 왼쪽
         */
        for (int x = 1; x < C; ++x) {
            int ty = 0;
            int tx = x - 1;
            int y = 0;
            map[ty][tx] = map[y][x];
        }

        /**
         * 아래 -> 위로
         */
        for (int y = 1; y <= sy; ++y) {
            int ty = y - 1;
            int tx = C - 1;
            int x = C - 1;
            map[ty][tx] = map[y][x];
        }

        /**
         * 왼쪽 -> 오른쪽
         */
        for (int x = C - 2; x > 0; --x) {
            int ty = sy;
            int tx = x + 1;
            int y = sy;
            map[ty][tx] = map[y][x];
        }

        map[sy][sx + 1] = 0;
    }

    public void clean1() {
        int sy = airPuri[1][0];
        int sx = airPuri[1][1];

        /**
         * 아래 -> 위
         */
        for (int y = sy + 1; y < R; ++y) {
            int ty = y - 1;
            int tx = 0;
            int x = 0;

            if (map[ty][tx] != -1) {
                map[ty][tx] = map[y][x];
            }
        }

        /**
         * 오른쪽 -> 왼쪽
         */
        for (int x = 1; x < C; ++x) {
            int ty = R - 1;
            int tx = x - 1;
            int y = R - 1;
            map[ty][tx] = map[y][x];
        }

        /**
         * 위 -> 아래
         */
        for (int y = R - 2; y >= sy; --y) {
            int ty = y + 1;
            int tx = C - 1;
            int x = C - 1;
            map[ty][tx] = map[y][x];
        }

        /**
         * 왼쪽 -> 오른쪽
         */
        for (int x = C - 2; x > 0; --x) {
            int ty = sy;
            int tx = x + 1;
            int y = sy;
            map[ty][tx] = map[y][x];
        }

        map[sy][sx + 1] = 0;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}