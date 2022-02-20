import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, x, y, nMove;
    String[] strs;

    static int MOVE_E = 1;
    static int MOVE_W = 2;
    static int MOVE_N = 3;
    static int MOVE_S = 4;

    int map[][];
    int EWNS[][] = {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };

//        int[][] dice = new int[4][];
//        dice[0] = new int[]{0, 0, 2, 0};
//        dice[1] = new int[]{6, 4, 1, 3};
//        dice[2] = new int[]{0, 0, 5, 0};
//        dice[3] = new int[]{0, 0, 6, 0};

    int dice[][] = new int[4][4];

    public void solve() throws IOException {

        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        x = Integer.parseInt(strs[2]);
        y = Integer.parseInt(strs[3]);
        nMove  = Integer.parseInt(strs[4]);

        int r = 0;

        x +=1;
        y +=1;

        map = new int[N+2][M+2];
        fill2D(map, -1);

        for (int n = 0; n<N; ++n) {
            strs = br.readLine().split(" ");

            for (int m =0; m<M; ++m) {
                map[n+1][m+1] = Integer.parseInt(strs[m]);
            }
        }

        strs = br.readLine().split(" ");

        for (int m = 0; m < nMove; m++) {
            int v = Integer.parseInt(strs[m]);
            int di = EWNS[v-1][0];
            int dj = EWNS[v-1][1];

            int i = x + di;
            int j = y + dj;

            if (map[i][j] == -1) {
//                do nothing
            }
            else {
                rollDice(v);

                if (map[i][j] == 0) {
                    map[i][j] = dice[1][2];
                }
                else {
                    dice[1][2] = map[i][j];
                    map[i][j] = 0;
                }

                x = i;
                y = j;

                r = dice[1][0];
                bw.write(String.valueOf(r));
                bw.newLine();
                bw.flush();
            }
        }

        bw.close();
    }

    public void rollDice(int move) {
        int t;

        if (MOVE_E == move) {
            // 동쪽으로 이동하면,
            // 주사위의 값은, 왼쪽에서 오른쪽으로 swap
            int i = 1;
            for (int j=0; j<4-1; ++j) {
                t = dice[i][j];
                dice[i][j] = dice[i][j+1];
                dice[i][j+1] = t;
            }

            dice[3][2] = dice[1][0];
        }
        else if (MOVE_W == move) {
            int i = 1;
            for (int j=4-1; j>=1; --j) {
                t = dice[i][j];
                dice[i][j] = dice[i][j-1];
                dice[i][j-1] = t;
            }

            dice[3][2] = dice[1][0];
        }
        else if (MOVE_N == move) {
            int j = 2;
            for (int i=4-1; i>=1; --i) {
                t = dice[i][j];
                dice[i][j] = dice[i-1][j];
                dice[i-1][j] = t;
            }

            dice[1][0] = dice[3][2];
        }
        else { // MOVE_S
            int j = 2;
            for (int i=0; i<4-1; ++i) {
                t = dice[i][j];
                dice[i][j] = dice[i+1][j];
                dice[i+1][j] = t;
            }

            dice[1][0] = dice[3][2];
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }
}
