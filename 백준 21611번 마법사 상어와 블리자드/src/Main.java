import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M;
    int[][] mapSeq;
    int[][] mapTmp;
    int[][] map;

    int ans = 0;
    String[] strs;

    ArrayList<int[]> alMagics = new ArrayList<>();
    LinkedList<Integer> llBeads = new LinkedList<>();
    int cntBead = 0;
    ArrayList<ArrayList<Integer>> alGroups = new ArrayList<>();

    int[][] SeqRC;
    int cR;
    int cC;
    int maxNumBead;

    Stack<Integer> stkTmp = new Stack<>();

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        mapSeq = new int[N][N];
        mapTmp = new int[N][N];
        map = new int[N][N];

        SeqRC = new int[N * N][2];
        maxNumBead = N * N - 1;

        cR = N / 2;
        cC = N / 2;

        for (int r = 0; r < N; ++r) {
            strs = br.readLine().split(" ");

            for (int c = 0; c < N; ++c) {
                int v = Integer.parseInt(strs[c]);

                if (v != 0) {
                    map[r][c] = v;
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

        // 상어에서 부터 시작해서, 칸의 번호 만들기
        drawBangle(mapSeq);

        // 칸의 번호에 따라서, 구슬 저장하기
        for (int num = 1; num < SeqRC.length; ++num) {
            int r = SeqRC[num][0];
            int c = SeqRC[num][1];

            if (map[r][c] != 0) {
                llBeads.add(map[r][c]);
                ++cntBead;
            }
        }

        for (int[] magic : alMagics) {
            // 블리자드 마법
            printhm("Before blizzardMagic");

            blizzardMagic(magic);

            printhm("After blizzardMagic");

            // 4개 이상 연속하는 구슬 폭발
            explodeBeads();

            // 구슬이 변화하는 단계
            convertBeads();

            printhm("After");

            // 구슬을 다시 상어 왼쪽 칸을 1번으로 배치 하는 단계
            placeBeads();
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    private void placeBeads() {
        // cntBead 는 N * N - 1 보다 작거나 같음
        ListIterator<Integer> ll = llBeads.listIterator();

        for (int idx = 1; idx <= cntBead; ++idx) {
            int r = SeqRC[idx][0];
            int c = SeqRC[idx][1];
            map[r][c] = ll.next();
        }

        for (int idx = cntBead; idx <= maxNumBead; ++idx) {
            int r = SeqRC[idx][0];
            int c = SeqRC[idx][1];
            map[r][c] = 0;
        }
    }

    private void convertBeads() {
        llBeads.clear();
        cntBead = 0;

        // numBead는 짝수임
        //   maxNumBead <- 홀수 * 홀수 -1
        int numBead = Math.min(maxNumBead, alGroups.size() * 2);
        int maxIdx = numBead / 2;

        for (int idxGroup = 0; idxGroup < maxIdx; ++idxGroup) {
            ArrayList<Integer> group = alGroups.get(idxGroup);
            llBeads.add(group.size());
            llBeads.add(group.get(0));
            cntBead += 2;
            group.clear();
        }
    }

    private void blizzardMagic(int[] magic) {
        int dir = magic[0];
        int len = magic[1];

        stkTmp.clear();

        // 블리자드 마법 시전
        for (int l = 1; l <= len; ++l) {
            int nr = cR + dRC[dir][0] * l;
            int nc = cC + dRC[dir][1] * l;

            int seq = mapSeq[nr][nc];
            stkTmp.push(seq);
        }

        while (!stkTmp.isEmpty()) {
            int cnt = stkTmp.pop();
            if (cnt <= llBeads.size()) {
                llBeads.remove(cnt - 1);
            }
        }
    }

    public void explodeBeads() throws IOException {
        int numExplosion = 1;

        while (numExplosion > 0) {
            makeGroups();
            numExplosion = explodeGroups();
            flattenGroups();
        }
    }

    private void makeGroups() {
        ListIterator<Integer> ll = llBeads.listIterator();
        ArrayList<Integer> alCrntGroup = null;

        alGroups.clear();

        while (ll.hasNext()) {
            int bead = ll.next();

            if (alGroups.size() == 0) {
                alCrntGroup = new ArrayList<>();
                alGroups.add(alCrntGroup);
                alCrntGroup.add(bead);
                continue;
            }

            int groupBead = alCrntGroup.get(0);

            if (bead == groupBead) {
                alCrntGroup.add(bead);
            } else {
                alCrntGroup = new ArrayList<>();
                alGroups.add(alCrntGroup);
                alCrntGroup.add(bead);
            }
        }
    }

    private int explodeGroups() {
        int rtn = 0;

        Stack<ArrayList<Integer>> stk = new Stack<>();

        for (ArrayList<Integer> group : alGroups) {
            if (group.size() >= 4) {
                ans += group.get(0) * group.size();
                stk.push(group);
                ++rtn;
            }
        }

        while(!stk.isEmpty()) {
            alGroups.remove(stk.pop());
        }

        return rtn;
    }

    private void flattenGroups() {
        llBeads.clear();

        for (ArrayList<Integer> group : alGroups) {
            for (int bead : group) {
                llBeads.add(bead);
            }
        }
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
        SeqRC[num] = new int[]{r, c};

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
                SeqRC[num] = new int[]{r, c};
            }
        }
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void printhm(String s) throws IOException {
//        bw.write(s);
//        bw.newLine();
//
//        String str = String.format("size: %2d\n", llBeads.size());
//        bw.write(str);
//
//        ListIterator<Integer> ll = llBeads.listIterator();
//        int cnt = 0;
//
//        while (ll.hasNext()) {
//            ++cnt;
//            str = String.format("%2d %2d\n", cnt, ll.next());
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