import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int cnt;
    String[] strs;
    int[][] map;
    boolean[][] mapApple;
    ArrayList<Integer> al;

    public int[] d4i = new int[]{-1, 0, 1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    static int UP = 0, RT = 1, DN = 2, LT = 3;
    static int L = 0, R = 1;

    int[][] dirs = new int[][]{{LT, RT, UP}, {UP, DN, RT}, {RT, LT, DN}, {DN, UP, LT}};

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 2][N + 2];
        mapApple = new boolean[N + 2][N + 2];

        fill2D(map, -1);
        for (int i = 1; i < N+1; ++i) {
            for (int j = 1; j < N+1; ++j) {
                map[i][j] = 0;
            }
        }

        cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; ++i) {
            strs = br.readLine().split(" ");
            int appleI = Integer.parseInt(strs[0]);
            int appleJ = Integer.parseInt(strs[1]);
            mapApple[appleI][appleJ] = true;
        }

        al = new ArrayList<>();
        cnt = Integer.parseInt(br.readLine());
        int sj = 0;
        int sV = R;

        for (int i = 0; i < cnt; ++i) {
            strs = br.readLine().split(" ");
            int second = Integer.parseInt(strs[0]);
            char c = strs[1].charAt(0);
            int v = ('L' == c) ? L : R;

            for (int j = sj; j < second; ++j) {
                if (j == sj) {
                    al.add(sV);
                } else {
                    al.add(2);
                }
            }

            sj = second;
            sV = v;
        }

        al.add(sV);

        for (int j = 0; j < N; ++j) {
            al.add(2);
        }

        int R = 0;
        int si = 1;
        sj = 1;

        map[1][1] = 1;
        int dir = UP;

        for (int r = 1; al.size() > 0; ++r) {
            dir = dirs[dir][al.get(0)];
            al.remove(0);

            int di = d4i[dir];
            int dj = d4j[dir];

            if (map[si + di][sj + dj] == 0) {
                if (mapApple[si + di][sj + dj]) {
                    do_d(map, 1);
                    mapApple[si + di][sj + dj] = false;
                }

                map[si + di][sj + dj] = map[si][sj] + 1;

                si = si + di;
                sj = sj + dj;

                do_d(map, -1);
            } else {
                R = r;
                break;
            }
        }

        bw.write(String.valueOf(R));
        bw.close();
    }

    void do_d(int[][] map, int delta) {
        for (int i = 1; i < N+1; ++i) {
            for (int j = 1; j < N+1; ++j) {
                if (map[i][j] > 0) {
                    map[i][j] += delta;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }
}