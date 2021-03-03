import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    char[][] map;
    boolean[][] mapDot;
    ArrayList<int[]> Xs;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        mapDot = new boolean[N][N];
        Xs = new ArrayList<>();

        for (int i=0; i<N; ++i) {
            String strLine = br.readLine();

            for (int j=0; j<N; ++j) {
                map[i][j] = strLine.charAt(j);

                if (map[i][j] == 'X') {
                    Xs.add(new int[]{i, j});
                }
                else if (map[i][j] == '.') {
                    mapDot[i][j] = true;
                }
            }
        }

        boolean b = false;

        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                b = solve2(i, j);

                if (b)
                    break;
            }

            if (b)
                break;
        }

        for (int[] yx: Xs) {
            int y = yx[0];
            int x = yx[1];

            map[y][x] = 'X';
        }

        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                bw.write(map[i][j]);
            }
            bw.newLine();
        }

        bw.close();
    }

    boolean solve2(int sy, int sx) {
        int[][] rs = explode(sy, sx);

        if (rs == null) {
            return false;
        }

        map[sy][sx] = 'B';

        // 폭발과 닿았던 건물잔해를 치우고
        for (int[] Xyx: rs) {
            int Xy = Xyx[0];
            int Xx = Xyx[1];

            if (Xy == N) {
                continue;
            }

            map[Xy][Xx] = '.';
        }

        if (IsXcleared())
            return true;

        for (int y=sy; y<N; ++y) {
            for (int x=sx+1; x<N; ++x) {
                if (solve2(y, x)) {
                    return true;
                }
            }
        }

        // 폭발과 닿았던 건물잔해를 원상복구
        for (int[] Xyx: rs) {
            int Xy = Xyx[0];
            int Xx = Xyx[1];

            if (Xy == N) {
                continue;
            }

            map[Xy][Xx] = 'X';
        }

        map[sy][sx] = '.';

        return false;
    }

    int[][] explode(int y, int x) {
        if(false == mapDot[y][x]) {
            return null;
        }

        int[][] rs = new int[4][2];

        for (int idx=0; idx<d4i.length; ++idx) {
            rs[idx] = dotOrX(y, x, d4i[idx], d4j[idx]);

            if (rs[idx] == null) {
                return null;
            }
        }

        return rs;
    }

    int[] dotOrX(int y, int x, int dy, int dx) {
        while ((0<=y) && (y<N) && (0<=x) && (x<N)) {
            if (map[y][x] == '.') {
                // 점은 지나가기
            }
            else if (map[y][x] == 'X') {
                return new int[]{y, x};
            }
            else { // O
                return null;
            }

            y += dy;
            x += dx;
        }

        return new int[]{N, N};
    }

    boolean IsXcleared() {
        for (int[] yx: Xs) {
            int y = yx[0];
            int x = yx[1];

            if (map[y][x] == 'X')
                return false;
        }

        return true;
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}