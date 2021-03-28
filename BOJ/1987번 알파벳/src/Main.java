import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * BOJ 1987번 알파벳
 *
 * 문제 풀이 : https://youtu.be/DkWh6PYunOc
 *
 * 문제링크 : https://www.acmicpc.net/problem/1987
 *
 * 자바소스 : https://bit.ly/3csgU6Y
 */


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

    int solve2(int y, int x, int depth, HashSet<Character> hs) {
        hs.add(map[y][x]);

        int r = depth;

        for (int idx=0; idx<d4i.length; ++idx) {
            int ny = y + d4i[idx];
            int nx = x + d4j[idx];

            if (IsInMap(ny, nx) && (hs.contains(map[ny][nx]) == false)) {
                r = Math.max(r, solve2(ny, nx, depth +1, hs));
            }
        }

        hs.remove(map[y][x]);

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