import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] strs;

    int N, L;
    int[][] map;
    int[][] mapGyungsa;   // 경사로가 배치된 위치, 첫번째 배치된 경사로는 1로 표시, 두번째 배치된 경사로는 2로 표시
    int[][] mapScore;   // 경사로가 배치되어서, 서로 높이가 다른 지점이 연결가능한지 판정하는대 사용함
    ArrayList<int[][]> alGyungsa = new ArrayList<>();

    int[][] gyungH0;
    int[][] gyungH1;
    int[][] gyungV0;
    int[][] gyungV1;

    final int LH_RL = 21;   // left high, right low
    final int LL_RH = 12;   // left low, right high
    final int UH_DL = 21;   // up high, down low
    final int UL_DH = 12;   // up low, down high

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        L = Integer.parseInt(strs[1]);

        map = new int[N + 2][N + 2];
        mapGyungsa = new int[N + 2][N + 2];
        mapScore = new int[N + 2][N + 2];

        for (int i = 0; i < N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                map[i + 1][j + 1] = Integer.parseInt(strs[j]);
            }
        }

        // 가능한 경사로 4가지 경우
        gyungH0 = new int[1][L + 1];
        gyungH1 = new int[1][L + 1];
        gyungV0 = new int[L + 1][1];
        gyungV1 = new int[L + 1][1];

        for (int i = 0; i < L + 1; ++i) {
            if (i == 0) {
                gyungH0[0][i] = 0;
                gyungV0[i][0] = 0;
            } else {
                gyungH0[0][i] = 1;
                gyungV0[i][0] = 1;
            }
        }

        for (int i = 0; i < L + 1; ++i) {
            if (i == L) {
                gyungH1[0][i] = 0;
                gyungV1[i][0] = 0;
            } else {
                gyungH1[0][i] = 1;
                gyungV1[i][0] = 1;
            }
        }

//      gyungH0[0][1] = 1;
//      gyungV0[1][0] = 1;
//      gyungH1[0][L - 1] = 1;
//      gyungV1[L - 1][0] = 1;

        int r = 0;  // 길의 수

        // 가로 방향으로 길의 수를 확인
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (IsFit(i, j, gyungH0)) {
                    placeGyungsa(i, j, gyungH0, LH_RL);
                    j += (gyungH0[0].length - 2);
                } else if (IsFit(i, j, gyungH1)) {
                    placeGyungsa(i, j, gyungH1, LL_RH);
                    j += (gyungH1[0].length - 2);
                }
            }

            if (IsHorizontalGil(i)) {
                ++r;
            }
        }

        fill2D(mapGyungsa, 0);
        fill2D(mapScore, 0);

        // 새로 방향으로 길의 수를 확인
        for (int j = 1; j <= N; ++j) {
            for (int i = 1; i <= N; ++i) {
                if (IsFit(i, j, gyungV0)) {
                    placeGyungsa(i, j, gyungV0, UH_DL);
                    i += (gyungV0.length - 2);
                } else if (IsFit(i, j, gyungV1)) {
                    placeGyungsa(i, j, gyungV1, UL_DH);
                    i += (gyungV1.length - 2);
                }
            }

            if (IsVerticalGil(j)) {
                ++r;
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public boolean IsHorizontalGil(int y) {
        int i = y;

        for (int j = 1; j < N; ++j) {
            if (map[i][j] != map[i][j + 1]) {
                if (map[i][j] < map[i][j + 1]) {
                    if (mapGyungsa[i][j] != LL_RH)
                        return false;

                    if (map[i][j] != (map[i][j + 1] + mapScore[i][j + 1]))
                        return false;
                } else {  // map[i][j] > map[i][j + 1]
                    if (mapGyungsa[i][j + 1] != LH_RL)
                        return false;

                    if ((map[i][j] + mapScore[i][j]) != map[i][j + 1])
                        return false;
                }
            }
        }

//        for (int j = 1; j < N; ++j) {
//            if (map[i][j] != map[i][j + 1]) {
//                boolean b1 = (map[i][j] + mapScore[i][j]) != map[i][j + 1];
//                boolean b2 = map[i][j] != (map[i][j + 1] + mapScore[i][j + 1]);
//
//                if (b1 == b2)
//                    return false;
//            }
//        }

        return true;
    }

    public boolean IsVerticalGil(int x) {
        int j = x;
        for (int i = 1; i < N; ++i) {
            if (map[i][j] != map[i + 1][j]) {
                if (map[i][j] > map[i + 1][j]) {
                    if (mapGyungsa[i + 1][j] != UH_DL)
                        return false;

                    if ((map[i][j] + mapScore[i][j]) != map[i + 1][j])
                        return false;
                } else { // map[i][j] < map[i + 1][j]
                    if (mapGyungsa[i][j] != UL_DH)
                        return false;

                    if (map[i][j] != (map[i + 1][j] + mapScore[i + 1][j]))
                        return false;
                }

            }
        }

//        int j = x;
//        int y = 1;
//
//        if ((map[y][j] + mapScore[y][j]) != map[y + 1][j])
//            return false;
//
//        for (int i = 2; i < N; ++i) {
//            if (map[i][j] != map[i + 1][j]) {
//                if (map[i][j] > map[i + 1][j]) {
//                    if ((map[i][j] + mapScore[i][j]) != map[i + 1][j])
//                        return false;
//                } else { // map[i][j] < map[i + 1][j]
//                    if (map[i][j] != (map[i + 1][j] + mapScore[i + 1][j]))
//                        return false;
//                }
//            }
//        }
//
//        y = N-1;
//        if (map[y][j] != (map[y + 1][j] + mapScore[y + 1][j]))
//            return false;

        return true;
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public boolean IsFit(int sy, int sx, int[][] gyungsa) {
        boolean first = true;
        int v = 0;

        for (int i = 0; i < gyungsa.length; ++i) {
            for (int j = 0; j < gyungsa[i].length; ++j) {
                if (IsOutOfMap(sy + i, sx + j))
                    return false;

                int delta = 0;
                if (gyungsa[i][j] > 0)
                    delta = 1;

                if (first) {
                    first = false;
                    v = map[sy + i][sx + j] + delta;
                } else {
                    if (v != (map[sy + i][sx + j] + delta)) {
                        return false;
                    }
                }

                // 다른 경사로가 놓여 있으면
                if (gyungsa[i][j] > 0) {
                    if (mapGyungsa[sy + i][sx + j] > 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void placeGyungsa(int sy, int sx, int[][] gyungsa, int dir) {
        for (int i = 0; i < gyungsa.length; ++i) {
            for (int j = 0; j < gyungsa[i].length; ++j) {
                if (gyungsa[i][j] == 0) {
                    mapScore[sy + i][sx + j] = -1;
                } else if (gyungsa[i][j] == 1) {
                    mapGyungsa[sy + i][sx + j] = dir;
                }
            }
        }
    }

    public boolean IsOutOfMap(int y, int x) {
        if (0 == map[y][x])
            return true;

        return false;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}