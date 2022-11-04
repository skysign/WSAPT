import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, K;
    int[][] map;
    int[][] mapVisitied;

    // 동→, 서←, 남↓, 북↑
    static int ET = 0;
    static int WT = 1;
    static int ST = 2;
    static int NT = 3;
    int[] bandaeWay = new int[]{WT, ET, NT, ST};
    int[] dR = new int[]{0, 0, 1, -1};
    int[] dC = new int[]{1, -1, 0, 0};
    int[] rotate90CW = new int[]{ST, NT, WT, ET};
    int[] rotate90CCW = new int[]{NT, ST, ET, WT};

    String[] strs;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        K = Integer.parseInt(strs[2]);

        map = new int[N][M];
        mapVisitied = new int[N][M];

        for (int n = 0; n < N; ++n) {
            strs = br.readLine().split(" ");

            for (int m = 0; m < M; ++m) {
                map[n][m] = Integer.parseInt(strs[m]);
            }
        }

        int ans = 0;

        int dir = ET;
        int r = 0, c = 0;

        for (int k = 0; k < K; ++k) {
            int dr = dR[dir];
            int dc = dC[dir];

            int nr = r + dr;
            int nc = c + dc;

            if (!((0 <= nr) && (nr < N) && (0 <= nc) && (nc < M))) {
                dir = bandaeWay[dir];
                dr = dR[dir];
                dc = dC[dir];

                nr = r + dr;
                nc = c + dc;
            }

            r = nr;
            c = nc;

            int diceNumber = rollDice(dir);
            fill2D(mapVisitied, 0);
            int count = bfs(r, c);
            int panNumber = map[r][c];

            ans += (count * panNumber);

            if (diceNumber > panNumber) {
                // 시계 90도 회전
                dir = rotate90CW[dir];
            } else if (diceNumber < panNumber) {
                // 반시계 90도 회전
                dir = rotate90CCW[dir];
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    Deque<int[]> queue = new ArrayDeque<>();

    int bfs(int sr, int sc) {
        int rtn = 0;
        int number = map[sr][sc];

        queue.add(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] rc = queue.pop();
            int r = rc[0];
            int c = rc[1];

            if (!((0 <= r) && (r < N) && (0 <= c) && (c < M))) {
                continue;
            }

            // visit
            if (map[r][c] == number && mapVisitied[r][c] == 0) {
                ++rtn;
                mapVisitied[r][c] = 1;

                for (int dir = 0; dir < 4; ++dir) {
                    int nr = r + dR[dir];
                    int nc = c + dC[dir];

                    queue.add(new int[]{nr, nc});
                }
            }
        }

        return rtn;
    }

    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    int[][] dice = new int[][]{
            {0, 2, 0, 0},
            {4, 1, 3, 6},
            {0, 5, 0, 0},
            {0, 6, 0, 0}
    };

    // dir 방향으로 구른 뒤에,
    int rollDice(int dir) {
        if (dir == ET) {
            int tmp = dice[1][3];
            dice[1][3] = dice[1][2];
            dice[1][2] = dice[1][1];
            dice[1][1] = dice[1][0];
            dice[1][0] = tmp;

            dice[3][1] = dice[1][3];
        } else if (dir == WT) {
            int tmp = dice[1][0];
            dice[1][0] = dice[1][1];
            dice[1][1] = dice[1][2];
            dice[1][2] = dice[1][3];
            dice[1][3] = tmp;

            dice[3][1] = dice[1][3];
        } else if (dir == ST) {
            int tmp = dice[3][1];
            dice[3][1] = dice[2][1];
            dice[2][1] = dice[1][1];
            dice[1][1] = dice[0][1];
            dice[0][1] = tmp;

            dice[1][3] = dice[3][1];
        } else {
            int tmp = dice[0][1];
            dice[0][1] = dice[1][1];
            dice[1][1] = dice[2][1];
            dice[2][1] = dice[3][1];
            dice[3][1] = tmp;

            dice[1][3] = dice[3][1];
        }

        return dice[1][3];
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}