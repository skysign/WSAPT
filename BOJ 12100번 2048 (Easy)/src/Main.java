/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 12100번 2048 (Easy)
 *
 * 유튜브 문제 풀이: https://youtu.be/gniZkicBGkA
 *
 * 문제링크: https://www.acmicpc.net/problem/12100
 *
 * 자바소스: https://bit.ly/3qT9p0x
 */
import java.io.*;
import java.util.ArrayList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    static int MAX_DEPTH = 5;
    static int UP = 0, RT = 1, DN = 2, LT = 3;
    int[] DIR = new int[]{UP, RT, DN, LT};
    int[][] dt;
    String[] strs;
    ArrayList<Integer> al = new ArrayList<>();

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        dt = new int[N][N];

        for (int i = 0; i < N; ++i) {
            strs = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                dt[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int r = pan(dt, 0);

        bw.write(String.valueOf(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public int pan(int[][] map, int depth) {
        if (depth >= MAX_DEPTH)
            return -1;

        int r = 0;

        for (int idxDir = 0; idxDir < DIR.length; ++idxDir) {
            int[][] clonedMap = clone2D(map);
            move(clonedMap, DIR[idxDir]);

            int t = findMaxInMap(clonedMap);
            r = Math.max(r, t);
            t = pan(clonedMap, depth +1);
            r = Math.max(r, t);
        }

        return r;
    }

    public void move(int[][] map, int dir) {
        // (i,j)
        // (0,0) (0,n)
        // (n,0) (n,n)

        // UP / DN 컬럼 방향 merge
        if (UP == dir) {
            for (int j = 0; j < N; ++j) {
                for (int i = 0; i < N; ++i) {
                    if (map[i][j] > 0) {
                        al.add(map[i][j]);
                        map[i][j] = 0;
                    }
                }

                merge(map, 0, j, 1, 0);
            }
        } else if (DN == dir) {
            for (int j = 0; j < N; ++j) {
                for (int i = N - 1; i >= 0; --i) {
                    if (map[i][j] > 0) {
                        al.add(map[i][j]);
                        map[i][j] = 0;
                    }
                }

                merge(map, N - 1, j, -1, 0);
            }
        } else if (RT == dir) {
            for (int i = 0; i < N; ++i) {
                for (int j = N - 1; j >= 0; --j) {
                    if (map[i][j] > 0) {
                        al.add(map[i][j]);
                        map[i][j] = 0;
                    }
                }

                merge(map, i, N-1, 0, -1);
            }
        } else { // LT
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (map[i][j] > 0) {
                        al.add(map[i][j]);
                        map[i][j] = 0;
                    }
                }

                merge(map, i, 0, 0, 1);
            }
        }
    }

    void merge(int[][] map, int bi, int bj, int di, int dj) {
        int i = bi;
        int j = bj;

        while (al.size() > 0) {
            int v = al.get(0);
            al.remove(0);

            if (map[i][j] == 0) {
                map[i][j] = v;
            } else if (map[i][j] == v) {
                map[i][j] += v;
                i += di;
                j += dj;
            }
            else {
                i += di;
                j += dj;
                map[i][j] = v;
            }
        }
    }

    public int findMaxInMap(int[][] map) {
        int r = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                r = Math.max(r, map[i][j]);
            }
        }

        return r;
    }

    public int[][] clone2D(int[][] src) {
        int row = src.length;
        int col = src[0].length;
        int[][] dst = new int[row][col];

        for (int i = 0; i < row; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, col);
        }

        return dst;
    }
}