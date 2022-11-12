import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
/**
 * 백준 23290번 마법사 상어와 복제
 *
 * 유튜브 문제 풀이: https://youtu.be/ySnaN5QDS10
 *
 * 문제링크: https://www.acmicpc.net/problem/23290
 *
 * 자바소스: http://bit.ly/3fXmTVO
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N = 4;
    int M, S;
    String[] strs;

    static int _x = 0, LT = 1, LU = 2, UP = 3, RU = 4, RT = 5, RD = 6, DN = 7, LD = 8;
    int[] fishDirs = {
            // _x ←, ↖, ↑, ↗, →, ↘, ↓, ↙
            _x, LT, LU, UP, RU, RT, RD, DN, LD
    };
    int[] dR = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    int[] dC = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    // 상은 1, 좌는 2, 하는 3, 우는 4
    int[] sharkDirs = {1, 2, 3, 4};
    int[][] sharkRC = {null, {-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int sharkR, sharkC;

    int[][] mapSmell = new int[4][4];
    int[][] mapAte = new int[4][4];
    int[][] map = mapSmell;
    int[] minPath = {5, 5, 5, 0};
    int[] maxPath = minPath;

    HashMap<Integer, Integer>[][] mapFishes = new HashMap[4][4];
    HashMap<Integer, Integer>[][] mapClonedFishes = new HashMap[4][4];
    HashMap<Integer, Integer>[][] mapMovingFishes = new HashMap[4][4];

    public void solve() throws IOException {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                mapFishes[i][j] = new HashMap<>();
                mapClonedFishes[i][j] = new HashMap<>();
                mapMovingFishes[i][j] = new HashMap<>();
            }
        }

        strs = br.readLine().split(" ");
        M = Integer.parseInt(strs[0]);
        S = Integer.parseInt(strs[1]);

        for (int m = 0; m < M; ++m) {
            strs = br.readLine().split(" ");
            int r = Integer.parseInt(strs[0]) - 1;
            int c = Integer.parseInt(strs[1]) - 1;
            int fishDir = Integer.parseInt(strs[2]);

            if (!mapFishes[r][c].containsKey(fishDir)) {
                mapFishes[r][c].put(fishDir, 0);
            }

            mapFishes[r][c].put(fishDir, mapFishes[r][c].get(fishDir) + 1);
        }

        strs = br.readLine().split(" ");
        sharkR = Integer.parseInt(strs[0]) - 1;
        sharkC = Integer.parseInt(strs[1]) - 1;

        for (int s = 0; s < S; ++s) {
            // 물고기의 이동 시작
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (mapFishes[i][j].size() == 0)
                        continue;

                    mapClonedFishes[i][j].putAll(mapFishes[i][j]);

                    int sr = i;
                    int sc = j;

                    Integer[] dirs = mapFishes[i][j].keySet().toArray(new Integer[0]);

                    for (int fishDir : dirs) {
                        int newDir = findFishDir(sr, sc, fishDir);
                        int nr = sr + dR[newDir];
                        int nc = sc + dC[newDir];
                        int count = mapFishes[i][j].get(fishDir);

                        // newDir(0)이면 이동할 수 있는 칸이 없다.
                        // 이동은 하지 안하고, 방향만 남겨둔다.
                        if (newDir == 0) {
                            newDir = fishDir;
                        }

                        if (!mapMovingFishes[nr][nc].containsKey(newDir)) {
                            mapMovingFishes[nr][nc].put(newDir, 0);
                        }

                        mapMovingFishes[nr][nc].put(newDir, mapMovingFishes[nr][nc].get(newDir) + count);
                    }

                    mapFishes[i][j].clear();
                }
            }

//            // 물고기 위치 출력 용도
//            for (int i = 0; i < N; ++i) {
//                for (int j = 0; j < N; ++j) {
//                    if (mapMovingFishes[i][j].size() > 0) {
//                        Integer[] dirs = mapMovingFishes[i][j].keySet().toArray(new Integer[0]);
//
//                        for (int dir : dirs) {
//                            bw.write(String.format("Fish %d,%d dir(%d) cnt(%d)", i, j, dir, mapMovingFishes[i][j].get(dir)));
//                            bw.newLine();
//                        }
//                    }
//                }
//            }

            // 냄새 1씩 감소
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (mapSmell[i][j] > 0) {
                        --mapSmell[i][j];
                    }
                }
            }

            // 상어가 최대한 물고기를 많이 먹을 수 있는 경로 탐색
            fill2D(mapAte, 0);
            maxPath = Arrays.copyOf(minPath, minPath.length);

            findPathOfShark(sharkR, sharkC, 0, maxPath);

            // 상어가 물고기 잡아 먹기
            for (int idx = 0; idx < 3; ++idx) {
                int dir = maxPath[idx];
//                bw.write(String.format("shark dir %d\n", dir));
//                bw.flush();
                sharkR += sharkRC[dir][0];
                sharkC += sharkRC[dir][1];

                if (mapMovingFishes[sharkR][sharkC].size() > 0) {
                    mapSmell[sharkR][sharkC] = 2;
                    mapMovingFishes[sharkR][sharkC].clear();
                }
            }

            // 물고기 복사
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (mapClonedFishes[i][j].size() > 0) {
                        mapFishes[i][j].putAll(mapClonedFishes[i][j]);
                        mapClonedFishes[i][j].clear();
                    }

                    if (mapMovingFishes[i][j].size() > 0) {
                        Integer[] dirs = mapMovingFishes[i][j].keySet().toArray(new Integer[0]);

                        for (int dir : dirs) {
                            if (!mapFishes[i][j].containsKey(dir)) {
                                mapFishes[i][j].put(dir, 0);
                            }

                            mapFishes[i][j].put(dir, mapFishes[i][j].get(dir) + mapMovingFishes[i][j].get(dir));
                        }

                        mapMovingFishes[i][j].clear();
                    }
                }
            }

//            print2D(mapSmell);
//            bw.write(String.format("shark %d,%d\n", sharkR, sharkC));
//            bw.write(String.format("%d\n\n", countFishes()));
//            bw.flush();
        }

        int ans = countFishes();

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    int getCount(HashMap<Integer, Integer> hm) {
        int rlt = 0;
        Integer[] dirs = hm.keySet().toArray(new Integer[0]);

        for (int dir : dirs) {
            rlt += hm.get(dir);
        }

        return rlt;
    }

    int countFishes() throws IOException {
        int rlt = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                Integer[] dirs = mapFishes[i][j].keySet().toArray(new Integer[0]);
                for (int dir : dirs) {
                    rlt += mapFishes[i][j].get(dir);
                }

//                bw.write(String.format(" [%d,%d] %6d", i, j, getCount(mapFishes[i][j])));
            }

//            bw.newLine();
        }

        return rlt;
    }

    void findPathOfShark(int sr, int sc, int depth, int[] path) {
        if (depth > 2) {
            comparePathes(path);
            return;
        }

        for (int dir : sharkDirs) {
            boolean bAte = false;
            int nr = sr + sharkRC[dir][0];
            int nc = sc + sharkRC[dir][1];

            if (!IsIn(map, nr, nc))
                continue;

            int[] newPath = Arrays.copyOf(path, path.length);
            newPath[depth] = dir;

            if (mapAte[nr][nc] == 0) {
                newPath[3] += getCount(mapMovingFishes[nr][nc]);
                mapAte[nr][nc] = 1;
                bAte = true;
            }

            findPathOfShark(nr, nc, depth + 1, newPath);

            if (bAte) {
                bAte = false;
                mapAte[nr][nc] = 0;
            }
        }
    }

    private void comparePathes(int[] path) {
        // 물고기 수 비교
        if (maxPath[3] < path[3]) {
            maxPath = path;
            return;
        }

        if (maxPath[3] > path[3])
            return;
        // 물고가 수 같음,
        // 첫번째 칸이 작은 것 찾기
        if (maxPath[0] < path[0]) {
            return;
        }

        if (maxPath[0] > path[0]) {
            maxPath = path;
            return;
        }

        // 두번째 칸이 작은 것 찾기
        if (maxPath[1] < path[1]) {
            return;
        }

        if (maxPath[1] > path[1]) {
            maxPath = path;
            return;
        }

        // 세번째 칸이 작은 것 찾기
        if (maxPath[2] < path[2]) {
            return;
        }

        if (maxPath[2] > path[2]) {
            maxPath = path;
            return;
        }
    }

    int findFishDir(int sr, int sc, int fishDir) {
        for (int dir = fishDir; dir > 0; --dir) {
            int nr = sr + dR[dir];
            int nc = sc + dC[dir];

            if ((sharkR == nr) && (sharkC == nc))
                continue;

            if (IsIn(map, nr, nc)) {
                if (mapSmell[nr][nc] == 0) {
                    return dir;
                }
            }
        }

        for (int dir = LD; dir > fishDir; --dir) {
            int nr = sr + dR[dir];
            int nc = sc + dC[dir];

            if ((sharkR == nr) && (sharkC == nc))
                continue;

            if (IsIn(map, nr, nc)) {
                if (mapSmell[nr][nc] == 0) {
                    return dir;
                }
            }
        }

        return 0;
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    boolean IsIn(int[][] map, int r, int c) {
        if ((0 <= r) && (r < map.length))
            if ((0 <= c) && (c < map[0].length))
                return true;

        return false;
    }

    void print2D(int[][] map) throws IOException {
        for (int r = 0; r < map.length; ++r) {
            for (int c = 0; c < map[0].length; ++c) {
                String str = String.format(" [%d,%d] %2d ", r, c, map[r][c]);
                bw.write(str);
            }
            bw.newLine();
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}