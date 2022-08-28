import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int[][] map;
    int[][][] map2;
    String[] strs;
    ArrayList<int[]> alVirus;
    ArrayList<int[]> alEmpty;

    static int EMPTY = 0;
    static int WALL = 1;
    static int VIRUS = 2;


    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        map = new int[N + 2][N + 2];
        fill2D(map, WALL);
        alVirus = new ArrayList<>();
        alEmpty = new ArrayList<>();

        for (int i = 1; i <= N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 1; j <= N; ++j) {
                int v = Integer.parseInt(strs[j - 1]);
                map[i][j] = v;

                if (v == EMPTY) {
                    alEmpty.add(new int[]{i, j});
                } else if (v == VIRUS) {
                    alVirus.add(new int[]{i, j});
                }
            }
        }

        map2 = new int[alVirus.size()][N + 2][N + 2];

        for (int m = 0; m < alVirus.size(); ++m) {
            floodFill(m);
        }

        int r = 0;

        if (alEmpty.size() > 0) {
            r = rec(0, new ArrayList<Integer>());
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public int[][] ds = new int[][]{{-1, 0, 1, 0},
            {0, 1, 0, -1}};

    private void floodFill(int m) {
        map2[m][alVirus.get(m)[0]][alVirus.get(m)[1]] = -1;

        ArrayDeque<int[]> toVisit = new ArrayDeque<>();
        int[] tmp = new int[]{alVirus.get(m)[0], alVirus.get(m)[1], 0};
        toVisit.add(tmp);

        while (toVisit.size() > 0) {
            int[] yx = toVisit.poll();
            int sy = yx[0];
            int sx = yx[1];
            int second = yx[2];

            if (-1 != map2[m][sy][sx]) {
                map2[m][sy][sx] = second;
            }

            ++second;

            for (int idx = 0; idx < ds[0].length; ++idx) {
                int dy = ds[0][idx];
                int dx = ds[1][idx];

                int ty = sy + dy;
                int tx = sx + dx;

                if ((map[ty][tx] == 0) || (map[ty][tx] == 2)) {
                    if (-1 != map2[m][ty][tx]) {
                        if (0 == map2[m][ty][tx]) {
                            toVisit.add(new int[]{ty, tx, second});
                        } else if (second < map2[m][ty][tx]) {
                            toVisit.add(new int[]{ty, tx, second});
                        }
                    }
                }
            }
        }
    }

    public int rec(int mo, ArrayList<Integer> al) throws IOException {
        int rtn = 0;
        int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

        // mo를 포함하고, M개를 선택
        ArrayList<Integer> al1 = new ArrayList<Integer>(al);
        al1.add(mo);

        if ((al1.size() < M) && ((mo + 1) < alVirus.size())) {
            r1 = rec(mo + 1, al1);
        } else if (al1.size() == M) {
            r1 = findMin(al1);
        }

        // mo를 제외하고, M개를 선택
        if ((mo + 1) < alVirus.size()) {
            r2 = rec(mo + 1, al);
        }

        if (r1 == Integer.MAX_VALUE) {
            r1 = -1;
        }

        if (r2 == Integer.MAX_VALUE) {
            r2 = -1;
        }

        if ((r1 > 0) && (r2 > 0)) {
            rtn = Math.min(r1, r2);
        } else if ((r1 == -1) && (r2 == -1)) {
            rtn = -1;
        } else if (r1 == -1) {
            rtn = r2;
        } else {
            rtn = r1;
        }


        return rtn;
    }

    public int findMin(ArrayList<Integer> al) throws IOException {
        int min = Integer.MIN_VALUE;

        // 선택된 바이러스 인덱스
//        bw.write('[');
//        for (int m : al) {
//            bw.write(String.valueOf(m));
//            bw.write(' ');
//        }
//        bw.write(']');
//        bw.newLine();
//        bw.flush();

        for (int[] yx : alEmpty) {
            int maxYX = Integer.MAX_VALUE;

            for (int m : al) {
                int y = yx[0];
                int x = yx[1];

                int v = map2[m][y][x];

                if (v != 0) {
                    maxYX = Math.min(maxYX, v);
                }
            }

            if (maxYX == Integer.MAX_VALUE) {
//                bw.write(String.valueOf(-1));
//                bw.newLine();
                return -1;
            }

            min = Math.max(min, maxYX);
        }

//        bw.write(String.valueOf(min));
//        bw.newLine();

        return min;
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