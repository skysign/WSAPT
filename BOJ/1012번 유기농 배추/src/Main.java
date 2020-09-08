import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public int M;
    public int N;
    public int T;
    public int K;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int t = 0; t < T; ++t) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();

            int[][] map = new int[M][N];
            int[][] visited = new int[M][N];

            for(int k = 0; k<K; ++k) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[x][y] = 1;
            }

            int r = findMin(map, visited);
            System.out.println(r);
        }
    }

    public int findMin(int[][] map, int[][] visited) {
        int r = 0;

        for(int i=0; i<M; ++i) {
            for(int j=0; j<N; ++j) {
                if(map[i][j] == 1 && visited[i][j] == 0) {
                    bfs(i, j, map, visited);
                    r++;
                }
            }
        }

        return r;
    }

    public boolean IsInMap(int x, int y) {
        if(0 <= x && x < M && 0 <= y && y < N)
            return true;

        return false;
    }

    public void bfs(int xs, int ys, int[][] map, int[][] visited) {
        visited[xs][ys] = 1;

        ArrayList<Integer[]> al = new ArrayList();
        al.add(new Integer[]{xs, ys});

        while(al.size() > 0) {
            Integer[] xy = al.get(0);
            al.remove(0);

            int x = xy[0];
            int y = xy[1];

            for(int di = 0; di < d4i.length; ++di) {
                int dx = x + d4i[di];
                int dy = y + d4j[di];

                if(IsInMap(dx, dy)) {
                    if(map[dx][dy] == 1 && visited[dx][dy] == 0) {
                        al.add(new Integer[]{dx, dy});
                        visited[dx][dy] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }

    // 4 ways
    public int[] d4i = new int[]{-1, 1, 0, 0};
    public int[] d4j = new int[]{0, 0, -1, 1};
}