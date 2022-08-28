import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

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
    int ans = Integer.MAX_VALUE;

    static int EMPTY = 0;
    static int WALL = 1;
    static int VIRUS = 2;


    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        map = new int[N + 2][N + 2];
        fill2D(map, WALL);
        mapT = new int[N + 2][N + 2];
        alVirus = new ArrayList<>();
        selectedVirus = new int[M][2];
//        alEmpty = new ArrayList<>();

        for (int i = 1; i <= N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 1; j <= N; ++j) {
                int v = Integer.parseInt(strs[j - 1]);

                if (v == EMPTY) {
                    map[i][j] = v;
                    ++cntEmpty;
                } else if (v == WALL) {
                    map[i][j] = v;
                } else if (v == VIRUS) {
                    alVirus.add(new int[]{i, j});
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
            findMinSecond();
            return;
        }

        for (int i = start; i < N; ++i) {
            selectedVirus[selected][0] = alVirus.get(i)[0];
            selectedVirus[selected][1] = alVirus.get(i)[1];

            combination(start + 1, selected + 1, N, R);
        }
    }

    public void findMinSecond() {
        int t = findMinSecondFromMap(selectedVirus);

        if (t != -1) {
            ans = Math.min(ans, t);
        }
    }

    public int findMinSecondFromMap(int[][] virus) {
        copy2D(map, mapT);

        for (int[] syx : virus) {
            int sy = syx[0];
            int sx = syx[1];

            floodFill(sy, sx, mapT);
        }

        // 선택된 바이러스 인덱스
//        bw.write('[');
//        for (int m : al) {
//            bw.write(String.valueOf(m));
//            bw.write(' ');
//        }
//        bw.write(']');
//        bw.newLine();
//        bw.flush();

        int minSecond = Integer.MIN_VALUE;

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (map[i][j] == 0) {
                    int y = i;
                    int x = j;

                    int v = mapT[y][x];

                    if (v == 0) {
                        return -1;
                    } else {
                        minSecond = Math.max(minSecond, v);
                    }
                }
            }
        }

        return minSecond;
    }

    public int[][] ds = new int[][]{{-1, 0, 1, 0},
            {0, 1, 0, -1}};

    private void floodFill(int y, int x, int[][] mapT) {
        ArrayDeque<int[]> toVisit = new ArrayDeque<>();
        int[] tmp = new int[]{y, x, 0};
        toVisit.add(tmp);

        while (toVisit.size() > 0) {
            int[] yx = toVisit.poll();
            int sy = yx[0];
            int sx = yx[1];
            int second = yx[2];

            mapT[sy][sx] = second;

            ++second;

            for (int idx = 0; idx < ds[0].length; ++idx) {
                int dy = ds[0][idx];
                int dx = ds[1][idx];

                int ty = sy + dy;
                int tx = sx + dx;

                if (map[ty][tx] == 0) {
                    if (0 == mapT[ty][tx]) {
                        toVisit.add(new int[]{ty, tx, second});
                    } else if (second < mapT[ty][tx]) {
                        toVisit.add(new int[]{ty, tx, second});
                    }
                }
            }
        }
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public int[][] copy2D(int[][] src, int[][] dst) {
        int N = src.length;

        for (int i = 0; i < N; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, N);
        }

        return dst;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}