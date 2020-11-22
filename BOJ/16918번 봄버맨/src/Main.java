import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    int R, C, N;
    int[][] map;

    int[] di = { 1, -1, 0, 0};
    int[] dj = { 0, 0, 1, -1};

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solve() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; ++i) {
            String str = input.readLine();

            for (int j = 0; j < C; ++j) {
                if (str.charAt(j) == 'O') {
                    map[i][j] = 3;
                }
                else {
                    map[i][j] = 0;
                }
            }
        }

        if (0 == N)
            return;

        secondPassed();
        --N;

        if (0 == N)
            return;

        boolean BombsOrPassed = true;

        for (int s = 0; s < N; ++s) {
            if (BombsOrPassed) {
                secondPassed();
                setupBombs();
                explodeBomb();
            }
            else {
                secondPassed();
                explodeBomb();
            }

            BombsOrPassed = !BombsOrPassed;
        }
    }

    public void secondPassed() {
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (map[i][j] > 0) {
                    --map[i][j];
                    if (map[i][j] == 0)
                        map[i][j] = -1;
                }

            }
        }
    }

    public void setupBombs() {
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (map[i][j] == 0) {
                    map[i][j] = 3;
                }
            }
        }
    }

    public void explodeBomb() {
        Deque<int[]> que = new ArrayDeque<>();

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (map[i][j] < 0) {
                    map[i][j] = 0;
                    que.add(new int[]{i, j});
                }
            }
        }

        for(int[] yx: que) {
            explodeBomb(yx[0], yx[1]);
        }
    }

    public void explodeBomb(int y, int x) {
        map[y][x] = 0;

        for(int idx = 0; idx < di.length; ++idx) {
            int cy = y + di[idx];
            int cx = x + dj[idx];
            if (IsInMap(cy, cx)) {
                map[cy][cx] = 0;
            }
        }
    }

    public void printMap() throws IOException {
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (map[i][j] > 0) {
                    bw.write('O');
                }
                else {
                    bw.write('.');
                }
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    boolean IsInMap(int y, int x) {
        return ((0 <= y) && (y < R) && (0 <= x) && (x < C));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
        main.printMap();
    }
}