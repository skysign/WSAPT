import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int R, C, M;
    String[] strs;

    int[][] map;
    // 1 아래 -> 위
    // 2 위 -> 아래
    // 3 왼쪽 -> 오른
    // 4 오른 -> 왼
    int[] d4i = {-1, 1, 0, 0};
    int[] d4j = {0, 0, 1, -1};
    ArrayList<int[]> al;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");

        R = Integer.parseInt(strs[0]);
        C = Integer.parseInt(strs[1]);
        M = Integer.parseInt(strs[2]);

        map = new int[R][C];
        fill2D(map, -1);

        al = new ArrayList<>();

//        N = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; ++m) {
            strs = br.readLine().split(" ");
            int r = Integer.parseInt(strs[0]) - 1;
            int c = Integer.parseInt(strs[1]) - 1;
            int s = Integer.parseInt(strs[2]);
            int d = Integer.parseInt(strs[3]) - 1;
            int z = Integer.parseInt(strs[4]);

            map[r][c] = m;
            al.add(new int[]{r, c, s, d, z});
        }

        int answer = 0;

        // 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
        for (int c = 0; c < C; ++c) {
            // 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
            for (int r = 0; r < R; ++r) {
                if (map[r][c] != -1) {
                    int idx = map[r][c];
                    int[] sang = al.get(idx);
                    answer += sang[4];
                    al.set(idx, null);
                    map[r][c] = -1;
                    break;
                }
            }

            // 3. 상어가 이동한다.
            for (int idx = 0; idx < al.size(); ++idx) {
                int[] sang = al.get(idx);
                if (sang != null) {
                    int sy = sang[0];
                    int sx = sang[1];
                    int speed = sang[2];
                    int dir = sang[3];

                    int dy = d4i[dir];
                    int dx = d4j[dir];

                    // 이동하기전의 위치를 지우고
                    map[sy][sx] = -1;

                    for (int s = speed; s > 0; --s) {
                        int ty = sy + dy;
                        int tx = sx + dx;
                        if ((0 <= ty) && (ty < R) && (0 <= tx) && (tx < C)) {
                            sy += dy;
                            sx += dx;
                        } else {
                            dy *= -1;
                            dx *= -1;

                            dir = changeDir(dir);

                            sy += dy;
                            sx += dx;
                        }
                    }

                    sang[0] = sy;
                    sang[1] = sx;
                    sang[3] = dir;
                }
            }

            // 이동한 위치 다시 map에 표시하기
            for (int idx = 0; idx < al.size(); ++idx) {
                int[] sang = al.get(idx);
                if (sang != null) {
                    int sy = sang[0];
                    int sx = sang[1];
                    int size = sang[4];

                    // 다른 상어를 만난 경우
                    if (map[sy][sx] != -1) {
                        int idxYou = map[sy][sx];
                        int[] sangYou = al.get(idxYou);
                        int sizeYou = sangYou[4];

                        if (size > sizeYou) {
                            al.set(idxYou, null);
                            map[sy][sx] = idx;
                        } else if (size < sizeYou) {
                            al.set(idx, null);
                        }
                    }
                    else {
                        map[sy][sx] = idx;
                    }
                }
            }
        }


        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.close();
    }

    public int changeDir(int dir) {
        if (dir == 0)
            return 1;
        else if (dir == 1)
            return 0;
        else if (dir == 2)
            return 3;

        return 2;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }
}