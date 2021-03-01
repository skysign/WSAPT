import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int R, C;
    char[][] map;
    boolean[][] visited;
    int[][] dp;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        dp = new int[R][C];


        for (int i=0; i<R; ++i) {
            String strLine = br.readLine();

            for (int j=0; j<C; ++j)
                map[i][j] = strLine.charAt(j);
        }

        int r =  solve2(0, 0, 1);

        bw.write(String.valueOf(r));
        bw.close();
    }

    int solve2(int y, int x, int depth) {
        if (!IsInMap(y, x))
            return 0;

        if (map[y][x] == 'H')
            return 0;

        if (visited[y][x] == true)
            return -1;

        if (dp[y][x] > 0)
            return dp[y][x] = Math.max(dp[y][x], depth);

        visited[y][x] = true;

        int r = depth;
        int v = map[y][x] - '0';

        for (int idx=0; idx<d4i.length; ++idx) {
            int ny = y + (d4i[idx] *v);
            int nx = x + (d4j[idx] *v);
            int t = solve2(ny, nx, depth +1);

            if (t == -1)
                return -1;

            r = Math.max(r, t);
        }

        dp[y][x] = r;
        visited[y][x] = false;

        return r;
    }

    boolean IsInMap(int y, int x) {
        return ((0<=y) && (y<R) && (0<=x) && (x<C));
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    // Initialize 3D arrays with value v
    public void fill3D(int[][][] _3D, int v) {
        for(int[][] _2D: _3D) {
            for(int[] _1D: _2D) {
                Arrays.fill(_1D, v);
            }
        }
    }

    // Initialize 2D arrays with value v
    public void fill2D(boolean[][] _2D, boolean v) {
        for(boolean[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}