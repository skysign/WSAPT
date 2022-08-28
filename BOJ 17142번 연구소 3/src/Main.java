import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int[][] map;
    int[][] mapT;
    int[][] selectedVirus;
    String[] strs;
    ArrayList<int[]> alVirus;

    int cntEmpty = 0;
    int cntWall = 0;
    int ans = Integer.MAX_VALUE;

    static int EMPTY = 0;
    static int WALL = 1;
    static int VIRUS = 2;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        map = new int[N + 2][N + 2];
//        fill2D(map, WALL);
        for (int i = 0; i < N + 2; ++i) {
            map[i][0] = WALL;
            map[i][N + 1] = WALL;

            map[0][i] = WALL;
            map[N + 1][i] = WALL;
        }


        alVirus = new ArrayList<>();
        selectedVirus = new int[M][2];
//        alEmpty = new ArrayList<>();

        for (int i = 1; i <= N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 1; j <= N; ++j) {
                int v = Integer.parseInt(strs[j - 1]);

                map[i][j] = v;

                if (v == EMPTY) {
                    ++cntEmpty;
                } else if (v == WALL) {
                    ++cntWall;
                } else if (v == VIRUS) {
                    alVirus.add(new int[]{i, j, 1});
                }
            }
        }


        if (cntEmpty > 0) {
            combination(0, 0, alVirus.size(), M);
        } else {
            ans = 0;
        }

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    /*
     * nCr 조합으로 n개 중에서, r개 선택사기
     * start : 시작값 0, 조합을 구하는 과정에서 rec로 호출되며 사용될 변수
     * selected : 시작값 0, 조합을 구하는 과정에서 rec로 호출되며 사용될 변수
     */
    public void combination(int start, int selected, int N, int R) {
        if (selected == R) {
            findMinSecondFromMap(selectedVirus);
            return;
        }

        for (int i = start; i < N; ++i) {
            selectedVirus[selected] = alVirus.get(i);

            combination(start + 1, selected + 1, N, R);
        }
    }

    public void findMinSecondFromMap(int[][] virus) {
        mapT = new int[N + 2][N + 2];

        LinkedList<int[]> toVisit = new LinkedList<>();

        // 선택된 바이러스는 MAX_VALUE로 표시함
        // floodfill 에서 MAX_VALUE는 second값으로 덮어 쓰기 됨
        for (int[] syx : virus) {
            toVisit.add(syx);
            mapT[syx[0]][syx[1]] = 1;
        }

        floodFill(toVisit, mapT);
    }

    public int[][] ds = new int[][]{{-1, 0, 1, 0},
            {0, 1, 0, -1}};

    public void floodFill(LinkedList<int[]> toVisit, int[][] mapT) {
        int myCntEmpty = cntEmpty;

        while (!toVisit.isEmpty()) {
            int[] yxs = toVisit.poll();
            int sy = yxs[0];
            int sx = yxs[1];
            int second = yxs[2];

            for (int idx = 0; idx < ds[0].length; ++idx) {
                int dy = ds[0][idx];
                int dx = ds[1][idx];

                int ty = sy + dy;
                int tx = sx + dx;

                if (0 < mapT[ty][tx]) {
                    continue;
                }

                if (1 == map[ty][tx]) {
                    continue;
                }

                mapT[ty][tx] = second + 1;
                toVisit.add(new int[]{ty, tx, second + 1});

                if (0 == map[ty][tx]) {
                    --myCntEmpty;
                }

                if (myCntEmpty == 0) {
                    this.ans = Math.min(this.ans, second);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}