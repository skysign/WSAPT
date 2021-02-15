import java.io.*;
import java.util.*;

// BOJ 9376번 탈옥

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int H, W;
    char[][] map;
    Deque<int[]> queSangGeun;
    Deque<int[]> quePrisoner;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t=0; t<T; ++t) {
            st = new StringTokenizer(br.readLine());
            queSangGeun = new ArrayDeque<>();
            quePrisoner = new ArrayDeque<>();

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            for (int i=0; i<H; ++i) {
                String strLine = br.readLine();

                for (int j=0; j<W; ++j) {
                    map[i][j] = strLine.charAt(j);

                    if ((i==0) || (j==0) || (i==H-1) || (j==W-1)) {
                        if ((map[i][j] == '#') || (map[i][j] == '.')) {
                            queSangGeun.add(new int[]{i, j});
                        }
                    }

                    if (map[i][j] == '$') {
                        quePrisoner.add(new int[]{i, j});
                        map[i][j] = '.';
                    }
                } // for (int j=0; j<W; ++j) {
            } // for (int i=0; i<H; ++i) {

            int r = solve2();
            bw.write(String.valueOf(r));
            bw.newLine();
        }

        bw.close();
    }

    public int solve2() {
        int[][][] pPathes = new int[quePrisoner.size()][H][W];
        int[][][] sPathes = new int[queSangGeun.size()][H][W];

        int size = quePrisoner.size();
        for (int idx=0; idx<size; ++idx) {
            int[] yx = quePrisoner.pop();
            bfs_by_door_count(yx, pPathes[idx]);
        }

        size = queSangGeun.size();
        for (int idx=0; idx<size; ++idx) {
            int[] yx = queSangGeun.pop();
            bfs_by_door_count(yx, sPathes[idx]);
        }

        int r = Integer.MAX_VALUE;

        for (int idx=0; idx<sPathes.length; ++idx) {
            for (int i=0; i<H; ++i) {
                for (int j=0; j<W; ++j) {
                    if ((pPathes[0][i][j] != Integer.MAX_VALUE)
                            && (pPathes[1][i][j] != Integer.MAX_VALUE)
                            && (sPathes[idx][i][j] != Integer.MAX_VALUE)) {
                        int sum = pPathes[0][i][j] + pPathes[1][i][j] + sPathes[idx][i][j];

                        // 문이 있는 곳에서, 3명이 만났으면,
                        // 문의 수가 2번 중복해서 카운트 되었음
                        if (map[i][j] == '#') {
                            sum -= 2;
                        }
                        r = Math.min(r, sum);
                    }
                }
            }
        }

        if (r == Integer.MAX_VALUE) {
            r = 0;
        }

        return r;
    }

    private void bfs_by_door_count(int[] yx, int[][] pathes) {
        fill2D(pathes, Integer.MAX_VALUE);
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{yx[0], yx[1], 0});

        ArrayList<int[]> queTmp = new ArrayList<>();

        while (que.size() > 0) {
            int lengthQue = que.size();

            for (int idxQue=0; idxQue < lengthQue; ++idxQue) {
                int[] yxd = que.pop();
                int i = yxd[0];
                int j = yxd[1];
                int door = yxd[2];

                if (map[i][j] == '#') {
                    ++door;
                }

                pathes[i][j] = door;

                for (int idx=0; idx<d4i.length; ++idx) {
                    int ni = i + d4i[idx];
                    int nj = j + d4j[idx];

                    if (IsInMap(ni, nj)) {
                        if (map[ni][nj] == '.') {
                            if (door < pathes[ni][nj]) {
                                addQueueIfLessDoorPassed(queTmp, new int[]{ni, nj, door});
                            }
                        }
                        else if (map[ni][nj] == '#') {
                            if ((door +1) < pathes[ni][nj]) {
                                addQueueIfLessDoorPassed(queTmp, new int[]{ni, nj, door});
                            }
                        }
                    }
                }
            }

            for (int[] yxd: queTmp) {
                que.add(yxd);
            }

            queTmp.clear();
        }
    }

    public void addQueueIfLessDoorPassed(ArrayList<int[]> que, int[] newyxd) {
        int size = que.size();

        for (int i=0; i<size; ++i) {
            int[] yxd = que.get(i);

            if ((yxd[0] == newyxd[0]) && (yxd[1] == newyxd[1])) {
                if (yxd[2] > newyxd[2]) {
                    que.remove(i);
                    que.add(newyxd);
                    return;
                }
            }
        }

        que.add(newyxd);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    boolean IsInMap(int i, int j) {
        return ((0<=i) && (i<H) && (0<=j) && (j<W));
    }
}