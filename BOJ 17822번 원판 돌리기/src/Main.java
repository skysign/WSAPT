import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, T;
    AtomicInteger[][] map;
    int[] xt, dt, kt;
    static int CW = 0; // clock wise 시계 방향
    static int CCW = 1; // count clock wise 반 시계 방향

    int[][] drc = new int[][]{
            {-1, 0}, // ↑
            {1, 0},  // ↓
            {0, -1}, // ←
            {0, 1}   // →
    };

    int[] v0123 = new int[]{0, 1, 2, 3}; // r
    int[] v123 = new int[]{1, 2, 3};     // r -1
    int[] v023 = new int[]{0, 2, 3};     // r +1

    ArrayList<AtomicInteger> alRemove;
    String[] strs;

    public void solve() throws Exception {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        T = Integer.parseInt(strs[2]);

        map = new AtomicInteger[N][M];
        xt = new int[T];
        dt = new int[T];
        kt = new int[T];

        alRemove = new ArrayList<>();

        for (int n = 0; n < N; ++n) {
            strs = br.readLine().split(" ");

            for (int m = 0; m < M; ++m) {
                int v = Integer.parseInt(strs[m]);
                map[(N - 1) - n][m] = new AtomicInteger(v);
            }
        }

        for (int t = 0; t < T; ++t) {
            strs = br.readLine().split(" ");

            xt[t] = Integer.parseInt(strs[0]);
            dt[t] = Integer.parseInt(strs[1]);
            kt[t] = Integer.parseInt(strs[2]);
        }


        for (int t = 0; t < T; ++t) {
            for (int n = 1; n <= N; ++n) {
                if (0 == (n % xt[t])) {
                    rotate(n, dt[t], kt[t]);
                }
            }

            checkAdjToRemove();

            if (alRemove.size() > 0) {
                removeAdj();
            } else {
                averageP1M1();
            }

        }

        int r = 0;
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                r += map[n][m].intValue();
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }


    void rotate(int sx, int sd, int sk) throws Exception {
        int r = N - sx; // sx를 배열의 인덱스로 변경

        if (CW == sd) {
            rotateByCW(r, sk);
        } else if (CCW == sd) {
            rotateByCCW(r, sk);
        } else {
            throw new Exception("no CW CCW");
        }
    }

    // 시계 방향 회전
    void rotateByCW(int r, int K) {
        for (int k = 0; k < K; ++k) {
            int tmp = map[r][M - 1].intValue();

            for (int m = M - 1; m >= 1; --m) {
                map[r][m].set(map[r][m - 1].intValue());
            }

            map[r][0].set(tmp);
        }
    }

    // 반시계 방향 회전
    void rotateByCCW(int r, int K) {
        for (int k = 0; k < K; ++k) {
            int tmp = map[r][0].intValue();

            for (int m = 0; m < M - 1; ++m) {
                map[r][m].set(map[r][m + 1].intValue());
            }

            map[r][M - 1].set(tmp);
        }
    }

    void averageP1M1() {
        int sum = 0;
        int count = 0;

        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                if (map[n][m].intValue() > 0) {
                    sum += map[n][m].intValue();
                    ++count;
                }
            }
        }

        if (count == 0)
            return;

        int avg = sum / count;
        int remain = sum % count;

        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                if (map[n][m].intValue() > avg)
                    map[n][m].set(map[n][m].intValue() - 1);
                else if ((map[n][m].intValue() < avg) && (map[n][m].intValue() != 0))
                    map[n][m].set(map[n][m].intValue() + 1);
                else if ((remain > 0) && (map[n][m].intValue() == avg))
                    map[n][m].set(map[n][m].intValue() + 1);
            }
        }
    }

    void removeAdj() {
        for (Object o : alRemove) {
            AtomicInteger v = (AtomicInteger) o;
            v.set(0);
        }

        alRemove.clear();
    }

    void checkAdjToRemove() {
        for (int sr = 0; sr < N; ++sr) {
            for (int sc = 0; sc < M; ++sc) {
                for (int idx = 0; idx < drc.length; ++idx) {
                    int dr = drc[idx][0];
                    int dc = drc[idx][1];

                    int r = sr + dr;
                    int c = sc + dc;

                    if (c < 0) {
                        c = M - 1;
                    }

                    if (c >= M) {
                        c = 0;
                    }

                    if (!((0 <= r) && (r < N))) {
                        continue;
                    }

                    if (0 == map[sr][sc].intValue())
                        continue;

                    if (map[sr][sc].intValue() == map[r][c].intValue()) {
                        if (!alRemove.contains(map[sr][sc]))
                            alRemove.add(map[sr][sc]);

                        if (!alRemove.contains(map[r][c]))
                            alRemove.add(map[r][c]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.solve();
    }
}