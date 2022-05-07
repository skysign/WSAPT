import java.io.*;

/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 14891번 톱니바퀴
 *
 * 유튜브 문제 풀이: https://youtu.be/uXPVbazZakw
 *
 * 문제링크: https://www.acmicpc.net/problem/14891
 *
 * 자바소스: https://bit.ly/3KUdQP1
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[][] tobni = new int[5][8];
    int N;

    String[] strs;

    static int CLOCKWISE = 1;
    static int COUNTER_CLOCKWISE = -1;

    public void solve() throws IOException {
        for (int i = 1; i < tobni.length; ++i) {
            String str = br.readLine();

            for (int j = 0; j < str.length(); ++j) {
                char c = str.charAt(j);

                if (c == '0') {
                    tobni[i][j] = 0;
                } else {
                    tobni[i][j] = 1;
                }
            }
        }

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            strs = br.readLine().split(" ");
            int nTomni = Integer.parseInt(strs[0]);
            int dir = Integer.parseInt(strs[1]);

            rotateTobni(nTomni, 0, dir);
        }

        /**
         *  07  00  01   07  00  01   07  00  01
         *  06      02   06      02   06      02
         *  05  04  03   05  04  03   05  04  03
         */

        int r = score();

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public int score() {
        int r = 0;

        if (tobni[1][0] == 1)
            r += 1;

        if (tobni[2][0] == 1)
            r += 2;

        if (tobni[3][0] == 1)
            r += 4;

        if (tobni[4][0] == 1)
            r += 8;

        return r;
    }

    public void rotateTobni(int nTobni, int dTobni, int dir) {
        if (dTobni == 0) {
            int d = -1;
            int v = nTobni + d;

            if ((1 <= v) && (v <= 4)) {
                if (tobni[v][2] != tobni[nTobni][6]) {
                    rotateTobni(v, d, (dir == CLOCKWISE) ? COUNTER_CLOCKWISE : CLOCKWISE);
                }
            }

            d = 1;
            v = nTobni + d;

            if ((1 <= v) && (v <= 4)) {
                if (tobni[nTobni][2] != tobni[v][6]) {
                    rotateTobni(v, d, (dir == CLOCKWISE) ? COUNTER_CLOCKWISE : CLOCKWISE);
                }
            }

            _rotateTobni(nTobni, dir);
        } else if (dTobni == -1) {
            int d = -1;
            int v = nTobni + d;

            if ((1 <= v) && (v <= 4)) {
                if (tobni[v][2] != tobni[nTobni][6]) {
                    rotateTobni(v, d, (dir == CLOCKWISE) ? COUNTER_CLOCKWISE : CLOCKWISE);
                }
            }

            _rotateTobni(nTobni, dir);
        } else if (dTobni == 1) {
            int d = 1;
            int v = nTobni + d;

            if ((1 <= v) && (v <= 4)) {
                if (tobni[nTobni][2] != tobni[v][6]) {
                    rotateTobni(v, d, (dir == CLOCKWISE) ? COUNTER_CLOCKWISE : CLOCKWISE);
                }
            }

            _rotateTobni(nTobni, dir);
        } else {
            // error
        }
    }

    public void _rotateTobni(int nTobni, int dir) {
        if (CLOCKWISE == dir) {
            int t = tobni[nTobni][7];

            tobni[nTobni][7] = tobni[nTobni][6];
            tobni[nTobni][6] = tobni[nTobni][5];
            tobni[nTobni][5] = tobni[nTobni][4];
            tobni[nTobni][4] = tobni[nTobni][3];
            tobni[nTobni][3] = tobni[nTobni][2];
            tobni[nTobni][2] = tobni[nTobni][1];
            tobni[nTobni][1] = tobni[nTobni][0];

            tobni[nTobni][0] = t;
        } else if (COUNTER_CLOCKWISE == dir) {
            int t = tobni[nTobni][0];

            tobni[nTobni][0] = tobni[nTobni][1];
            tobni[nTobni][1] = tobni[nTobni][2];
            tobni[nTobni][2] = tobni[nTobni][3];
            tobni[nTobni][3] = tobni[nTobni][4];
            tobni[nTobni][4] = tobni[nTobni][5];
            tobni[nTobni][5] = tobni[nTobni][6];
            tobni[nTobni][6] = tobni[nTobni][7];

            tobni[nTobni][7] = t;
        } else {
            // error
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}