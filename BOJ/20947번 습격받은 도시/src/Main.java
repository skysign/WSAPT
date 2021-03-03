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

        // 폭탄(B)를 찾는 과정에서, X를 모두 '.'으로 지웠기 때문에
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

    // back-tracking으로 동작하는 함수
    boolean solve2(int sy, int sx) {
        int[][] rs = explode(sy, sx);

        if (rs == null) {
            return false;
        }

        map[sy][sx] = 'B';

        // 폭발과 닿았던 건물잔해를 '.'으로 바꿔서, 맵에서 치우고
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

    // y, x 위치에서 폭탄을 폭발시켜보는 함수
    int[][] explode(int y, int x) {
        // y, x 가 '.' 위치가 아니므로, 폭탄(B)를 놓을 수 없다.
        if(false == mapDot[y][x]) {
            return null;
        }

        int[][] rs = new int[4][2];

        // y, x 위치에서 4방향으로 가보기
        for (int idx=0; idx<d4i.length; ++idx) {
            // 한방으로 가보기
            rs[idx] = dotOrX(y, x, d4i[idx], d4j[idx]);

            if (rs[idx] == null) {
                return null;
            }
        }

        return rs;
    }

    // 폭탄의 한방으로 갔을 때,
    // '.'을 계속 지나서 맵을 벗어나는 경우, {N,N}리턴
    // 건물잔해 'X'를 만난 경우, X의 위치 {y,x} 리턴
    // 그렇지 않는 모든 경우는 실패 null 리턴
    int[] dotOrX(int y, int x, int dy, int dx) {
        while ((0<=y) && (y<N) && (0<=x) && (x<N)) {
            if (map[y][x] == '.') {
                // 점은 지나가기
            }
            else if (map[y][x] == 'X') {
                return new int[]{y, x};
            }
            else { // O 를 만난 경우
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