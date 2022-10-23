import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[][] map;
    int[][] mapPath;

    static int ap = 100 - (1 + 2 + 7 + 10) * 2 - 5;

    static int LT = 0;
    static int DN = 1;
    static int RT = 2;
    static int UP = 3;
    // ← ↓ → ↑
    // LT DN RT UP
    // 0  1  2  3
    int[][] dRC = new int[][]{
            {0, -1},
            {1, 0},
            {0, 1},
            {-1, 0}
    };

    int[] dTurnLeft = new int[]{DN, RT, UP, LT};

    int[][] WindLT = {
            {0, 0, 2, 0, 0},
            {0, 10, 7, 1, 0},
            {5, -1, 0, 0, 0},
            {0, 10, 7, 1, 0},
            {0, 0, 2, 0, 0}
    };

    int srcR, srcC;
    String[] strs;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + (2 * 2)][N + (2 * 2)];
        mapPath = new int[N + (2 * 2)][N + (2 * 2)];
        fill2D(mapPath, -1);

        for (int i = 0; i < N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                int v = Integer.parseInt(strs[j]);
                map[2 + i][2 + j] = v;
            }
        }

        int[][][] mapWinds = new int[4][][];

        mapWinds[LT] = WindLT;

        int[][] WindUP = rotateMatrixBy90(WindLT);
        mapWinds[UP] = WindUP;

        int[][] WindRT = rotateMatrixBy90(WindUP);
        mapWinds[RT] = WindRT;

        int[][] WindDN = rotateMatrixBy90(WindRT);
        mapWinds[DN] = WindDN;

        int dstR = 0;
        int dstC = 0;

        int dir = LT;

        srcR = N / 2 + 2;
        srcC = N / 2 + 2;
        mapPath[srcR][srcC] = 0;

        srcR = N / 2 + 2;
        srcC = N / 2 + 2;

        for (int idx = 1; (2 != dstR) || (2 != dstC); ++idx) {
            dstR = srcR + dRC[dir][0];
            dstC = srcC + dRC[dir][1];

            mapPath[dstR][dstC] = idx;

            int topLeftR = dstR - 2;
            int topLeftC = dstC - 2;

            int[][] mapWind = mapWinds[dir];
            int oriSand = map[dstR][dstC];

            if (oriSand != 0) {
                int movedSand = 0;

                int rowAlpha = 0, colAlpha = 0;

                for (int row = 0; row < 5; ++row) {
                    for (int col = 0; col < 5; ++col) {
                        if (-1 == mapWind[row][col]) {
                            rowAlpha = row;
                            colAlpha = col;
                        } else if (0 != mapWind[row][col]) {
                            int tmp = (oriSand * mapWind[row][col] / 100);
                            map[topLeftR + row][topLeftC + col] += tmp;
                            movedSand += tmp;
                        }
                    }
                }

                map[dstR][dstC] = 0;
                map[topLeftR + rowAlpha][topLeftC + colAlpha] += (oriSand - movedSand);
            }

            srcR = dstR;
            srcC = dstC;

            int turnLeftR = srcR + dRC[dTurnLeft[dir]][0];
            int turnLeftC = srcC + dRC[dTurnLeft[dir]][1];

            if (mapPath[turnLeftR][turnLeftC] == -1) {
                dir = dTurnLeft[dir];
            }
        }

        int ans = 0;

        for (int col = 0; col < N + (2 * 2); ++col) {
            ans += map[0][col];
            ans += map[1][col];

            ans += map[N + (2 * 2) - 2][col];
            ans += map[N + (2 * 2) - 1][col];
        }

        for (int row = 2; row < N + (2 * 2) - 2; ++row) {
            ans += map[row][0];
            ans += map[row][1];
            ans += map[row][N + (2 * 2) - 2];
            ans += map[row][N + (2 * 2) - 1];
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    // 배열 시계방향 90도 회전
    public int[][] rotateMatrixBy90(int[][] src2D) {
        int[][] dst2D = new int[src2D[0].length][src2D.length];

        for (int i = 0; i < src2D.length; i++)
            for (int j = 0; j < src2D[0].length; j++)
                dst2D[i][j] = src2D[src2D[0].length - j - 1][i];

        return dst2D;
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}