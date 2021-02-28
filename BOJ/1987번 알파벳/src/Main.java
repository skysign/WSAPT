import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[][] map;
    int R, C;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i=0; i<R; ++i) {
            String str = br.readLine();

            for (int j=0; j<C; ++j) {
                map[i][j] = str.charAt(j);
            }
        }

        int r = solve2(0, 0, 1, new HashSet<>());

        bw.write(String.valueOf(r));
        bw.close();
    }

    int solve2(int y, int x, int depth, HashSet<Character> ar) {
        ar.add(map[y][x]);

        int r = depth;

        for (int idx=0; idx<d4i.length; ++idx) {
            int ny = y + d4i[idx];
            int nx = x + d4j[idx];

            if (IsInMap(ny, nx) && (ar.contains(map[ny][nx]) == false)) {
                r = Math.max(r, solve2(ny, nx, depth +1, ar));
            }
        }

        ar.remove(map[y][x]);

        return r;
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    boolean IsInMap(int i, int j) {
        return ((0<=i) && (i<R) && (0<=j) && (j<C));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}