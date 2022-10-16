import java.io.*;
import java.util.HashMap;

/**
 * BOJ 19237번 어른 상어
 *
 * 유튜브 문제 풀이: https://youtu.be/iRfjDgcPPAo
 *
 * 문제링크: https://www.acmicpc.net/problem/19237
 *
 * 자바소스: https://bit.ly/3D3yVpz
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 문제 풀이에 중요한 변수들
    int N, M, K;

    int[][] dRC = new int[][]{
            {0, 0},

            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    int[][][] dirSharks;

    class Shark {
        public int mM;
        public int mDir;
        public int mR;
        public int mC;

        public Shark(int m, int dir, int r, int c) {
            mM = m;
            mDir = dir;
            mR = r;
            mC = c;
        }

        public void gone(int second) {
            sharks[mM] = null;
        }
    }

    Shark[] sharks;

    static int MAX_SECONDS_1000 = 1000;
    static int R = 0;
    static int C = 1;
    static int DIR = 2;

    int[][] map;
    int[][] mapSmell;
    int[][] mapSecond;

    HashMap<Integer, int[]> hm = new HashMap<>();

    // 중요하지 않은 변수들
    String[] strs;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        K = Integer.parseInt(strs[2]);

        sharks = new Shark[M + 1];
        map = new int[N + 2][N + 2];
        mapSmell = new int[N + 2][N + 2];
        mapSecond = new int[N + 2][N + 2];

        // 상어가 이동할 맵을 읽는다
        for (int r = 0; r < N; ++r) {
            strs = br.readLine().split(" ");

            for (int c = 0; c < N; ++c) {
                int m = Integer.parseInt(strs[c]);

                if (m != 0) {
                    sharks[m] = new Shark(m, -1, r + 1, c + 1);
                    mapSmell[r + 1][c + 1] = m;
                    mapSecond[r + 1][c + 1] = K;
                }
            }
        }

        strs = br.readLine().split(" ");

        // 각 상어의 처음 이동 방향
        for (int s = 0; s < M; ++s) {
            int dir = Integer.parseInt(strs[s]);
            sharks[s + 1].mDir = dir;
        }

        // 각 상어별 이동 우선 순위
        dirSharks = new int[M + 1][4 + 1][4];

        for (int s = 1; s <= M; ++s) {
            for (int i = 1; i <= 4; ++i) {
                strs = br.readLine().split(" ");

                for (int j = 0; j < 4; ++j) {
                    int v = Integer.parseInt(strs[j]);
                    dirSharks[s][i][j] = v;
                }
            }
        }

        // 맵의 테두리는 이동 불가 지역으로 -1 표시함
        for (int r = 0; r < map.length; ++r) {
            map[r][0] = -1;
            map[r][map[0].length - 1] = -1;
        }

        for (int c = 0; c < map[0].length; ++c) {
            map[0][c] = -1;
            map[map.length - 1][c] = -1;
        }
        // 끝

        // 시작 부터 1번 상어 밖에 없는 경우
        if (isEnd()) {
            bw.write(String.valueOf(0));
            System.exit(0);
        }

        // 최대 1000초 동안 이동 시작
        for (int second = 0; second < MAX_SECONDS_1000; ++second) {
            // 상어가 이동할 위치 찾기
            for (int idxShark = 1; idxShark <= M; ++idxShark) {
                if (sharks[idxShark] == null)
                    continue;

                int[] rc = moveSharkToEmpty(idxShark);

                if (rc == null) {
                    rc = moveSharkToMySmell(idxShark);
                }

                // idxShark 상어 이동할 곳이 없음
                if (rc == null) {
                    sharks[idxShark].gone(second);
                    sharks[idxShark] = null;
                    hm.put(idxShark, null);
                }
                else {
                    hm.put(idxShark, rc);
                }
            }
            
            // 1초가 지났으므로, 시간 감소,
            // 0초가 되면, 냄세 지우기
            for (int r = 1; r <=N; ++r) {
                for (int c = 1; c <=N; ++c) {
                    if (mapSecond[r][c] == 0)
                        continue;

                    --mapSecond[r][c];

                    if (mapSecond[r][c] == 0) {
                        mapSmell[r][c] = 0;
                    }
                }
            }
            
            // 상어가 새 위치로 이동
            for (int idxShark = 1; idxShark <= M; ++idxShark) {
                if (sharks[idxShark] == null)
                    continue;

                Shark shark = sharks[idxShark];
                map[shark.mR][shark.mC] = 0;

                int[] rc = hm.get(idxShark);

                shark.mR = rc[R];
                shark.mC = rc[C];
                shark.mDir = rc[DIR];

                // 이동하려는 위치에 다른 상어가 있음
                if (map[shark.mR][shark.mC] != 0) {
                    // 2개의 상어중에, 누가 번호가 작은가?
                    int dstM = map[shark.mR][shark.mC];

                    // 이동하려는 위치에 있는 상어가 번호가 작음
                    if (dstM < shark.mM) {
                        shark.gone(second);
                    }
                    else { // 이동하는 상어의 번호가 작음
                        sharks[dstM].gone(second);
                        map[shark.mR][shark.mC] = shark.mM;
                        mapSmell[shark.mR][shark.mC] = shark.mM;
                        mapSecond[shark.mR][shark.mC] = K;
                    }
                }
                else {// 이동하려는 위치가 빈곳
                    map[shark.mR][shark.mC] = shark.mM;
                    mapSmell[shark.mR][shark.mC] = shark.mM;
                    mapSecond[shark.mR][shark.mC] = K;
                }
            }

            if (isEnd()) {
                bw.write(String.valueOf(second + 1));
                bw.newLine();
                bw.flush();
                System.exit(0);
            }
        }

        // 1000초가 넘는 경우
        bw.write(String.valueOf(-1));
        bw.newLine();
        bw.close();
    }

    private boolean isEnd() {
        for (int idxShark = 2; idxShark <= M; ++idxShark) {
            if (sharks[idxShark] != null)
                return false;
        }

        return true;
    }

    public int[] moveSharkToEmpty(int idxShark) {
        Shark shark = sharks[idxShark];
        int[] dirShark = dirSharks[idxShark][shark.mDir];

        for (int dir: dirShark) {
            int dstR = shark.mR + dRC[dir][0];
            int dstC = shark.mC + dRC[dir][1];

            if (map[dstR][dstC] == 0 && mapSmell[dstR][dstC] == 0) {
                return new int[]{dstR, dstC, dir};
            }
        }

        return null;
    }

    public int[] moveSharkToMySmell(int idxShark) {
        Shark shark = sharks[idxShark];
        int[] dirShark = dirSharks[idxShark][shark.mDir];

        for (int dir: dirShark) {
            int dstR = shark.mR + dRC[dir][0];
            int dstC = shark.mC + dRC[dir][1];

            if (mapSmell[dstR][dstC] == shark.mM) {
                return new int[]{dstR, dstC, dir};
            }
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}