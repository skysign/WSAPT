import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[][] map;
    boolean[][] visited;
    int N;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i=0; i<N; ++i) {
            st = new StringTokenizer(br.readLine());

            for (int j=0; j<N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idOfIsland = N * N;

        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                if ((map[i][j] < idOfIsland) && (map[i][j] != 0)) {
                    setIdOfIsland(i, j, map[i][j], idOfIsland);
                    idOfIsland--;
                }
            }
        }

        int r = solve2();

        bw.write(String.valueOf(r));
        bw.close();
    }

    public void setIdOfIsland(int sy, int sx, int oriId, int idOfIsland) {
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{sy, sx});

        while (que.size() > 0) {
            int[] yx = que.pop();
            int y = yx[0];
            int x = yx[1];

            map[y][x] = idOfIsland;

            for (int idx=0; idx<d4i.length; ++idx) {
                int dy = y + d4i[idx];
                int dx = x + d4j[idx];

                if (IsInMap(dy, dx) && (oriId == map[dy][dx])) {
                    que.add(new int[]{dy, dx});
                }
            }
        }
    }

    public boolean IsInMap(int y, int x) {
        return (0<=y) && (y<N) && (0<=x) && (x<N);
    }

    public int solve2() {
        int r = 0;

        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                if (0 != map[i][j]) {
                    int tmp = floodFillWithoutRecursion(i, j, map[i][j]);
                    r = Math.max(r, tmp);
                }
            }
        }

        return (Integer.MAX_VALUE - r);
    }

    public int floodFillWithoutRecursion(int sy, int sx, int fromId) {
        int depth = -1;
        int r = 0;

        fill2D(visited, false);

        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{sy, sx});

        Deque<int[]> queNext = new ArrayDeque<>();

        while (queNext != null) {
            while (que.size() > 0) {
                int[] yx = que.pop();
                int y = yx[0];
                int x = yx[1];

                visited[y][x] = true;

                if ((map[y][x] > 0) & (fromId != map[y][x])) {
                    r = Math.max(r, Integer.MAX_VALUE -depth);
                    return r;
                }

                for (int idx=0; idx<d4i.length; ++idx) {
                    int dy = y + d4i[idx];
                    int dx = x + d4j[idx];

                    if (IsInMap(dy, dx)) {
                        if (fromId != map[dy][dx]) {
                            if (visited[dy][dx] == false) {
                                int[] tyx = new int[]{dy, dx};

                                if (!IsContained(tyx, queNext))
                                    queNext.add(tyx);
                            }
                        }
                    }
                }
            } // while (que.size() > 0)

            depth++;

            if (queNext.size() <= 0) {
                queNext = null;
            }
            else {
                // swap two queues
                Deque<int[]> queTmp = que;
                que = queNext;
                queNext = queTmp;
            }
        } // while (queNext != null) {

        return r;
    }

    public void fill2D(boolean[][] _2D, boolean v) {
        for(boolean[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public boolean IsContained(int[] tyx, Deque<int[]> que) {
        for(int[] yx: que) {
            if ((tyx[0] == yx[0]) && (tyx[1] == yx[1]))
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};
}