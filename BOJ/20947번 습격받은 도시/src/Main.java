import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    char[][] map;
    int[][] mapSkip;
    boolean[][] mapDot;
    ArrayList<int[]> Xs;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        mapSkip = new int[N][N];
        mapDot = new boolean[N][N];
        Xs = new ArrayList<>();

        // 맵을 입력 받습니다.
        for (int i=0; i<N; ++i) {
            String strLine = br.readLine();

            for (int j=0; j<N; ++j) {
                map[i][j] = strLine.charAt(j);

                if (map[i][j] == 'X') {
                    // 폭탄위치도 기억해 둡니다.
                    Xs.add(new int[]{i, j});
                }
                else if (map[i][j] == '.') {
                    // '.'을 따로 기억해 두고, '.' 폭탄(B)를 놓는 것인지 체크용도로 사용합니다.
                    mapDot[i][j] = true;
                }
            }
        }

        boolean b = false;

        for (int i=0; i<N; ++i) {
            for (int j=0; j<N; ++j) {
                b = solve2(i, j);

                if (b) {
                    break;
                }
            }

            if (b)
                break;
        }

        // 폭탄(B)를 찾는 과정에서, X를 모두 'C'으로 지웠기 때문에
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

    void doSkipLineX(int y) {
        for (int skipX = 0; skipX < N; ++skipX) {
            mapSkip[y][skipX]++;
        }
    }

    void doSkipLineY(int x) {
        for (int skipY = 0; skipY < N; ++skipY) {
            mapSkip[skipY][x]++;
        }
    }

    void undoSkipLineX(int y) {
        for (int skipX = 0; skipX < N; ++skipX) {
            mapSkip[y][skipX]--;
        }
    }

    void undoSkipLineY(int x) {
        for (int skipY = 0; skipY < N; ++skipY) {
            mapSkip[skipY][x]--;
        }
    }

    // back-tracking으로 동작하는 함수
    boolean solve2(int sy, int sx) {
        // y, x 가 '.' 위치가 아니므로, 폭탄(B)를 놓을 수 없다.
        if(false == mapDot[sy][sx]) {
            return false;
        }

        int[][] rs = new int[4][2];
        boolean bMetX = false;
        boolean bMetC = false;
        int skipY = -1;
        int skipX = -1;

        // y, x 위치에서 4방향으로 가보기
        for (int idx=0; idx<d4i.length; ++idx) {
            // 한방향으로 가보기
            int rtn = dotOrX(sy, sx, d4i[idx], d4j[idx], rs[idx]);
            switch (rtn) {
                case 0:
                    break;
                case 1:
                    bMetX = true;
                    break;
                case 2:
                    if (idx % 2 == 0) { // i가 delta 있음
                        skipX = sx;
                    }
                    else {  // j가 delta 있음
                        skipY = sy;
                    }
                    break;
                case 3:
                    bMetC = true;
                    break;
            }
        }

        if (bMetC)
            bMetX = false;

        if ((bMetX) && (skipX == -1) && (skipY == -1)) {
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

            if (IsXcleared())
                return true;
        }
        else {
            if (skipX != -1) {
                doSkipLineY(skipX);
            }

            if (skipY != -1) {
                doSkipLineX(skipY);
            }
        }

        for (int x=sx+1; x<N; ++x) {
            if (mapSkip[sy][x] > 0)
                continue;

            if (mapDot[sy][x] == false)
                continue;

            if (map[sy][x] != '.')
                continue;

            if (solve2(sy, x)) {
                return true;
            }
        }

        for (int y=sy+1; y<N; ++y) {
            for (int x=0; x<N; ++x) {
                if (mapSkip[y][x] > 0)
                    continue;

                if (mapDot[y][x] == false)
                    continue;

                if (map[y][x] != '.')
                    continue;

                if (solve2(y, x)) {
                    return true;
                }
            }
        }

        if (skipX != -1) {
            undoSkipLineY(skipX);
        }

        if (skipY != -1) {
            undoSkipLineX(skipY);
        }

        if (bMetX) {
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
        }

        return false;
    }

    // 폭탄의 한방으로 갔을 때,
    // '.'을 계속 지나서 맵을 벗어나는 경우, {N,N}리턴
    // 건물잔해 'X'를 만난 경우, X의 위치 {y,x} 리턴
    // 그렇지 않는 모든 경우는 실패 null 리턴

    // 바깥 0
    // X 만난 1
    // O 만난 2
    int dotOrX(int y, int x, int dy, int dx, int[] rs) {
        while ((0<=y) && (y<N) && (0<=x) && (x<N)) {
            if (map[y][x] == '.' || map[y][x] == 'B' ) {
                // 점은 지나가기
            }
            else if (map[y][x] == 'X') {
                rs[0] = y;
                rs[1] = x;
                return 1;
            }
            else if (map[y][x] == 'C') {
                rs[0] = N;
                rs[1] = N;
                return 3;
            }
            else { // O 를 만난 경우
                rs[0] = N;
                rs[1] = N;
                return 2;
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

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}