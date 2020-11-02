import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    int T;
    int M;
    int[][] map;
    int srcX, srcY;
    int dstX, dstY;

    int[] d8i = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    int[] d8j = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

    public void solve() {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        sc.nextLine();

        for(int t = 0; t < T; ++t) {
            M = sc.nextInt();
            sc.nextLine();
            map = new int[M][M];

            srcY = sc.nextInt();
            srcX = sc.nextInt();
            sc.nextLine();
            dstY = sc.nextInt();
            dstX = sc.nextInt();

            Deque<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{srcY, srcX});
            map[srcY][srcX] = 1;

            int r = 0;
            r = BFS(queue, 2);
            r -= 1;
            System.out.println(r);
        }
    }

    public int BFS(Deque<int[]> queue, int depth) {
        int r = Integer.MAX_VALUE;
        Deque<int[]> queueNext = new ArrayDeque<>();

        while(queue.size() > 0) {
            int[] yx = queue.pop();
            int sY = yx[0];
            int sX = yx[1];

            if ((sY == dstY) && (sX == dstX)) {
                r = Math.min(r, map[sY][sX]);
            }

            for(int idx = 0; idx<d8i.length; ++idx) {
                int dY = sY + d8i[idx];
                int dX = sX + d8j[idx];

                if (IsIn(dY, dX) && (map[dY][dX] == 0)) {
                    if(!IsContained(queueNext, dY, dX)) {
                        queueNext.add(new int[]{dY, dX});
                        map[dY][dX] = depth;
                    }
                }
            }
        }

        if (queueNext.size() > 0)
            r = Math.min(r, BFS(queueNext, depth +1));

        return r;
    }

    public boolean IsContained(Deque<int[]> queue, int y, int x) {
        for(int[] yx: queue) {
            if(yx[0] == y && yx[1] == x)
                return true;
        }

        return false;
    }

    public boolean IsIn(int y, int x) {
        if ((0<=y) && (y<M) && (0<=x) && (x<M)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}