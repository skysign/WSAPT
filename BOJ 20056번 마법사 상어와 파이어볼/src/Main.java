import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, K;
    String[] strs;

    static int UP = 0;
    static int UR = 1;
    static int RT = 2;
    static int DR = 3;
    static int DN = 4;
    static int DL = 5;
    static int LT = 6;
    static int UL = 7;

    static int _M = 2;
    static int _S = 3;
    static int _D = 4;
    static int _NUM = 5;

    int[][] dRC = new int[][]{
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };

    int[][] mergedDirs = new int[][]{
            {0, 2, 4, 6},
            {1, 3, 5, 7}
    };

    int numFireBall = 1;

    HashMap<Integer, int[]> hmFireBalls = new HashMap<>();
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> mapRows = new HashMap<>();
    HashMap<Integer, Integer> hmEvenOdd = new HashMap<Integer, Integer>();

    public void solve() throws Exception {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
        K = Integer.parseInt(strs[2]);

        for (int m = 1; m <= M; ++m) {
            strs = br.readLine().split(" ");
            // r, c, m, s, d
            int[] fb = new int[]{
                    Integer.parseInt(strs[0]),
                    Integer.parseInt(strs[1]),
                    Integer.parseInt(strs[2]),
                    Integer.parseInt(strs[3]),
                    Integer.parseInt(strs[4]),
                    numFireBall
            };

            ++numFireBall;

            hmFireBalls.put(m, fb);
        }

        for (int k = 0; k < K; ++k) {
            moveFireBall();
            mergeFireBall();
        }

        int r = 0;

        Object[] nums = hmFireBalls.keySet().toArray();
        Arrays.sort(nums);

        for (Object _num : nums) {
            int num = (int) _num;

            int[] fb = hmFireBalls.get(num);
            r += fb[_M];
        }

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public void mergeFireBall() {
        Object[] keysRow = mapRows.keySet().toArray();
        Arrays.sort(keysRow);

        for (Object _keyRow : keysRow) {
            int keyRow = (int) _keyRow;
            HashMap<Integer, ArrayList<Integer>> mapCols = mapRows.get(keyRow);

            Object[] keysCol = mapCols.keySet().toArray();
            Arrays.sort(keysCol);

            for (Object _keyCol : keysCol) {
                int keyCol = (int) _keyCol;
                ArrayList<Integer> mapRowCol = mapCols.get(keyCol);

                if (mapRowCol.size() > 1) {
                    doMergeFireBalls(mapRowCol);
                }

                mapRowCol.clear();
                mapCols.remove(keyCol);
            }
        }
    }

    private void doMergeFireBalls(ArrayList<Integer> mapRowCol) {
        int[] mergedFB = new int[6];
        hmEvenOdd.put(0, 0);
        hmEvenOdd.put(1, 0);

        for (int num : mapRowCol) {
            int[] fb = hmFireBalls.get(num);

            mergedFB[0] = fb[0];
            mergedFB[1] = fb[1];

            mergedFB[_M] += fb[_M];
            mergedFB[_S] += fb[_S];

            if (fb[_D] % 2 == 0) {
                hmEvenOdd.put(0, hmEvenOdd.get(0) + 1);
            } else {
                hmEvenOdd.put(1, hmEvenOdd.get(1) + 1);
            }

            hmFireBalls.remove(num);
        }

        mergedFB[_M] = mergedFB[_M] / 5;
        mergedFB[_S] = mergedFB[_S] / mapRowCol.size();

        if (mergedFB[_M] == 0)
            return;

        int idxDir = 0;

        if ((hmEvenOdd.get(0) == mapRowCol.size()) || (hmEvenOdd.get(1) == mapRowCol.size())) {
            idxDir = 0;
        } else {
            idxDir = 1;
        }

        int[] dirs = mergedDirs[idxDir];

        for (int dir : dirs) {
            mergedFB[_D] = dir;
            mergedFB[_NUM] = numFireBall;
            int[] newFireball = Arrays.copyOf(mergedFB, mergedFB.length);

            hmFireBalls.put(numFireBall, newFireball);

            numFireBall++;
        }
    }

    //    ↑ ↗ → ↘ ↓ ↙ ← ↖
//    0  1  2  3  4  5  6  7
    public void moveFireBall() throws Exception {
        Object[] keys = hmFireBalls.keySet().toArray();
        Arrays.sort(keys);

        for (Object _key : keys) {
            int key = (int) _key;
            int[] fb = hmFireBalls.get(key);

            // r, c, m, s, d
            int sr = fb[0];
            int sc = fb[1];
            int m = fb[2];
            int s = fb[3];
            int d = fb[4];

            int dr = dRC[d][0];
            int dc = dRC[d][1];

            int dstR = sr + (s * dr);
            int dstC = sc + (s * dc);

            while (dstR <= 0) {
                dstR = (N + dstR);
            }

            while (dstR > N) {
                dstR = (dstR - N);
            }

            while (dstC <= 0) {
                dstC = (N + dstC);
            }

            while (dstC > N) {
                dstC = (dstC - N);
            }

            fb[0] = dstR;
            fb[1] = dstC;

//            if (!((0 < dstR) && (dstR <= N))) {
////                throw new Exception("out of map");
//                hmFireBalls.remove(key);
//                continue;
//            }
//
//            if (!((0 < dstC) && (dstC <= N))) {
////                throw new Exception("out of map");
//                hmFireBalls.remove(key);
//                continue;
//            }

            moveToRC(fb);
        }
    }

    public void moveToRC(int[] fb) {
        int r = fb[0];
        int c = fb[1];

        if (mapRows.containsKey(r) == false) {
            mapRows.put(r, new HashMap<Integer, ArrayList<Integer>>());
        }

        HashMap<Integer, ArrayList<Integer>> mapCols = mapRows.get(r);
        if (mapCols.containsKey(c) == false) {
            mapCols.put(c, new ArrayList<Integer>());
        }

        ArrayList<Integer> mapRowCol = mapCols.get(c);
        mapRowCol.add(fb[_NUM]);
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.solve();
    }
}