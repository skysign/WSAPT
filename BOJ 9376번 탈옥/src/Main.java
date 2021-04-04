import java.io.*;
import java.util.*;
/**
 * BOJ 9376번 탈옥
 *
 * 유튜브 문제 풀이 : https://youtu.be/teJT9JX5oL0
 *
 * 문제링크 : https://www.acmicpc.net/problem/9376
 *
 * 자바소스 : https://bit.ly/3pCPKh6
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int H, W;
    char[][] map;
    ArrayList<int[]> quePrisoner;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t=0; t<T; ++t) {
            st = new StringTokenizer(br.readLine());
            quePrisoner = new ArrayList<>();
            quePrisoner.add(new int[]{0, 0});

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H+2][W+2];

            fill2D(map, '.');

            for (int i=0; i<H; ++i) {
                String strLine = br.readLine();

                for (int j=0; j<W; ++j) {
                    map[i+1][j+1] = strLine.charAt(j);

                    if (map[i+1][j+1] == '$') {
                        quePrisoner.add(new int[]{i+1, j+1});
                        map[i+1][j+1] = '.';
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
        int[][][] pPathes = new int[quePrisoner.size()][H+2][W+2];
        int r = Integer.MAX_VALUE;

        for (int idx=0; idx<quePrisoner.size(); ++idx) {
            int[] yx = quePrisoner.get(idx);
            bfs_by_door_count(yx, pPathes[idx]);
        }

        for (int i=1; i<H+2; ++i) {
            for (int j=1; j<W+2; ++j) {
                if ((map[i][j] == '#') || (map[i][j] == '.')) {
                    int sum = pPathes[0][i][j] + pPathes[1][i][j] + pPathes[2][i][j];

                    // 문이 있는 곳에서, 3명이 만났으면,
                    // 문의 수가 2번 중복해서 카운트 되었음
                    if (map[i][j] == '#') {
                        sum -= 2;
                    }

                    r = Math.min(r, sum);

                    if (sum == r) {
                        r = r;
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
        que.add(new int[]{yx[0], yx[1]});
        pathes[yx[0]][yx[1]] = 0;

        ArrayList<int[]> queTmp = new ArrayList<>();

        while (que.size() > 0) {
            int lengthQue = que.size();

            for (int idxQue=0; idxQue < lengthQue; ++idxQue) {
                int[] yxd = que.pop();
                int i = yxd[0];
                int j = yxd[1];

                for (int idx=0; idx<d4i.length; ++idx) {
                    int ni = i + d4i[idx];
                    int nj = j + d4j[idx];

                    if (IsInMap(ni, nj)) {
                        if (map[ni][nj] == '.') {
                            if (pathes[i][j] < pathes[ni][nj]) {
                                pathes[ni][nj] = pathes[i][j];
                                if (!IsContained(queTmp, ni, nj)) {
                                    queTmp.add(new int[]{ni, nj});
                                }
                            }
                        }
                        else if (map[ni][nj] == '#') {
                            if ((pathes[i][j] +1) < pathes[ni][nj]) {
                                pathes[ni][nj] = pathes[i][j] +1;
                                if (!IsContained(queTmp, ni, nj)) {
                                    queTmp.add(new int[]{ni, nj});
                                }
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

    boolean IsContained(ArrayList<int[]> que, int ni, int nj) {
        for (int[] yxd: que) {
            if ((yxd[0] == ni) && (yxd[1] == nj)) {
                return true;
            }
        }

        return false;
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

    public void fill2D(char[][] _2D, char v) {
        for(char[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    boolean IsInMap(int i, int j) {
        return ((0<=i) && (i<H+2) && (0<=j) && (j<W+2));
    }
}