import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] strs;

    int N, M;
    int sx, sy, sdir;
    static int _N = 0;
    static int _E = 1;
    static int _S = 2;
    static int _W = 3;
    int[][] map;

    int[][] sDIR = {
            {-1, 0}, // N ↑
            {0, 1}, // E →
            {1, 0}, // S ↓
            {0, -1} // W ←
    };

    int[] counterClockwise = {
            3, 0, 1, 2
    };

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");
        sx = Integer.parseInt(strs[0]);
        sy = Integer.parseInt(strs[1]);
        sdir = Integer.parseInt(strs[2]);

        int r = 0;

        map = new int[N][M];

        for (int n = 0; n < N; ++n) {
            strs = br.readLine().split(" ");
            for (int m = 0; m < M; ++m) {
                map[n][m] = Integer.parseInt(strs[m]);
            }
        }

        while (true) {
            // 1. 현재 위치를 청소한다.
            if (map[sx][sy] == 0){
                map[sx][sy] = 2;
                ++r;
            }

            // a 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면,
            int dir = counterClockwise[sdir];
            int dx = sDIR[dir][0];
            int dy = sDIR[dir][1];

            if (map[sx + dx][sy + dy] == 0) {
                // 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
                sx = sx + dx;
                sy = sy + dy;
                sdir = dir;
            }
            // c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는,
            else if ((map[sx - 1][sy + 0] > 0)
                    && (map[sx + 0][sy + 1] > 0)
                    && (map[sx + 1][sy + 0] > 0)
                    && (map[sx + 0][sy - 1] > 0)) {

                // 바라보는 방향을 유지한 채로
                dx = (sDIR[sdir][0] * -1);
                dy = (sDIR[sdir][1] * -1);

                if ((map[sx + dx][sy + dy] == 0)
                    || (map[sx + dx][sy + dy] == 2)) {
                    //  한 칸 후진을 하고 2번으로 돌아간다.
                    sx = sx + dx;
                    sy = sy + dy;
                }
                else {
                    // d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
                    break;
                }
            }
            else {
                // b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
                sdir = dir;
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}