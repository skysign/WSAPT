import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] strs;

    int N, M;
    int[][] map;

    // Rotate by clockwise, up, rt, dn, lt
    public int[] d4i = new int[]{-1, 0, 1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    class CCTV {
        public int type;
        public int x, y;

        public CCTV(int type, int y, int x) {
            this.type = type;
            this.y = y;
            this.x = x;
        }

        public int getMaxDirs() {
            int r = 0;

            switch (this.type) {
                case 1:
                    r = 4;
                    break;
                case 2:
                    r = 2;
                    break;
                case 3:
                    r = 4;
                    break;
                case 4:
                    r = 4;
                    break;
                case 5:
                    r = 1;
                    break;
                default:
                    r = 0;
                    break;
            }

            return r;
        }
    }

    ArrayList<CCTV> alCCTV = new ArrayList<>();

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        map = new int[N + 2][M + 2];
        fill2D(map, 6);

        for (int i = 1; i <= N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 1; j <= M; ++j) {
                int t = Integer.parseInt(strs[j - 1]);

                if (t > 0) {
                    if (t == 6) {
                        map[i][j] = 6;
                    } else {
                        alCCTV.add(new CCTV(t, i, j));
                        map[i][j] = 0;
                    }
                } else {
                    map[i][j] = 0;
                }
            }
        }

        int r = rec(0, map);

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public int rec(int depth, int[][] map) {
        if (depth >= alCCTV.size()) {
            return countWatchedArea(map);
        }

        int r = Integer.MAX_VALUE;
        CCTV cctv = alCCTV.get(depth);

        for (int dir = 0; dir < cctv.getMaxDirs(); ++dir) {
            int[][] mymap = clone2D(map);
            watchCCTV(cctv, dir, mymap);
            int t = rec(depth + 1, mymap);
            r = Math.min(r, t);
        }

        return r;
    }

    public int countWatchedArea(int[][] map) {
        int r = 0;

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {
                if (map[i][j] == 1)
                    ++r;

                if (map[i][j] == 6)
                    ++r;
            }
        }

        return (N*M) - r;
    }

    public void watchCCTV(CCTV cctv, int dir, int[][] map) {
        int[] yxs = deltaCCTVbyidx(cctv, dir);
        int cnt = yxs.length / 2;

        for (int idx = 0; idx < cnt; ++idx) {
            int dy = yxs[idx * 2];
            int dx = yxs[idx * 2 + 1];
            watchCCTVoneline(cctv.y, cctv.x, dy, dx, map);
        }
    }

    public void watchCCTVoneline(int sy, int sx, int dy, int dx, int[][] map) {
        int y = sy;
        int x = sx;

        while (map[y][x] != 6) {
            map[y][x] = 1;
            y = y + dy;
            x = x + dx;
        }
    }

    public int[] deltaCCTVbyidx(CCTV cctv, int idx) {
        if (cctv.type == 1) {
            return deltaCCTV1(cctv, idx);
        } else if (cctv.type == 2) {
            return deltaCCTV2(cctv, idx);
        } else if (cctv.type == 3) {
            return deltaCCTV3(cctv, idx);
        } else if (cctv.type == 4) {
            return deltaCCTV4(cctv, idx);
        } else if (cctv.type == 5) {
            return deltaCCTV5(cctv, idx);
        }

        return null;
    }

    public int[] deltaCCTV1(CCTV cctv, int idx) {
        return new int[]{d4i[idx], d4j[idx]};
    }

    public int[] deltaCCTV2(CCTV cctv, int idx) {
        if (idx == 0) {
            return new int[]{d4i[0], d4j[0], d4i[2], d4j[2]};
        }

        return new int[]{d4i[1], d4j[1], d4i[3], d4j[3]};
    }

    public int[] deltaCCTV3(CCTV cctv, int idx) {
        if (idx == 0) {
            return new int[]{d4i[0], d4j[0], d4i[1], d4j[1]};
        } else if (idx == 1) {
            return new int[]{d4i[1], d4j[1], d4i[2], d4j[2]};
        } else if (idx == 2) {
            return new int[]{d4i[2], d4j[2], d4i[3], d4j[3]};
        }

        return new int[]{d4i[0], d4j[0], d4i[3], d4j[3]};
    }

    public int[] deltaCCTV4(CCTV cctv, int idx) {
        if (idx == 0) {
            return new int[]{d4i[1], d4j[1], d4i[2], d4j[2], d4i[3], d4j[3]};
        } else if (idx == 1) {
            return new int[]{d4i[0], d4j[0], d4i[2], d4j[2], d4i[3], d4j[3]};
        } else if (idx == 2) {
            return new int[]{d4i[0], d4j[0], d4i[1], d4j[1], d4i[3], d4j[3]};
        }

        return new int[]{d4i[0], d4j[0], d4i[1], d4j[1], d4i[2], d4j[2]};
    }

    public int[] deltaCCTV5(CCTV cctv, int idx) {
        return new int[]{d4i[0], d4j[0], d4i[1], d4j[1], d4i[2], d4j[2], d4i[3], d4j[3]};
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public int[][] clone2D(int[][] src) {
        int row = src.length;
        int col = src[0].length;
        int[][] dst = new int[row][col];

        for (int i = 0; i < row; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, col);
        }

        return dst;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}