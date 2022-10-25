import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int _n;
    int N, Q;

    int[][] map;
    int[][] mapRotate;
    int[][] mapDelta;
    int[][] mapVisited;

    // UP -1, 0
    // RT 0, 1
    // DN 1. 0
    // LT 0, -1
    int[][] dRC = new int[][]{
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    int[] qs;

    String[] strs;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        _n = Integer.parseInt(strs[0]);
        N = myPow(2, _n);
        Q = Integer.parseInt(strs[1]);

        map = new int[N + (1 * 2)][N + (1 * 2)];
        mapRotate = new int[N + (1 * 2)][N + (1 * 2)];
        mapDelta = new int[N + (1 * 2)][N + (1 * 2)];
        mapVisited = new int[N + (1 * 2)][N + (1 * 2)];

        for (int r = 1; r <= N; ++r) {
            strs = br.readLine().split(" ");

            for (int c = 1; c <= N; ++c) {
                int v = Integer.parseInt(strs[c - 1]);
                map[r][c] = v;
            }
        }

        strs = br.readLine().split(" ");
        qs = new int[strs.length];

        for (int i = 0; i < strs.length; ++i) {
            qs[i] = Integer.parseInt(strs[i]);
        }

        for (int i = 0; i < Q; ++i) {
            int L = qs[i];

            for (int l = 1; l <=L; ++l) {
                int q = myPow(2, l);

                // 2L × 2L 크기의 부분 격자로 나눈다. 그 후, 모든 부분 격자를 시계 방향으로 90도 회전시킨다.
                for (int r = 1; r < N; r += q) {
                    for (int c = 1; c < N; c += q) {
                        rotate(r, c, q);
                    }
                }

                int[][] mapTmp = map;
                map = mapRotate;
                mapRotate = mapTmp;
            }

            // 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.
            fill2D(mapDelta, 0);

            for (int r = 1; r <= N; ++r) {
                for (int c = 1; c <= N; ++c) {
                    // UP -1, 0
                    // RT 0, 1
                    // DN 1. 0
                    // LT 0, -1
                    int cnt = 0;
                    for (int[] drc : dRC) {
                        int dstR = r + drc[0];
                        int dstC = c + drc[1];

                        if (map[dstR][dstC] == 0) {
                            ++cnt;
                        }
                    }

                    if (cnt > 1) {
                        mapDelta[r][c] += -1;
                    }
                }
            }

            for (int r = 1; r <= N; ++r) {
                for (int c = 1; c <= N; ++c) {
                    if (map[r][c] > 0) {
                        map[r][c] += mapDelta[r][c];
                    }
                }
            }
        }

        int ans1 = 0;

        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                ans1 += map[r][c];
            }
        }

        bw.write(String.valueOf(ans1));
        bw.newLine();

        int ans2 = 0;
        int group = 1;

        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                // 얼음이 있고
                if (map[r][c] != 0) {
                    if (mapVisited[r][c] == 0) {
                        int v = bfs(r, c, group);
                        ans2 = Math.max(ans2, v);
                    }
                }
            }
        }

        bw.write(String.valueOf(ans2));
        bw.newLine();

        bw.close();
    }

    public int bfs(int sr, int sc, int group) {
        int cnt = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] rc = queue.pollFirst();
            int r = rc[0];
            int c = rc[1];

            if ((mapVisited[r][c] == 0) && (map[r][c] != 0)) {
                mapVisited[r][c] = group;
                ++cnt;

                for (int[] drc : dRC) {
                    int dstR = r + drc[0];
                    int dstC = c + drc[1];
                    queue.add(new int[]{dstR, dstC});
                }
            }
        }

        return cnt;
    }

    // 0 1
    // 2 3
    int[][] dSrcRCs = new int[][]{
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
    };

    int[][] dToRCs = new int[][]{
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    // L제곱 크기로, 전체 격자를 나누어서 만들어진,
    // 각 부분격자의 가장 Top/left cornder의 row/col 값이 srcR, srcC
    public void rotate(int srcR, int srcC, int q) {
        int _2_l_1 = (q / 2);

        for (int idx = 0; idx < dSrcRCs.length; ++idx) {
            int dSrcR = dSrcRCs[idx][0];
            int dSrcC = dSrcRCs[idx][1];

            int dtoR = dToRCs[idx][0];
            int dtoC = dToRCs[idx][1];

            // part는 부분격자 안에서 회전할 때,
            // 같은 방향으로 이동하는 row/col의 top/left값
            int partR = srcR + (dSrcR * _2_l_1);
            int partC = srcC + (dSrcC * _2_l_1);

            // partR/C를 기준으로 2에 L-1제곱 크기, 격자 루핑
            for (int frR = partR; frR < partR + _2_l_1; ++frR) {
                for (int frC = partC; frC < partC + _2_l_1; ++frC) {
                    int toR = frR + (dtoR * _2_l_1);
                    int toC = frC + (dtoC * _2_l_1);

                    mapRotate[toR][toC] = map[frR][frC];
                }
            }
        }
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public int myPow(int a, int b) {
        int rtn = a;

        if (b == 0) {
            return 1;
        }

        --b;

        while (b > 0) {
            rtn *= a;
            --b;
        }

        return rtn;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}