import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * BOJ 2146번 다리 만들기
 *
 * 유튜브 문제 풀이 : https://youtu.be/2bY5l8V6ggo
 *
 * 문제링크 : https://www.acmicpc.net/problem/2146
 *
 * 자바소스 : https://bit.ly/2YfeJvx
 */
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
        visited[sy][sx] = true;

        while (que.size() > 0) {
            int[] yx = que.pop();
            int y = yx[0];
            int x = yx[1];

            map[y][x] = idOfIsland;

            for (int idx=0; idx<d4i.length; ++idx) {
                int dy = y + d4i[idx];
                int dx = x + d4j[idx];

                if (IsInMap(dy, dx)) {
                    if (oriId == map[dy][dx]) {
                        if (visited[dy][dx] == false) {
                            que.add(new int[]{dy, dx});
                            visited[dy][dx] = true;
                        }
                    }
                }
            }
        }
    }

    public boolean IsInMap(int y, int x) {
        return (0<=y) && (y<N) && (0<=x) && (x<N);
    }

    public int solve2() {
        int r = Integer.MAX_VALUE;

        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                if (0 != map[i][j]) {
                    int tmp = floodFillWithoutRecursion(i, j, map[i][j]);
                    if (tmp > 0) {
                        r = Math.min(r, tmp);
                    }
                }
            }
        }

        return r;
    }

    public int floodFillWithoutRecursion(int sy, int sx, int fromId) {
        int lengthOfBridge = 0;

        fill2D(visited, false);

        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (que.size() > 0) {
            int length = que.size();;

            for(int i=0; i<length; ++i) {
                int[] yx = que.pop();
                int y = yx[0];
                int x = yx[1];

                visited[y][x] = true;

                if ((map[y][x] > 0) & (fromId != map[y][x])) {
                    return (lengthOfBridge -1);
                }

                for (int idx=0; idx<d4i.length; ++idx) {
                    int dy = y + d4i[idx];
                    int dx = x + d4j[idx];

                    if (IsInMap(dy, dx)) {
                        if (fromId != map[dy][dx]) {
                            if (visited[dy][dx] == false) {
                                int[] tyx = new int[]{dy, dx};
                                que.add(tyx);
                                visited[dy][dx] = true;
                            }
                        }
                    }
                }
            } // for(int i=0; i<length; ++i) {

            lengthOfBridge++;
        } // while (que.size() > 0)

        return (lengthOfBridge -1);
    }

    public void fill2D(boolean[][] _2D, boolean v) {
        for(boolean[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};
}