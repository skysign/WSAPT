import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int EMPTY = 0;
    static int WALL = 1;
    static int VIRUS = 2;

    int[][] map;
    boolean[][] visited;
    ArrayList<int []> alEmpty;
    ArrayList<int []> alVirus;
    int N, M;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        alEmpty = new ArrayList<>();
        alVirus = new ArrayList<>();

        for (int i = 0; i<N; ++i) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j<M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != EMPTY) {
                    visited[i][j] = true;

                    if (map[i][j] == VIRUS)
                        alVirus.add(new int[]{i, j});
                }
                else { // v == VIRUS
                    alEmpty.add(new int[]{i, j});
                }
            }
        }

        int r = solve2();

        bw.write(String.valueOf(r));
        bw.close();
    }

    int solve2() {
        // 전체 빈 공간중에 3개를 고르기
        // alEmpty에서 길이 3인, combination 구하기

        // alEmpty사용해서, combination을 직접 구할 수없어서
        // 0, 1, 2, ... N-1 까지 배열인 dt를 사용함
        int[] dt = new int[alEmpty.size()];
        for (int i=0; i<alEmpty.size(); ++i)
            dt[i] = i;

        boolean[] visited = new boolean[alEmpty.size()];
        ArrayList<int[]> alComb = new ArrayList<>();
        // 배열 dt, dt의 길이는 alEmpty.size()
        // 길이 3인 combination을 모두 찾는다
        combination(dt, visited, 0, alEmpty.size(), 3, 3, alComb);

        ArrayList<int[][]> alCombOfEmpty = new ArrayList<>();

        for (int[] combOfIdx: alComb) {
            int[] a0 = alEmpty.get(combOfIdx[0]);
            int[] a1 = alEmpty.get(combOfIdx[1]);
            int[] a2 = alEmpty.get(combOfIdx[2]);

            alCombOfEmpty.add(new int[][]{{a0[0], a0[1]}, {a1[0], a1[1]}, {a2[0], a2[1]}});
        }

        int r = Integer.MIN_VALUE;

        for (int[][] wall3: alCombOfEmpty) {
            int tmp = BFS_virus_spread_return_safe_area(wall3);
            r = Math.max(r, tmp);
        }
        
        return r;
    }

    void combination(int[] arr, boolean[] visited, int start, int n, int r, int R, ArrayList<int[]> al) {
        if (r == 0) {
            print(arr, visited, n, R, al);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1, R, al);
            visited[i] = false;
        }
    }

    void print(int[] arr, boolean[] visited, int n, int R, ArrayList<int[]> al) {
        int[] comb = new int[R];

        for (int i = 0, idx = 0; i < n; i++) {
            if (visited[i] == true) {
//                System.out.print(arr[i] + " ");
                comb[idx] = arr[i];
                idx++;
            }
        }

        al.add(comb);
//        System.out.println();
    }

    int BFS_virus_spread_return_safe_area(int[][] wall3) {
        Deque<int []> que = new ArrayDeque<>();

        for (int[] yx: alVirus)
            que.add(yx);

        int[][] vmap = clone2D(map);

        for (int[] yx: wall3) {
            int y = yx[0];
            int x = yx[1];
            vmap[y][x] = WALL;
        }

        while (que.size() > 0) {
            int[] yx = que.pop();
            int y = yx[0];
            int x = yx[1];

            for (int idx=0; idx<d4i.length; ++idx) {
                int dy = y + d4i[idx];
                int dx = x + d4j[idx];

                if (IsInMap(dy, dx) && IsEmpty(dy, dx, vmap)) {
                    vmap[dy][dx] = VIRUS;
                    que.add(new int[]{dy, dx});
                }
            }
        }

        // 안전지역 개수 세기
        int r = 0;

        for (int i=0; i<N; ++i) {
            for (int j=0; j<M; ++j) {
                if (vmap[i][j] == EMPTY)
                    ++r;
            }
        }

        return r;
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    boolean IsInMap(int y, int x) {
        return (0<=y) && (y<N) && (0<=x) && (x<M);
    }

    boolean IsEmpty(int y, int x, int[][] vmap) {
        return (vmap[y][x] == 0);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
   }

    public int[][] clone2D(int[][] src) {
        int row = src.length;
        int col = src[0].length;
        int[][] dst = new int[row][col];

        for (int i=0; i<row; i++) {
            System.arraycopy(src[i],0, dst[i],0, col);
        }

        return dst;
    }
}