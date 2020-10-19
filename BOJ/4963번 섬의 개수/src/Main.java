import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
/**
 * BOJ 2156번 포도주 시식
 *
 * 유튜브 문제 풀이
 * https://youtu.be/YEs45M7ujPI
 *
 * 문제링크 : https://www.acmicpc.net/problem/4963
 *
 * 자바소스 : https://bit.ly/2H5FVIp
 */
public class Main {
    int W, H;

    // Travel 8 ways, start from 12h, and rotate as clockwise
    //                          12     3       6       9
    public int[] d8i = new int[]{1, 1, 0, -1, -1, -1,  0,  1};
    public int[] d8j = new int[]{0, 1, 1,  1,  0, -1, -1, -1};

    public int[][] map;
    public int[][] visited;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        W = sc.nextInt();
        H = sc.nextInt();

        while((W != 0) && (H != 0)) {
            int r = 0;
            sc.nextLine();

            map = new int[H][W];
            visited = new int[H][W];

            for(int i=0; i<H; ++i) {
                for(int j=0; j<W; ++j) {
                    map[i][j] = sc.nextInt();
                }

                sc.nextLine();
            }

            for(int i=0; i<H; ++i) {
                for(int j=0; j<W; ++j) {
                    if((map[i][j] == 1) && (visited[i][j] == 0)) {
                        r++;
                        bfs(i, j, r);
                    }
                }
            }

            System.out.println(r);

            W = sc.nextInt();
            H = sc.nextInt();
        }
    }

    public void bfs(int y, int x, int cntIsland) {
        Deque<int[]> queue = new ArrayDeque();
        queue.add(new int[]{y, x});

        while(queue.size() > 0) {
            int[] pos = queue.pop();
            int sY = pos[0];
            int sX = pos[1];
            visited[sY][sX] = cntIsland;

            for(int idx = 0; idx< d8i.length; ++idx) {
                int dY = sY + d8i[idx];
                int dX = sX + d8j[idx];

                if(IsInMap(dY, dX) &&
                   (map[dY][dX] == 1) &&
                   (visited[dY][dX] == 0) &&
                   !IsContain(queue, dY, dX)) {
                    queue.add(new int[]{dY, dX});
                }
            }
        }
    }

    boolean IsContain(Deque<int[]> queue, int y, int x) {
        for(int[] yx: queue) {
            if(y == yx[0] && x == yx[1])
                return true;
        }

        return false;
    }

    boolean IsInMap(int y, int x) {
        if((0<=y) && (y<H) && (0<=x) && (x<W))
            return true;

        return false;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}