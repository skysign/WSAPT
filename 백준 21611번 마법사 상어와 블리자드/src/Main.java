import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int[][] mapSeq;
    int[][] mapTmp;

    Bead[][] map;
    int ans = 0;
    String[] strs;

    class Bead {
        public int n;

        public Bead(int num) {
            n = num;
        }
    }

    Bead nullBead = new Bead(0);
    ArrayList<int[]> alMagics = new ArrayList<>();
    HashMap<Integer, Bead> hm = new HashMap<>();
    HashMap<Integer, int[]> hmSeqRC = new HashMap<>();

    Stack<Integer> stkTmp = new Stack<>();

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        mapSeq = new int[N][N];
        mapTmp = new int[N][N];
        map = new Bead[N][N];

        int cr = N / 2;
        int cc = N / 2;

        drawBangle(mapSeq);

        for (int r = 0; r < N; ++r) {
            strs = br.readLine().split(" ");

            for (int c = 0; c < N; ++c) {
                int v = Integer.parseInt(strs[c]);

                if (v != 0) {
                    int seq = mapSeq[r][c];
                    Bead bead = new Bead(v);
                    hm.put(seq, bead);
                    map[r][c] = bead;
                    mapTmp[r][c] = bead.n;
                }
            }
        }

        for (int m = 0; m < M; ++m) {
            strs = br.readLine().split(" ");
            alMagics.add(new int[]{
                    Integer.parseInt(strs[0]) - 1,
                    Integer.parseInt(strs[1])
            });
        }

        for (int[] magic : alMagics) {
            int dir = magic[0];
            int len = magic[1];

            // 블리자드 마법 시전
            for (int l = 1; l <= len; ++l) {
                int nr = cr + dRC[dir][0] * l;
                int nc = cc + dRC[dir][1] * l;

                int seq = mapSeq[nr][nc];
                hm.remove(seq);
            }

            printhm("Before no explosion\n");

            // 4개 이상 연속하는 구슬 폭발
            boolean bExploed = explodeBeads();
            printhm("");

            while (bExploed) {
                bExploed = explodeBeads();
                printhm("");
            }


            // 구슬이 변화하는 단계가 된다.
            HashMap<Integer, Bead> hmTmp = new HashMap<>();

            Integer[] keys = hm.keySet().toArray(new Integer[hm.size()]);
            Arrays.sort(keys);

            for (int key : keys) {
                if (stkTmp.size() == 0) {
                    int n = hm.get(key).n;
                    stkTmp.push(n);
                } else {
                    int lastNum = stkTmp.peek();
                    int crntNum = hm.get(key).n;

                    if (crntNum == lastNum) {
                        stkTmp.push(lastNum);
                    } else {
                        int newkey = 1 + hmTmp.size();
                        hmTmp.put(newkey, new Bead(stkTmp.size()));
                        hmTmp.put(newkey + 1, new Bead(stkTmp.peek()));
                        stkTmp.clear();
                        stkTmp.push(crntNum);
                    }
                }
            }

            if (stkTmp.size() > 0) {
                int newkey = 1 + hmTmp.size();
                hmTmp.put(newkey, new Bead(stkTmp.size()));
                hmTmp.put(newkey + 1, new Bead(stkTmp.peek()));
                stkTmp.clear();
            }

            hm.clear();
            hm = hmTmp;

            // 변한 구슬을 map에 이동 시킬 차례
            fill2D(mapTmp, -1);

            keys = hm.keySet().toArray(new Integer[hm.size()]);
            Arrays.sort(keys);

            for (int seq : keys) {
                if (seq >= (N*N-1))
                    break;
                int[] rc = hmSeqRC.get(seq);
                map[rc[0]][rc[1]] = hm.get(seq);
                mapTmp[rc[0]][rc[1]] = hm.get(seq).n;
            }

            for (int seq = keys.length + 1; seq <= (N * N - 1); ++seq) {
                if (seq >= (N*N-1))
                    break;
                int[] rc = hmSeqRC.get(seq);
                map[rc[0]][rc[1]] = nullBead;
                mapTmp[rc[0]][rc[1]] = 0;
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public boolean explodeBeads() {
        stkTmp.clear();

        Integer[] keys = hm.keySet().toArray(new Integer[hm.size()]);
        Arrays.sort(keys);

        for (int key : keys) {
            if (stkTmp.size() == 0) {
                stkTmp.push(key);
            } else {
                int lastKey = stkTmp.peek();

                if (hm.get(key).n == hm.get(lastKey).n) {
                    stkTmp.push(key);
                } else {
                    if (stkTmp.size() >= 4) {
                        int num = hm.get(lastKey).n;
                        ans += (num * stkTmp.size());

                        while (!stkTmp.isEmpty()) {
                            int k = stkTmp.pop();
                            hm.remove(k);
                        }

                        return true;
                    }

                    stkTmp.clear();
                    stkTmp.add(key);
                }
            }
        }

        // stkTmp에 남아 있는 구슬도 폭발 가능한지 확인
        if (stkTmp.size() >= 4) {
            int num = hm.get(stkTmp.peek()).n;
            ans += (num * stkTmp.size());

            while (!stkTmp.isEmpty()) {
                int k = stkTmp.pop();
                hm.remove(k);
            }

            return true;
        }

        stkTmp.clear();

        return false;
    }

    // ↑, ↓, ←, →
    int[][] dRC = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int UP = 0;
    static int DN = 1;
    static int LT = 2;
    static int RT = 3;
    int[] turnLeft = new int[]{LT, RT, DN, UP};

    // precondition
    // map의 row와 col의 길이는 홀수이며, row와 col의 길이는 같다.
    public void drawBangle(int[][] map) {
        fill2D(map, -1);

        int N = map.length;
        int r = map.length / 2;
        int c = r;
        int num = 0;
        map[r][c] = num;

        int dir = LT;
        r = r + dRC[dir][0];
        c = c + dRC[dir][1];
        ++num;
        map[r][c] = num;
        hmSeqRC.put(num, new int[]{r, c});

        while ((0 <= r) && (r < N) && (0 <= c) && (c < N)) {
            int dirTurnLeft = turnLeft[dir];

            int nr = r + dRC[dirTurnLeft][0];
            int nc = c + dRC[dirTurnLeft][1];

            if (map[nr][nc] == -1) {
                dir = dirTurnLeft;
                r = nr;
                c = nc;
            } else {
                r = r + dRC[dir][0];
                c = c + dRC[dir][1];
            }

            ++num;
            if (num < N * N) {
                map[r][c] = num;
                hmSeqRC.put(num, new int[]{r, c});
            }
        }
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void printhm(String s) throws IOException {
//        Integer[] keys = hm.keySet().toArray(new Integer[hm.size()]);
//        Arrays.sort(keys);
//
//        bw.write(s);
//        bw.newLine();
//
//        String str = String.format("size: %2d\n", hm.size());
//        bw.write(str);
//
//        for (int key : keys) {
//            str = String.format("%2d %2d\n", key, hm.get(key).n);
//            bw.write(str);
//        }
//
//        bw.newLine();
//        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}