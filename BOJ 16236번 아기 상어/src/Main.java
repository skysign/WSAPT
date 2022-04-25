import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;
    int[][] map;
    int[][] path;
    int sI, sJ;
    int sharkSize = 2;
    int eat = 0;
    ArrayList<Integer[]> al = new ArrayList<>();
    ArrayList<Integer[]> alCandidate = new ArrayList<>();

    static int Y = 0;
    static int X = 1;
    static int Time = 2;
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};
    int r = 0;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 2][N + 2];
        fill2D(map, -1);
        path = new int[N + 2][N + 2];

        for (int i = 0; i < N; ++i) {
            strs = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                int v = Integer.parseInt(strs[j]);
                map[i + 1][j + 1] = v;

                if (v == 9) {
                    sI = i + 1;
                    sJ = j + 1;
                }
            }
        }

        map[sI][sJ] = 0;

        while (true) {
            Integer[] rlt = floodFill();

            if (rlt == null)
                break;

            r += rlt[Time];
            sI = rlt[Y];
            sJ = rlt[X];
            map[sI][sJ] = 0;

            eat++;

            if (eat == sharkSize) {
                sharkSize++;
                eat = 0;
            }
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public Integer[] floodFill() {
        fill2D(path, 1000);
        alCandidate.clear();
        al.add(new Integer[]{sI, sJ, 0});
        path[sI][sJ] = 0;

        while (al.size() > 0) {
            Integer[] p = al.get(0);
            al.remove(0);

            int si = p[Y];
            int sj = p[X];
            int depth = p[2];

            if (path[si][sj] != 1000) {
                if (path[si][sj] < depth) {
                    continue;
                }
            }

            for (int idxD = 0; idxD < d4i.length; ++idxD) {
                int y = si + d4i[idxD];
                int x = sj + d4j[idxD];

                if (map[y][x] == -1)
                    continue;

                if (map[y][x] == 0
                        && depth + 1 < path[y][x]) {
                    al.add(new Integer[]{y, x, depth + 1});
                    path[y][x] = depth + 1;
                    continue;
                }

                if (map[y][x] == sharkSize
                        && depth + 1 < path[y][x]) {
                    al.add(new Integer[]{y, x, depth + 1});
                    path[y][x] = depth + 1;
                    continue;
                }

                if (map[y][x] < sharkSize
                        && depth + 1 < path[y][x]) {
                    alCandidate.add(new Integer[]{y, x, depth + 1});
                    al.add(new Integer[]{y, x, depth + 1});
                    path[y][x] = depth + 1;
                    continue;
                }
            }
        }

        if (alCandidate.size() == 0) {
            return null;
        }

        Collections.sort(alCandidate, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[2] == o2[2]) {
                    if (o1[0] == o2[0]) {
                        return (o1[1] - o2[1]);
                    }

                    return (o1[0] - o2[0]);
                }

                return (o1[2] - o2[2]);
            }
        });

        return alCandidate.get(0);
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }


}