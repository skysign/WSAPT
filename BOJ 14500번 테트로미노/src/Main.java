import java.io.*;
import java.util.Arrays;
/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 14500번 테트로미노
 *
 * 유튜브 문제 풀이: https://youtu.be/zSWlY-4j9P8
 *
 * 문제링크: https://www.acmicpc.net/problem/14500
 *
 * 자바소스: https://bit.ly/3IuHw4W
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[][] tet1a = {{1, 1, 1, 1}};
    int[][] tet1b = {
            {1},
            {1},
            {1},
            {1}
    };

    int[][] tet2a = {
            {1, 1},
            {1, 1}
    };

    int[][] tet3a = {
            {1, 0},
            {1, 0},
            {1, 1}
    };

    int[][] tet3a1 = {
            {0, 1},
            {0, 1},
            {1, 1}
    };

    int[][] tet3b = {
            {1, 1, 1},
            {1, 0, 0}
    };

    int[][] tet3b1 = {
            {1, 0, 0},
            {1, 1, 1}
    };

    int[][] tet3c = {
            {1, 1},
            {0, 1},
            {0, 1}
    };

    int[][] tet3c1 = {
            {1, 1},
            {1, 0},
            {1, 0}
    };

    int[][] tet3d = {
            {0, 0, 1},
            {1, 1, 1}
    };

    int[][] tet3d1 = {
            {1, 1, 1},
            {0, 0, 1}
    };

    int[][] tet4a = {
            {1, 0},
            {1, 1},
            {0, 1}
    };

    int[][] tet4a1 = {
            {0, 1},
            {1, 1},
            {1, 0}
    };

    int[][] tet4b = {
            {0, 1, 1},
            {1, 1, 0}
    };

    int[][] tet4b1 = {
            {1, 1, 0},
            {0, 1, 1}
    };

    int[][] tet4c = {
            {0, 1},
            {1, 1},
            {1, 0}
    };

    int[][] tet4c1 = {
            {1, 0},
            {1, 1},
            {0, 1}
    };

    int[][] tet4d = {
            {1, 1, 0},
            {0, 1, 1}
    };

    int[][] tet4d1 = {
            {0, 1, 1},
            {1, 1, 0}
    };

    int[][] tet5a = {
            {1, 1, 1},
            {0, 1, 0}
    };

    int[][] tet5a1 = {
            {0, 1, 0},
            {1, 1, 1}
    };

    int[][] tet5b = {
            {0, 1},
            {1, 1},
            {0, 1}
    };

    int[][] tet5b1 = {
            {1, 0},
            {1, 1},
            {1, 0}
    };

    int[][] tet5c = {
            {0, 1, 0},
            {1, 1, 1}
    };

    int[][] tet5c1 = {
            {1, 1, 1},
            {0, 1, 0}
    };

    int[][] tet5d = {
            {1, 0},
            {1, 1},
            {1, 0}
    };

    int[][] tet5d1 = {
            {0, 1},
            {1, 1},
            {0, 1}
    };

    int[][][] tet1 = {tet1a, tet1b};
    int[][][] tet2 = {tet2a};
    int[][][] tet3 = {tet3a, tet3b, tet3c, tet3d, tet3a1, tet3b1, tet3c1, tet3d1};
    int[][][] tet4 = {tet4a, tet4b, tet4c, tet4d, tet4a1, tet4b1, tet4c1, tet4d1};
    int[][][] tet5 = {tet5a, tet5b, tet5c, tet5d, tet5a1, tet5b1, tet5c1, tet5d1};

    int[][][][] tets = {tet1, tet2, tet3, tet4, tet5};

    int N, M;
    String[] strs;
    int[][] mapScore;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);

        mapScore = new int[N+3][M+3];
        fill2D(mapScore, -1);

        for (int n = 0; n < N; ++n) {
            strs = br.readLine().split(" ");

            for (int m = 0; m < M; ++m) {
                mapScore[n][m] = Integer.parseInt(strs[m]);
            }
        }

        int r = findBestScore();

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public int findBestScore() {
        int r = 0;
        int tmpr = 0;

        for (int idxTets = 0; idxTets < tets.length; idxTets++) {
            for (int idxTetN = 0; idxTetN < tets[idxTets].length; ++idxTetN) {
                for (int posI = 0; posI < N; ++posI) {
                    for (int posJ = 0; posJ < M; ++posJ) {
                        tmpr = placeTet(posI, posJ, tets[idxTets][idxTetN]);
                        if (tmpr > 0) {
                            r = Math.max(r, tmpr);
                        }
                    }
                }
            }
        }

        return r;
    }

    private int placeTet(int sI, int sJ, int[][] tetnx) {
        int r = 0;

        for (int i = 0; i < tetnx.length; ++i) {
            for (int j = 0; j < tetnx[0].length; ++j) {
                if (tetnx[i][j] == 1) {
                    if (mapScore[i+sI][j+sJ] == -1)
                        return -1;

                    r += mapScore[i+sI][j+sJ];
                }
            }
        }

        return r;
    }

    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}