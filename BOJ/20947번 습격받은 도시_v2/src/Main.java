import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    char[][] map;
    int[][] map2;
    int[][][][] warp;
    boolean[][] map2b;
    int cntXs;
    ArrayList<int[]> Xs;
    ArrayList<int[]> Os;
    ArrayList<int[]> Bs;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int r = 0;

        map = new char[N][N];
        map2 = new int[N][N];
        map2b = new boolean[N][N];
        warp = new int[N][N][4][2];
        Xs = new ArrayList<>();
        Os = new ArrayList<>();
        Bs = new ArrayList<>();

        // 맵을 입력 받습니다.
        for (int i=0; i<N; ++i) {
            String strLine = br.readLine();

            for (int j=0; j<N; ++j) {
                map[i][j] = strLine.charAt(j);

                if (map[i][j] == 'X') {
                    // 잔해위치도 저장해 둡니다.
                    Xs.add(new int[]{i, j});
                }
                else if (map[i][j] == 'O') {
                    // 건물위치도 저장해 둡니다.
                    Os.add(new int[]{i, j});
                }
            }
        }

        cntXs = Xs.size();

        for(int[] yx: Xs) {
            int sy = yx[0];
            int sx = yx[1];

            for (int idx=0; idx<d4i.length; ++idx) {
                int dy = d4i[idx];
                int dx = d4j[idx];

                int y = sy + dy;
                int x = sx + dx;

                while ((0<=y) && (y<N) && (0<=x) && (x<N) && (map[y][x] == '.')) {
                    //map2[y][x] = 1;
                    map2[y][x]++;
                    y += dy;
                    x += dx;
                }
            }
        }

        for(int[] yx: Os) {
            int sy = yx[0];
            int sx = yx[1];

            for (int idx=0; idx<d4i.length; ++idx) {
                int dy = d4i[idx];
                int dx = d4j[idx];

                int y = sy + dy;
                int x = sx + dx;

                while ((0<=y) && (y<N) && (0<=x) && (x<N) && (map[y][x] == '.')) {
                    map2[y][x] = 0;
                    y += dy;
                    x += dx;
                }
            }
        }

        for (int y=0; y<N; ++y) {
            for (int x=0; x<N; ++x) {
                if (map2[y][x] > 0) {
                    map2b[y][x] = true;
                    Bs.add(new int[]{y, x});
                }
            }
        }

        for (int y=0; y<N; ++y) {
            for (int x = 0; x < N; ++x) {
                setWarp(y, x);
            }
        }

        Collections.sort(Bs, new Comparator<int[]>() {
            @Override
            public int compare(int[] yx1, int[] yx2) {
                int y1 = yx1[0];
                int x1 = yx1[1];
                int y2 = yx2[0];
                int x2 = yx2[1];

                if (map2[y1][x1] > map2[y2][x2])
                    return -1;
                else if (map2[y1][x1] < map2[y2][x2])
                    return 1;
                return 0;
            }
        });

        for (int idx=0; idx<Bs.size(); idx++) {
            int[] yx = Bs.get(idx);
            int y = yx[0];
            int x = yx[1];

            if (solve2(y, x, idx+1, 0))
                break;
        }

