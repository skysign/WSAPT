import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int[][] map;
    boolean[][] visited;

    public void solve() throws IOException {
        initPatterns();

        String[] strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; ++i) {
            strs = br.readLine().split(" ");

            for (int j=0; j<M; ++j) {
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int r = solve2(0, 0);
        bw.write(String.valueOf(r));
        bw.close();
    }

    static int Deg0= 0;
    static int Deg90= 1;
    static int Deg180= 2;
    static int Deg270= 3;
    static int _X= 0;
    static int _Y= 1;

    int[] cos = {1, 0, -1, 0};  /** cos0, cos90, cos180, cos270 */
    int[] sin = {0, 1, 0, -1};  /** sin0, sin90, sin180, sin270 */

    int[][] ptn0 = {{-1, 0}, {0, 0}, {0, -1}};  /** ⌝ */
    int[][] ptn1 = {{0, 0}, {0, 0}, {0, 0}};    /** ⌟ */
    int[][] ptn2 = {{0, 0}, {0, 0}, {0, 0}};    /** ⌞ */
    int[][] ptn3 = {{0, 0}, {0, 0}, {0, 0}};    /** ⌜ */

    int[][][] ptns = {ptn0, ptn1, ptn2, ptn3};

    void initPatterns() {
        for (int deg=Deg90; deg<=Deg270; ++deg) {
            for (int idx=0; idx<ptn0.length; ++idx) {
                ptns[deg][idx][_X] = rotateX(deg, ptn0[idx][_X], ptn0[idx][_Y]);
                ptns[deg][idx][_Y] = rotateY(deg, ptn0[idx][_X], ptn0[idx][_Y]);
            }
        }
    }

    /** Rotate X by clock-wise */
    int rotateX(int deg, int x, int y) {
        return (x * cos[deg]) + (y * sin[deg]);
    }

    /** Rotate Y by clock-wise */
    int rotateY(int deg, int x, int y) {
        return (y * cos[deg]) - (x * sin[deg]);
    }

    public int solve2(int sy, int sx) {
        int r = 0;

        for (int y=sy; y<N; ++y) {
            for (int x=sx; x<M; ++x) {
                if (visited[y][x])
                    continue;

                for (int[][] ptn: ptns) {
                    if (IsPtnInMapOrVisited(ptn, y, x)) {
                        markVisited(ptn, y, x);

                        // 부메랑의 강도 계산
                        int tr = getStrengthOfBoomerang(ptn, y, x);
                        tr += solve2(y, x +1);
                        r = Math.max(r, tr);

                        unmarkVisited(ptn, y, x);
                    }
                }
            }

            sx = 0;
        }

        return r;
    }

    int getStrengthOfBoomerang(int[][] ptn, int y, int x) {
        int r = 0;

        for (int i=0; i<ptn.length; ++i) {
            int[] yx = ptn[i];
            int ny = yx[_Y] +y;
            int nx = yx[_X] +x;

            r += map[ny][nx];

            if (i==1) {
                r += map[ny][nx];
            }
        }

        return r;
    }

    void markVisited(int[][] ptn, int y, int x) {
        _setVisited(ptn, y, x, true);
    }

    void unmarkVisited(int[][] ptn, int y, int x) {
        _setVisited(ptn, y, x, false);
    }

    void _setVisited(int[][] ptn, int y, int x, boolean b) {
        for (int[] yx: ptn) {
            int ny = yx[_Y] +y;
            int nx = yx[_X] +x;

            visited[ny][nx] = b;
        }
    }

    boolean IsPtnInMapOrVisited(int[][] ptn, int y, int x) {
        for (int[] yx: ptn) {
            int ny = yx[_Y] +y;
            int nx = yx[_X] +x;

            if (!IsInMap(ny, nx)) {
                return false;
            }

            if (visited[ny][nx])
                return false;
        }

        return true;
    }

    boolean IsInMap(int y, int x) {
        return ((0<=y) && (y<N) && (0<=x) && (x<M));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}