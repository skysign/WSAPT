/**
 * BOJ 16234번 인구 이동
 *
 * 유튜브 문제 풀이: https://youtu.be/d_W8nLUYT5o
 *
 * 문제링크: https://www.acmicpc.net/problem/16234
 *
 * 자바소스: https://bit.ly/3RVTZ6Q
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[][] dD = new int[][]{
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    int N, L, R;
    String[] strs;
    int[][] map;
    int[][] mapVisitied;

    ArrayList<ArrayList<int[]>> alAlliance = new ArrayList<>();

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        L = Integer.parseInt(strs[1]);
        R = Integer.parseInt(strs[2]);

        map = new int[N + 2][N + 2];
        fill2D(map, -1);

        mapVisitied = new int[N + 2][N + 2];
        fill2D(mapVisitied, 0);

        for (int i = 1; i < N + 1; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 1; j < N + 1; ++j) {
                map[i][j] = Integer.parseInt(strs[j - 1]);
            }
        }

        int r = 0;

        while (true) {
            fill2D(mapVisitied, 0);
            alAlliance.clear();

            for (int sy = 1; sy < N + 1; ++sy) {
                for (int sx = 1; sx < N + 1; ++sx) {
                    if (mapVisitied[sy][sx] == 0) {
                        ArrayList<int[]> al = openBorder(sy, sx);

                        if (al.size() > 1) {
                            alAlliance.add(al);
                        }
                    }
                }
            }

            if (alAlliance.size() > 0) {
                for (ArrayList<int[]> alVisitied: alAlliance) {
                    int sum = 0;
                    for (int[] yx: alVisitied) {
                        sum += map[yx[0]][yx[1]];
                    }

                    int v = sum / alVisitied.size();
                    for (int[] yx: alVisitied) {
                        map[yx[0]][yx[1]] = v;
                    }
                }

                ++r;
            }
            else {
                break;
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    ArrayList<int[]> openBorder(int _y, int _x) {
        ArrayList<int[]> alVisit = new ArrayList<>();
        alVisit.add(new int[]{_y, _x});
        mapVisitied[_y][_x] = 1;

        ArrayList<int[]> alVisited = new ArrayList<>();

        while (false == alVisit.isEmpty()) {
            int[] syx = alVisit.get(0);
            alVisit.remove(0);
            alVisited.add(syx);

            int sy = syx[0];
            int sx = syx[1];

            for (int[] dd : dD) {
                int dy = dd[0];
                int dx = dd[1];

                int y = dy + sy;
                int x = dx + sx;

                // 이동할 수 있는 곳인지 확인
                if ((map[y][x] != -1) && (mapVisitied[y][x] != 1)) {
                    int diff = Math.abs(map[sy][sx] - map[y][x]);
                    if ((L <= diff) && (diff <= R)) {
                        alVisit.add(new int[]{y, x});
                        mapVisitied[y][x] = 1;
                    }
                }
            } // for (int[] d : dD) {
        }

        return alVisited;
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void copy2D(int[][] src, int[][] dst) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, src[i].length);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}