//        boolean b = false;
//        for (int i=0; i<N; ++i) {
//            for (int j=0; j<N; ++j) {
//                if (map2[i][j] > 0) {
//                    b = solve2(i, j, 0);
//
//                    if (b) {
//                        break;
//                    }
//                }
//            }
//
//            if (b)
//                break;
//        }

        // 폭탄(B)를 찾는 과정에서, X를 모두 'C'로 지웠기 때문에
        // 맵에 다시 폭탄 위치를 적어 줍니다.
        for (int[] yx: Xs) {
            int y = yx[0];
            int x = yx[1];

            map[y][x] = 'X';
        }

        // 맵을 출력해주는 코드
        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                bw.write(map[i][j]);
            }
            bw.newLine();
        }

        bw.close();
    }

    void setWarp(int sy, int sx) {
        for (int idx=0; idx<d4i.length; ++idx) {
            int dy = d4i[idx];
            int dx = d4j[idx];

            int y = sy + dy;
            int x = sx + dx;

            while ((0<=y) && (y<N) && (0<=x) && (x<N) && (map[y][x] == '.')) {
                y += dy;
                x += dx;
            }

            warp[sy][sx][idx][0] = y;
            warp[sy][sx][idx][1] = x;
        }
    }

    boolean solve2(int sy, int sx, int idxBs, int myXs) {
        int[][] rs = new int[4][2];
        int cnt_1 = 0;
        int cnt0 = 0;
        int cnt1 = 0;

        for (int idx=0; idx<4; ++idx) {
            int ty = warp[sy][sx][idx][0];
            int tx = warp[sy][sx][idx][1];

            if ((0<=ty) && (ty<N) && (0<=tx) && (tx<N)) {
                if (map[ty][tx] == 'C') {
                    rs[idx][0] = N;
                    rs[idx][1] = N;
                    cnt_1++;
                }
                else if (map[ty][tx] == 'X') {
                    rs[idx][0] = ty;
                    rs[idx][1] = tx;
                    cnt1++;
                }
            }
            else {
                rs[idx][0] = N;
                rs[idx][1] = N;
                cnt0++;
            }
        }

        // y, x 위치에서 4방향으로 가보기
//        for (int idx=0; idx<d4i.length; ++idx) {
//            // 한방향으로 가보기
//            int rtn = XorC(sy, sx, d4i[idx], d4j[idx], rs[idx]);
//            switch (rtn) {
//                case -1:
//                    cnt_1++;
//                    break;
//                case 0:
//                    cnt0++;
//                    break;
//                case 1:
//                    cnt1++;
//                    break;
//            }
//        }

        // 0이 4가 아니고, 0과 1의 합이 4인 경우
        if ((cnt0 != 4) && (4 == (cnt0 + cnt1))) {
            map[sy][sx] = 'B';

            // 폭발과 닿았던 건물잔해 C 로 변경
            for (int[] Xyx: rs) {
                int Xy = Xyx[0];
                int Xx = Xyx[1];

                if (Xy == N) {
                    continue;
                }

                map[Xy][Xx] = 'C';
            }

            myXs += cnt1;

            if (cntXs == myXs)
                return true;

//            if (IsXcleared())
//                return true;

            setmap2(sy, sx, 0);
        }

        for (int idx=idxBs; idx<Bs.size(); idx++) {
            int[] yx = Bs.get(idx);
            int y = yx[0];
            int x = yx[1];

            if (map2[y][x] > 0) {
                if (solve2(y, x, idx + 1, myXs))
                    return true;
            }
        }

//        {
//            int y = sy;
//            for (int x=sx+1; x<N; ++x) {
//                if (map2[y][x] > 0) {
//                    if (solve2(y, x, 0)) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        for (int y=sy+1; y<N; ++y) {
//            for (int x=0; x<N; ++x) {
//                if (map2[y][x] > 0) {
//                    if (solve2(y, x, 0)) {
//                        return true;
//                    }
//                }
//            }
//        }

        // 0이 4가 아니고, 0과 1의 합이 4인 경우
        if ((cnt0 != 4) && (4 == (cnt0 + cnt1))) {
            setmap2(sy, sx, 1);

            myXs -= cnt1;

            // 폭발과 닿았던 건물잔해를 원상복구
            for (int[] Xyx : rs) {
                int Xy = Xyx[0];
                int Xx = Xyx[1];

                if (Xy == N) {
                    continue;
                }

                map[Xy][Xx] = 'X';
            }

            map[sy][sx] = '.';
        }

        return false;
    }

    int XorC(int y, int x, int dy, int dx, int[] rs) {
        while ((0<=y) && (y<N) && (0<=x) && (x<N)) {
            if (map[y][x] == '.') {
                // 점은 지나가기
            }
            else if (map[y][x] == 'B' ) {
                rs[0] = N;
                rs[1] = N;
                return -1;
            }
            else if (map[y][x] == 'C') {
                rs[0] = N;
                rs[1] = N;
                return -1;
            }
            else if (map[y][x] == 'X') {
                rs[0] = y;
                rs[1] = x;
                return 1;
            }

            y += dy;
            x += dx;
        }

        rs[0] = N;
        rs[1] = N;
        return 0;
    }

    boolean IsXcleared() {
        for (int[] yx: Xs) {
            int y = yx[0];
            int x = yx[1];

            if (map[y][x] == 'C')
                continue;
            else
                return false;
        }

        return true;
    }

    void setmap2 (int sy, int sx, int d) {
        map2[sy][sx] = d;

        for (int idx=0; idx<d4i.length; ++idx) {
            int dy = d4i[idx];
            int dx = d4j[idx];

            int y = sy + dy;
            int x = sx + dx;

            while ((0<=y) && (y<N) && (0<=x) && (x<N) && (map2b[y][x])) {
                map2[y][x] = d;
                y += dy;
                x += dx;
            }
        }
    }
    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}