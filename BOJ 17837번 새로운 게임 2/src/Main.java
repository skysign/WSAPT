/**
 * BOJ 17837번 새로운 게임 2
 *
 * 유튜브 문제 풀이: https://youtu.be/d3ozTN-lP1Y
 *
 * 문제링크: https://www.acmicpc.net/problem/17837
 *
 * 자바소스: https://bit.ly/3DcUMvd
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // map
    // 0은 흰색, 1은 빨간색, 2는 파란색이다.
    static int WHITE = 0;
    static int RED = 1;
    static int BLUE = 2;

    int N, K;
    String[] strs;
    int[][] map;
    ArrayList[][] mapMal;
    HashMap<Integer, Mal> hm;

    public class Mal {
        public int mk, mr, mc, mdir;

        public Mal(int k, int r, int c, int dir) {
            mk = k;
            mr = r;
            mc = c;
            mdir = dir;
        }

        public void setRC(int r, int c) {
            mr = r;
            mc = c;
        }
    }

    // 1부터 순서대로 →, ←, ↑, ↓
    int[][] drc = new int[][]{
            {-2, -2},
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };

    int[] reversDrc = new int[]{
            -1, 2, 1, 4, 3
    };

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        K = Integer.parseInt(strs[1]);

        map = new int[N + 2][N + 2];
        fill2D(map, 2);
        mapMal = new ArrayList[N + 2][N + 2];
        hm = new HashMap<>();

        for (int r = 1; r <= N; ++r) {
            strs = br.readLine().split(" ");

            for (int c = 1; c <= N; ++c) {
                map[r][c] = Integer.parseInt(strs[c - 1]);
                mapMal[r][c] = new ArrayList<Integer>();
            }
        }

        for (int k = 1; k <= K; ++k) {
            strs = br.readLine().split(" ");

            int r = Integer.parseInt(strs[0]);
            int c = Integer.parseInt(strs[1]);
            int dir = Integer.parseInt(strs[2]);

            mapMal[r][c].add(k);
            hm.put(k, new Mal(k, r, c, dir));
        }


        for (int turn = 1; turn <= 1000; ++turn) {
            // 말 이동
            moveMal(turn);
        }

        bw.write(String.valueOf(-1));
        bw.newLine();
        bw.close();
    }

    public void moveMal(int turn) throws IOException {
        for (int k = 1; k <= K; ++k) {
            Mal mal = hm.get(k);

            int srcR = mal.mr;
            int srcC = mal.mc;

            int dstR = srcR + drc[mal.mdir][0];
            int dstC = srcC + drc[mal.mdir][1];

            if (map[dstR][dstC] == BLUE) {
                mal.mdir = reversDrc[mal.mdir];

                dstR = srcR + drc[mal.mdir][0];
                dstC = srcC + drc[mal.mdir][1];

                if (map[dstR][dstC] == BLUE) {
                    continue;
                }
            }

            if ((map[dstR][dstC] == RED)
                    || (map[dstR][dstC] == WHITE)) {
                ArrayList alSrc = mapMal[srcR][srcC];
                ArrayList alDst = mapMal[dstR][dstC];

                int idx = alSrc.indexOf(k);

                if (map[dstR][dstC] == WHITE) {
                    while (alSrc.size() > idx) {
                        int v = (Integer)alSrc.get(idx);
                        alSrc.remove(idx);
                        hm.get(v).setRC(dstR, dstC);
                        alDst.add(v);
                    }

                    // 게임 종료되는 턴
                    if (alDst.size() >= 4) {
                        this.bw.write(String.valueOf(turn));
                        this.bw.newLine();
                        this.bw.close();
                        System.exit(0);
                    }
                }
                else {
                    Stack<Integer> stk = new Stack<>();

                    while (alSrc.size() > idx) {
                        int v = (Integer)alSrc.get(idx);
                        alSrc.remove(idx);
                        stk.push(v);
                    }

                    while (!stk.empty()) {
                        int v = stk.pop();
                        hm.get(v).setRC(dstR, dstC);
                        alDst.add(v);
                    }

                    // 게임 종료되는 턴
                    if (alDst.size() >= 4) {
                        this.bw.write(String.valueOf(turn));
                        this.bw.newLine();
                        this.bw.close();
                        System.exit(0);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }
}