import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * BOJ 16235번 나무 재테크
 *
 * 유튜브 문제 풀이: https://youtu.be/bi3eE28MY30
 *
 * 문제링크: https://www.acmicpc.net/problem/16235
 *
 * 자바소스: https://bit.ly/39F4Zkp
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, K;
    int[][] map;
    int[][] mapYangbun;

    ArrayDeque<Integer>[][] mapTree;
    ArrayList<Integer>[][] mapDeadTree;

    String[] strs;


    // 8 ways
    public int[] d8i = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    public int[] d8j = new int[]{0, 0, -1, 1, -1, 1, 1, -1};


    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        K = Integer.parseInt(strs[2]);

        map = new int[N + 2][N + 2];
        fill2D(map, 5);

        mapYangbun = new int[N + 2][N + 2];
        mapTree = new ArrayDeque[N + 2][N + 2];
        mapDeadTree = new ArrayList[N + 2][N + 2];

        //  X___X
        //  X___X
        //  X___X
        for (int i = 0; i < N + 2; ++i) {
            map[i][0] = -1;
            map[i][N + 1] = -1;
            mapYangbun[i][0] = 0;
            mapYangbun[i][N + 1] = 0;
        }

        //  _XXX_
        //  _____
        //  _XXX_
        for (int j = 1; j < N + 2; ++j) {
            map[0][j] = -1;
            map[N + 1][j] = -1;
            mapYangbun[0][j] = 0;
            mapYangbun[N + 1][j] = 0;
        }

        // N x N
        for (int _y = 0; _y < N; ++_y) {
            strs = br.readLine().split(" ");
            int y = _y + 1;

            for (int _x = 0; _x < N; ++_x) {
                int x = _x + 1;
                int v = Integer.parseInt(strs[_x]);

                mapYangbun[y][x] = v;
                mapTree[y][x] = new ArrayDeque<Integer>();
                mapDeadTree[y][x] = new ArrayList<Integer>();
            }
        }

        for (int m = 0; m < M; ++m) {
            strs = br.readLine().split(" ");
            int y = Integer.parseInt(strs[0]);
            int x = Integer.parseInt(strs[1]);
            int age = Integer.parseInt(strs[2]);

            mapTree[y][x].add(age);
        }


        // K 년도 동안 시간을 보내기
        for (int k = 0; k < K; ++k) {

            // 봄
            for (int y = 1; y < N + 1; ++y) {
                for (int x = 1; x < N + 1; ++x) {
                    ArrayDeque<Integer> al = mapTree[y][x];
                    ArrayDeque<Integer> tmpal = new ArrayDeque<Integer>();
                    int yangbun = map[y][x];

                    for (int treeAge : al) {
                        if (yangbun >= treeAge) {
                            yangbun -= treeAge;
                            treeAge++;
                            tmpal.add(treeAge);
                        } else {
                            mapDeadTree[y][x].add(treeAge);
                        }
                    }

                    map[y][x] = yangbun;
                    mapTree[y][x] = tmpal;
                }
            }

            // 여름
            for (int y = 1; y < N + 1; ++y) {
                for (int x = 1; x < N + 1; ++x) {
                    ArrayList<Integer> al = mapDeadTree[y][x];

                    for (int treeAge : al) {
                        map[y][x] += (treeAge / 2);
                    }

                    al.clear();
                }
            }

            // 가을
            for (int y = 1; y < N + 1; ++y) {
                for (int x = 1; x < N + 1; ++x) {
                    ArrayDeque<Integer> al = mapTree[y][x];

                    for (int treeAge : al) {
                        if (treeAge % 5 == 0) {
                            for (int idx = 0; idx < d8i.length; ++idx) {
                                int ty = y + d8i[idx];
                                int tx = x + d8j[idx];

                                if (map[ty][tx] != -1) {
                                    mapTree[ty][tx].addFirst(1);
                                }
                            }
                        }
                    }
                }
            }

            // 겨울
            for (int y = 1; y < N + 1; ++y) {
                for (int x = 1; x < N + 1; ++x) {
                    map[y][x] += mapYangbun[y][x];
                }
            }
        }


        int r = 0;

        for (int y = 1; y < N + 1; ++y) {
            for (int x = 1; x < N + 1; ++x) {
                r += mapTree[y][x].size();
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}