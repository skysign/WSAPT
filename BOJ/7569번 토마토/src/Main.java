import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * BOJ 7569번 토마토
 *
 * 유튜브 문제 풀이
 * https://youtu.be/B7r6HHQxYgw
 *
 * 문제링크 : https://www.acmicpc.net/problem/16918
 *
 * 자바소스 : https://bit.ly/35xcyrJ
 */
public class Main {
    public int M, N, H;
    public int[][][] map;
    Deque<int[]> que0;
    static int[] dZ = { 1, -1, 0, 0, 0, 0 };
    static int[] dY = { 0, 0, 1, -1, 0, 0 };
    static int[] dX = { 0, 0, 0, 0, 1, -1 };

    public void solve() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        // Scanner sc = new Scanner(System.in);

        M = Integer.parseInt(st.nextToken()); // sc.nextInt();
        N = Integer.parseInt(st.nextToken()); // sc.nextInt();
        H = Integer.parseInt(st.nextToken()); // sc.nextInt();
        // sc.nextLine();

        map = new int[H][N][M];
        Deque<int[]> queue = new ArrayDeque();
        que0 = new ArrayDeque();

        for(int i = 0; i < H; ++i) {
            for(int j = 0; j < N; ++j) {
                st = new StringTokenizer(input.readLine());

                for(int k = 0; k < M; ++k) {
                    map[i][j][k] = Integer.parseInt(st.nextToken()); // sc.nextInt();

                    if(map[i][j][k] == 0) {
                        que0.add(new int[]{i, j, k});
                    }
                    else if(map[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                    }
                }

                // sc.nextLine();
            }
        }

        // flood fill
        int r = floodfill(queue,0);
        System.out.println(r);
    }

    public int floodfill(Deque<int[]> queue, int days) {
        if(IsReady())
            return days;

        Deque<int[]> queueNext = new ArrayDeque();

        while(queue.size() > 0) {
            int[] zyx = queue.pop();

            for(int idx = 0; idx<dZ.length; ++idx) {
                int z = zyx[0] + dZ[idx];
                int y = zyx[1] + dY[idx];
                int x = zyx[2] + dX[idx];

                if(IsInMap(z, y, x)) {
                    if(map[z][y][x] == 0) {
                        map[z][y][x] = 1;
                        queueNext.add(new int[]{z, y, x});
                    }
                }
            }
        }

        if(queueNext.size() > 0) {
            return floodfill(queueNext, days +1);
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
	    Main main = new Main();
	    main.solve();
    }

    public boolean IsReady() {
        for(int[] zyx: que0) {
            int z = zyx[0];
            int y = zyx[1];
            int x = zyx[2];

            if(map[z][y][x] == 0) {
                return false;
            }
            else if (map[z][y][x] == 1) {
                que0.remove(zyx);
            }
        }

        return true;
    }

    public boolean IsInMap(int z, int y, int x) {
        if(0 <= z && z < H && 0 <= y && y < N && 0 <= x && x < M) {
            return true;
        }

        return false;
    }
}