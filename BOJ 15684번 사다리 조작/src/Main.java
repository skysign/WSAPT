import java.io.*;

/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 14499번 최단경로
 *
 * 유튜브 문제 풀이: https://youtu.be/VoZuHNw24tw
 * 유튜브 문제 풀이: https://youtu.be/KDIm_wALXQA
 *
 * 문제링크: https://www.acmicpc.net/problem/15684
 *
 * 자바소스: https://bit.ly/3PMIQUK
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, H;
    String[] strs;
    int[][] map;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        H = Integer.parseInt(strs[2]);

        map = new int[H + 1][N + 1];

        for (int i = 1; i <= M; ++i) {
            strs = br.readLine().split(" ");
            int y = Integer.parseInt(strs[0]);
            int x = Integer.parseInt(strs[1]);
            map[y][x] = i;
            map[y][x + 1] = i;
        }

        int r = -1;

        for (int maxDepth = 0; maxDepth <= 3; ++maxDepth) {
            r = rec(map, 1, 1, 0, maxDepth);
            if (r == 1) {
                r = maxDepth;
                break;
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public int rec(int[][] map, int sy, int sx, int depth, int maxDepth) {
        if (depth == maxDepth) {
            if (sadari(map))
                return 1;

            return -1;
        }

        for (int x = sx; x < N; ++x) {
            if ((map[sy][x] == 0) && (map[sy][x + 1] == 0)) {
                map[sy][x] = M + depth + 1;
                map[sy][x + 1] = M + depth + 1;

                if (rec(map, sy, x + 2, depth + 1, maxDepth) == 1)
                    return 1;

                map[sy][x] = 0;
                map[sy][x + 1] = 0;
            }
        }

        for (int y = sy + 1; y <= H; ++y) {
            for (int x = 1; x < N; ++x) {
                if ((map[y][x] == 0) && (map[y][x + 1] == 0)) {
                    map[y][x] = M + depth + 1;
                    map[y][x + 1] = M + depth + 1;

                    if (rec(map, y, x + 2, depth + 1, maxDepth) == 1)
                        return 1;

                    map[y][x] = 0;
                    map[y][x + 1] = 0;
                }
            }
        }

        return -1;
    }

    public boolean sadari(int[][] map) {
        for (int x = 1; x <= N; ++x) {
            int r = sadariX(map, x);

            if (r != x)
                return false;
        }

        return true;
    }

    public int sadariX(int[][] map, int sx) {
        int x = sx;

        for (int y = 1; y <= H; ++y) {
            int dx = sadariLorR(map, y, x);
            x += dx;
        }

        return x;
    }

    public int sadariLorR(int[][] map, int y, int x) {
        if (map[y][x] >= 1) {
            if ((x - 1 >= 1) && (map[y][x - 1] == map[y][x])) {
                return -1;
            } else if ((x + 1 <= N) && (map[y][x + 1] == map[y][x])) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}