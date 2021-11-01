import java.io.*;
import java.util.*;

/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 1753번 최단경로
 *
 * 유튜브 문제 풀이: https://youtu.be/Ks4Ox0jgOPQ
 *
 * 문제링크: https://www.acmicpc.net/problem/19238
 *
 * 자바소스: https://bit.ly/3nPAjmy
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, mFuel;
    String[] strs;
    int[][] map;
    int[][] path;
    int[][] mapPsng;
    int[][] psng;
    int[][] mapDst = new int[N+1][N+1];

    int sTaxiX;
    int sTaxiY;

    int idxPsng;
    int psngDist;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        mFuel = Integer.parseInt(strs[2]);

        map = new int[N+1][N+1];
        path = new int[N+1][N+1];
        mapPsng = new int[N+1][N+1];
        mapDst = new int[N+1][N+1];

        for (int i=1; i<=N; ++i) {
            strs = br.readLine().split(" ");
            for (int j=0; j<N; ++j) {
                map[i][j+1] = Integer.parseInt(strs[j]);
            }
        }

        strs = br.readLine().split(" ");
        sTaxiY = Integer.parseInt(strs[0]);
        sTaxiX = Integer.parseInt(strs[1]);

        fill2D(mapPsng, -1);
        psng = new int[M][2];

        for (int i=0; i<M; ++i) {
            strs = br.readLine().split(" ");
            int frY = Integer.parseInt(strs[0]);
            int frX = Integer.parseInt(strs[1]);
            int toY = Integer.parseInt(strs[2]);
            int toX = Integer.parseInt(strs[3]);

            mapPsng[frY][frX] = i;
            psng[i][0] = toY;
            psng[i][1] = toX;
        }

        int r = 0;

        ArrayList<int[]> al = new ArrayList<>();
        al.add(new int[]{sTaxiY, sTaxiX});

        while (al.size() > 0) {
            int[] yx = al.get(0);
            al.remove(0);
            int y = yx[0];
            int x = yx[1];

            int[] rlt = floodFill(y, x, mapPsng, psng);

            if (rlt == null) {
                r = -1;
                break;
            }

            int dist = rlt[2];
            mFuel -= dist;
            if (mFuel < 0) {
                r = -1;
                break;
            }

            fill2D(mapDst, -1);
            mapDst[rlt[0]][rlt[1]] = 0;
            int[][] dsts = new int[][]{{rlt[0], rlt[1]}};
            int psgnY = rlt[3];
            int psgnX = rlt[4];

            rlt = floodFill(psgnY, psgnX, mapDst, dsts);
            if (rlt == null) {
                r = -1;
                break;
            }

            dist = rlt[2];
            mFuel -= dist;
            if (mFuel < 0) {
                r = -1;
                break;
            }

            mFuel += (dist *2);
            r = mFuel;

            M--;
            if (M > 0) {
                al.add(new int[]{rlt[0], rlt[1]});
            }
        }

        bw.write(String.valueOf(r));
        bw.close();
    }

    int[] floodFill(int frY, int frX, int[][] mapDst, int[][] dsts) {
        fill2D(path, -1);
        ArrayList<int[]> alDst = new ArrayList<>();

        ArrayList<int[]> al = new ArrayList<>();
        al.add(new int[]{frY, frX});
        path[frY][frX] = 0;

        while (al.size() > 0) {
            for (int i=al.size(); i>0; --i) {
                int[] yx = al.get(0);
                al.remove(0);
                int y = yx[0];
                int x = yx[1];
                int frDist = path[y][x];

                if (mapDst[y][x] != -1) {
                    int idx = mapDst[y][x];
                    alDst.add(new int[]{dsts[idx][0], dsts[idx][1], path[y][x], y, x, mapDst[y][x]});
                    mapDst[y][x] = -1;
                }

                for (int j=0; j<d4i.length; ++j) {
                    int ny = d4i[j] +y;
                    int nx = d4j[j] +x;

                    if (!IsIn(ny, nx))
                        continue;

                    if (map[ny][nx] != 0)
                        continue;

                    if (path[ny][nx] == -1) {
                        path[ny][nx] = frDist +1;

                        if (mapDst[ny][nx] != -1) {
                            int idx = mapDst[ny][nx];
                            alDst.add(new int[]{dsts[idx][0], dsts[idx][1], path[ny][nx], ny, nx, mapDst[ny][nx]});
                            mapDst[ny][nx] = -1;
                        }

                        al.add(new int[]{ny, nx});
                    }
                }
            }
        }

        if (alDst.size() > 0) {
            Collections.sort(alDst,
                    new Comparator<int[]>() {
                        @Override
                        public int compare(int[] o1, int[] o2) {
                            if (o1[2] == o2[2]) {
                                if (o1[3] == o2[3]) {
                                    if (o1[4] == o2[4]) {
                                        return 0;
                                    }
                                    else if (o1[4] < o2[4]) {
                                        return -1;
                                    }
                                    else {
                                        return 1;
                                    }
                                }
                                else if (o1[3] < o2[3]) {
                                    return -1;
                                }
                                else {
                                    return 1;
                                }
                            }
                            else if (o1[2] < o2[2]) {
                                return -1;
                            }

                            return 1;
                        }
                    });

            int[] rtn = alDst.get(0);
            alDst.remove(0);

            for (int[] yx: alDst) {
                int y = yx[3];
                int x = yx[4];
                int idx = yx[5];
                mapDst[y][x] = idx;
            }

            alDst.clear();
            al.clear();

            return rtn;
        }

        alDst.clear();
        al.clear();

        return null;
    }

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    boolean IsIn(int y, int x) {
        return ((0<y) && (y<=N) && (0<x) && (x<=N));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}