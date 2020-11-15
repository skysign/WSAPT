import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public int M, N, H;
    public int[][][] map;
    Deque<int[]> que0;

    public void solve() {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();
        sc.nextLine();

        map = new int[H][N][M];
        Deque<int[]> queue = new ArrayDeque();
        que0 = new ArrayDeque();

        for(int i = 0; i < H; ++i) {
            for(int j = 0; j < N; ++j) {
                for(int k = 0; k < M; ++k) {
                    map[i][j][k] = sc.nextInt();

                    if(map[i][j][k] == 0) {
                        que0.add(new int[]{i, j, k});
                    }
                    else if(map[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                    }
                }

                sc.nextLine();
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
            int tmpZ = zyx[0];
            int tmpY = zyx[1];
            int tmpX = zyx[2];
            Deque<int[]> queueTmp = new ArrayDeque();

            queueTmp.add(new int[]{tmpZ +1, tmpY, tmpX});
            queueTmp.add(new int[]{tmpZ -1, tmpY, tmpX});
            queueTmp.add(new int[]{tmpZ, tmpY +1, tmpX});
            queueTmp.add(new int[]{tmpZ, tmpY -1, tmpX});
            queueTmp.add(new int[]{tmpZ, tmpY, tmpX +1});
            queueTmp.add(new int[]{tmpZ, tmpY, tmpX -1});

            while(queueTmp.size() > 0) {
                zyx = queueTmp.pop();
                int z = zyx[0];
                int y = zyx[1];
                int x = zyx[2];

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

    public static void main(String[] args) {
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
