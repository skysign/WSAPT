import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    int M, N;
    int[][] map;
    int[] di = new int[]{1, -1, 0, 0};
    int[] dj = new int[]{0, 0, 1, -1};
    Deque<int[]> queNotReady = new ArrayDeque<>();

    public void solve() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        Deque<int[]> que = new ArrayDeque<>();
        map = new int[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());

                switch (map[i][j]) {
                    case 0:
                        queNotReady.add(new int[]{i, j});
                        break;
                    case 1:
                        que.add(new int[]{i, j});
                        break;
                }
            }
        }

        int r = floodFill(que, 0);
        System.out.println(r);
    }

    public int floodFill(Deque<int[]> que, int day) {
        if (IsReady())
            return day;

        Deque<int[]> queNext = new ArrayDeque<>();

        while (que.size() > 0) {
            int[] yx = que.pop();
            int y = yx[0];
            int x = yx[1];

            for (int idx = 0; idx < di.length; ++idx) {
                int cy = y + di[idx];
                int cx = x + dj[idx];

                if (IsInMap(cy, cx)) {
                    if (map[cy][cx] == 0) {
                        map[cy][cx] = 1;
                        queNext.add(new int[]{cy, cx});
                    }
                }
            }
        }

        if (queNext.size() > 0)
            return floodFill(queNext, day +1);

        return -1;
    }

    public boolean IsReady() {
        for(int[] yx: queNotReady) {
            int y = yx[0];
            int x = yx[1];

            if (map[y][x] == 0) {
                return false;
            }
            else if (map[y][x] == 1) {
                queNotReady.remove(yx);
            }
        }

        return true;
    }

    public boolean IsInMap(int y, int x) {
        return ((0 <= y) && (y < N) && (0 <= x) && (x < M));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}