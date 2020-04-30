/**
 * 4991번 로봇 청소기 / BOJ
 * 문제링크 : https://www.acmicpc.net/problem/4991
 * 제출링크 : https://www.acmicpc.net/source/19520818
 * 문제풀이 : https://skysign.tistory.com/214
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /** 입력값의 column갯수 */
    int col;
    /** 입력값의 row갯수 */
    int row;
    /** 입력값을 저장하고 있는 맵 */
    char[][] map;
    /** 청소로봇과 모든 쓰레기 사이에 거리 */
    int[][] d;
    /** 청소로봇과 모든 쓰레기 갯수*/
    int N;

    public void solve() throws IOException {
        while(true) {
            col = sc.nextInt();
            row = sc.nextInt();

            /* 문제의 정의에 따라서, 0 0 입력일 때 종료 */
            if((col == row) && (col == 0))
                return;

            /** 청소로봇이 모든 쓰레기를 치우며, 이동하는 거리의 합중에서 최소값*/
            int rMinSumOfDistance = Integer.MAX_VALUE;
            map = new char[row][col];
            /** 청소로봇의 시작 위치 */
            Pair<Integer, Integer> robot = null;
            /** 쓰레기들의 위치 */
            ArrayList<Pair<Integer, Integer>> alPosTrash = new ArrayList<>();

            for(int i=0; i<row; ++i) {
                String line = sc.nextLine();

                for(int j=0; j<col; ++j) {
                    char c = line.charAt(j);
                    map[i][j] = c;

                    if('o' == c)
                        robot = new Pair<>(i, j);

                    if('*' == c)
                        alPosTrash.add(new Pair<Integer, Integer>(i, j));
                }
            }

            /**
             * 문제에서 청소로봇이 이동하면서, 쓰레기를 치운다고 설명하고 있지만,
             * 문제에서 요구하는 것을 보다, 코딩에 하기 쉽도로고 바꿔써 보면
             * alPosAll(0)에서 출발해서,alPosAll(1) ~ alPosAll(N-1)까지
             * 모두 방뭉하는 거리의 최소값을 찾는 문제입니다.
             */
            /** 청소로봇 + 쓰레기들의 위치 */
            ArrayList<Pair<Integer, Integer>> alPosAll = new ArrayList();
            alPosAll.add(robot);
            alPosAll.addAll(alPosTrash);
            // robot 위치 + 쓰레기들의 위치, 갯수
            N = alPosAll.size();

            d = new int[alPosAll.size()][alPosAll.size()];

            // i에서 출발해서, j에 도착하는 거리는 distance[i][j]에 저장
            // i에서 추발해서, j에 도착할 수 없으면 -1 저장
            // 시작은 모두 도착할 수 없다고 가정
            fill2D(d, -1);
            
            // robot의 시작 위치와, 쓰레기들의 위치를 o * 에서 인덱스로 바꿔줌
            // flood fill 에서 청소로봇과, 쓰레기를 찾아서, d 에 거리를 저장하기 쉽도록 인덱스로 바꿔준다
            // ASCII코드에서 0~10은 모두 제어문자임으로, 알파벳에 속하는 '.' 과 'x'등과 겹치지 않는다.
            for(int i=0; i<alPosAll.size(); ++i) {
                Pair<Integer, Integer> pos = alPosAll.get(i);
                int xi = (Integer)pos.getKey();
                int xj = (Integer)pos.getValue();

                map[xi][xj] = (char)i;
            }

            for(int i=0; i<alPosAll.size(); ++i) {
                // i 에서 출발해서, i를 제외한 다른 위치(청소로봇 or 쓰레기)까지의 거리를 구한다.
                // false : 청소로봇이 이동할 수 없는 위치에 쓰레기가 있음
                if(false == updateDistanceByFloodFill(alPosAll, i)) {
                    rMinSumOfDistance = -1;
                    break;
                }
                // flood fill을 사용해서, O(N * row * col)의 시간으로,
                // 청소로봇과 쓰레기들 사이의 이동거리를 알아 낼 수 있습니다.
                // 입력값의 최대값을 가정하면, O(11 * 20 * 20) 시간으로 찾을 수 있습니다.

                // 완전탐색(exhaustive search)으로 거리를 찾을 수 있지만,
                // 2가지 이유 때문에, 이 문제에서는 flood fill을 사용합니다.
                // 1. 이 문제에서는 이동거리만 구하면 되고, 이동 경로는 필요하지 안습니다.
                // 2. flood fill이 완전탐색보다 빨리 동작합니다.
            }

            // -1 프린트 한다음, 가장위의 while루프로 돌아감
            if(-1 == rMinSumOfDistance) {
                println(rMinSumOfDistance);
                continue;
            }

            // 쓰레기들을 치우는 순서에 따라서,
            // 청소로봇이 움직이는 거리가 달라진다.
            // 따라서, 쓰레기의 갯수에 따라서, Permutation(순열)을 만들어,
            // 순열의 순서대로, 청소로봇이 이동했을 때, 이동 경로의 합을 구한다.

            // 플로이드 워셜(floyd warshall), 해밀턴 패스(halmilton path) 등의 방법으로도,
            // 이 문제를 푸는 것이 가능할 것으로 생각하지만,
            // 순열을 사용해서 푸는 것이 보다 간단한 풀이입니다.
            // 입력에 따라서, 이동하는 방식이 2차원의 전후좌우 4가지 방향 밖에 없기 때문에,
            // 쓰레기들을 방문하는 순서를 순열로만들고, 가능한 순열을 모두 만든 후에,
            // 순열의 순서대로 쓰레기를 방문하여, 청소로봇의 최소이동 경로를 찾는다.
            int[] idxTrashes = new int[alPosTrash.size()];
            for(int i=0; i<alPosTrash.size(); ++i) {
                idxTrashes[i] = i+1;
            }

            ArrayList<int[]> alPermutationTrash = new ArrayList<>();
            permutation(idxTrashes, 0, idxTrashes.length, idxTrashes.length, alPermutationTrash);

            rMinSumOfDistance = Integer.MAX_VALUE;

            for(int[] permutationTrashes: alPermutationTrash) {
                int[] pathes = new int[permutationTrashes.length +1];
                System.arraycopy(permutationTrashes, 0, pathes, 1, permutationTrashes.length);

                int sumOfDistance = 0;

                for(int i=0; i<pathes.length-1; ++i) {
                    sumOfDistance += d[pathes[i]][pathes[i+1]];
                }

                // 이동 경로합의 최소값
                rMinSumOfDistance = Math.min(rMinSumOfDistance, sumOfDistance);
            }

            println(rMinSumOfDistance);
        }
    }

    /**
     * i 에서 출발해서, i를 제외한 다른 위치(청소로봇 or 쓰레기)까지의 거리를 구한다.
     * @param alPosAll 청소로봇과 쓰레기들의 위치
     * @param idx 시작위치를 가리키는 인덱스
     * @return false 이동할 수 없는 위치에 쓰레기가 있음, true 모든 쓰레기가 이동할 수 있는 위치에 있음
     */
    public boolean updateDistanceByFloodFill(ArrayList<Pair<Integer, Integer>> alPosAll, int idx) {
        int[][] visitied = new int[row][col];
        ArrayList<Pair<Integer, Integer>> alPos = new ArrayList<>();
        alPos.add(alPosAll.get(idx));
        return floodFill(alPos, visitied, 1, idx);
    }

    public boolean floodFill(ArrayList<Pair<Integer, Integer>> alPos,
                         int[][] visitied, int depth, int idx) {
        ArrayList<Pair<Integer, Integer>> alPosNext = new ArrayList<>();

        for(Pair<Integer, Integer> pos: alPos) {
            int si = (Integer) pos.getKey();
            int sj = (Integer) pos.getValue();

            int r = IsBeFlooded(si, sj, visitied);

            if(r == 0) {
                visitied[si][sj] = depth;
                char m = map[si][sj];
                if((0<=m) && (m<N)) {
                    // bidirectional 하기 때문에
                    d[idx][m] = depth -1;
                    d[m][idx] = depth -1;
                }

                for(int idxD4=0; idxD4<d4i.length; ++idxD4) {
                    int di = si + d4i[idxD4];
                    int dj = sj + d4j[idxD4];

                    alPosNext.add(new Pair<Integer, Integer>(di, dj));
                }
            }
        }

        if(0 == alPosNext.size()) {
            // 이동 할 수 없는 위치에, 쓰레기가 있는지 확인
            for(int j=0; j<N; ++j) {
                if(-1 == d[idx][j])
                    return false;
            }

            return true;
        }

        return floodFill(alPosNext, visitied, depth +1, idx);
    }

    public int IsBeFlooded(int i, int j, int[][] visitied) {
        // case : blocked / 못감 / -1
        // case : can be flooded / 이동할 수 있음 / 0

        boolean b;
        b = ((0<= i) && (i <row));
        if(!b)
            return -1;

        b = ((0<= j) && (j <col));
        if(!b)
            return -1;

        if('x' == map[i][j])
            return -1;

        if(0 == visitied[i][j])
            return 0;

        // 0 != visitied[i][j] 이미 flooeded되서, 한번 방문 했던 곳
        return -1;
    }

//        int n = ;
//        for(int i=0; i<n; ++i) {
//
//        }

//        int n = ;
//        for(int j=0; j<n; ++j) {
//
//        }

//        int n = ;
//        for(int i=0; i<n; ++i) {
//            for(int j=0; j<n; ++j) {
//
//            }
//        }

//        int N = ;
//        for(int i=0; i<n; ++i) {
//            for(int j=0; j<n; ++j) {
//                for(int k=0; k<n; ++k) {
//
//                }
//            }
//        }

    public void _solve() throws IOException {
        solve();
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main._solve();
    }

    // begin Permutation
    /*
     * https://bcp0109.tistory.com/14
     * depth 0
     * n arr의 길이
     * r 뽑는 갯수
     */
    void permutation(int[] arr, int depth, int n, int r, ArrayList<int[]> al) {
        if (depth == r) {
            int[] rs = new int[r];
            System.arraycopy(arr, 0, rs, 0, r);
            al.add(rs);
            return;
        }

        for (int i=depth; i<n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r, al);
            swap(arr, depth, i);
        }
    }

    void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }
    // end Permutation

    public class MNode<TV> extends Node {
        ArrayList<MNode<TV>> mChidren ;

        MNode(TV v) {
            super(v);
            mChidren = new ArrayList<>();
        }
    }

    public class BNode<TV> extends Node {
        BNode<TV> mL;
        BNode<TV> mR;

        BNode(TV v) {
            super(v);
        }
    }

    public class Node<TV> {
        public TV mV;

        Node(TV v) {
            set(v);
        }

        public void set(TV v) {
            mV = v;
        }
    }

    public int[][] pSum2D(int[][] dt) {
        int[][] pSum2D = new int[dt.length][dt[0].length];
        return _prefixSum2D(dt, pSum2D, dt.length, dt[0].length);
    }

    public int[][] pSum2D(int[][] dt, int[][] pSum2D) {
        return _prefixSum2D(dt, pSum2D, dt.length, dt[0].length);
    }

    public int[][] _prefixSum2D(int[][] dt, int[][] pSum2D, int I, int J) {
        pSum2D[0][0] = dt[0][0];
        for(int j=1; j<J; ++j) {
            pSum2D[0][j] = dt[0][j] + pSum2D[0][j-1];
        }

        for(int i=1; i<I; ++i) {
            pSum2D[i][0] = dt[i][0] + pSum2D[i-1][0];
        }

        for(int i=1; i<I; ++i) {
            for(int j=1; j<J; ++j) {
                pSum2D[i][j] = dt[i][j] + pSum2D[i-1][j] + pSum2D[i][j-1] - pSum2D[i-1][j-1];
            }
        }

        return pSum2D;
    }

    public int area2DbyCnt(int[][] pSum2D, int i1, int j1, int i2, int j2) {
        return area2DbyIdx(pSum2D, i1-1, j1-1, i2-1, j2-1);
    }

    public int area2DbyIdx(int[][] pSum2D, int i1, int j1, int i2, int j2) {
        int r = pSum2D[i2][j2];

        if(i1>0)
            r -= pSum2D[i1-1][j2];
        if(j1>0)
            r -= pSum2D[i2][j1-1];

        if((i1>0) && (j1>0))
            r += pSum2D[i1-1][j1-1];

        return r;
    }

    public int[][] rotate90(int[][] a, int N) {
        int[][] tp = new int[N][N];

        for(int i=0; i<N; ++i) {
            for(int j=0; j<N; ++j) {
                tp[i][j] = a[N-1-j][i];
            }
        }

        return tp;
    }

    public int[][] rotate180(int[][] a, int N) {
        int[][] tp = new int[N][N];

        for(int i=0; i<N; ++i) {
            for(int j=0; j<N; ++j) {
                tp[N-i-1][N-j-1] = a[i][j];
            }
        }

        return tp;
    }

    public int[][] rotate270(int[][] a, int N) {
        int[][] tp = new int[N][N];

        for(int i=0; i<N; ++i) {
            for(int j=0; j<N; ++j) {
                tp[N-1-j][i] = a[i][j];
            }
        }

        return tp;
    }

    public void flip_h(int[][] a, int N) {
        for(int i=0; i<N; ++i) {
            for(int j=0; j<N/2; ++j) {
                int tp = a[i][N-j-1];
                a[i][N-j-1] = a[i][j];
                a[i][j] = tp;
            }
        }
    }

    public void flip_v(int[][] a, int N) {
        for(int i=0; i<N/2; ++i) {
            for(int j=0; j<N; ++j) {
                int tp = a[N-i-1][j];
                a[N-i-1][j] = a[i][j];
                a[i][j] = tp;
            }
        }
    }

    public char[][] clone2D(char[][] src) {
        int row = src.length;
        int col = src[0].length;
        char[][] dst = new char[row][col];

        for (int i=0; i<row; i++) {
            System.arraycopy(src[i],0, dst[i],0, col);
        }

        return dst;
    }

    public boolean[][] clone2D(boolean[][] src) {
        int row = src.length;
        int col = src[0].length;
        boolean[][] dst = new boolean[row][col];

        for (int i=0; i<row; i++) {
            System.arraycopy(src[i],0, dst[i],0, col);
        }

        return dst;
    }

    public int[][] clone2D(int[][] src) {
        int row = src.length;
        int col = src[0].length;
        int[][] dst = new int[row][col];

        for (int i=0; i<row; i++) {
            System.arraycopy(src[i],0, dst[i],0, col);
        }

        return dst;
    }

    public class Pair<K, V> extends _Pair implements Comparable {
        public Pair(K key, V value) {
            super(key, value);
        }

        @Override
        public int compareTo(Object o) {
            Pair other = (Pair)o;
            int k1 = Integer.parseInt(this.getKey().toString());
            int k2 = Integer.parseInt(other.getKey().toString());

            if((k1 - k2) == 0) {
                int v1 = Integer.parseInt(this.getValue().toString());
                int v2 = Integer.parseInt(other.getValue().toString());
                return v1 - v2;
            }

            return k1 - k2;
        }
    }

    // http://cr.openjdk.java.net/~vadim/8140503/webrev.01/modules/base/src/main/java/javafx/util/Pair.java.html
    public class _Pair<K,V> {
        private K key;
        public K getKey() { return key; }
        public void setKey(K kk) { key = kk; }
        private V value;
        public V getValue() { return value; }
        public void setValue(V vv) { value = vv; }

        public void set(K kk, V vv) { key = kk; value = vv; }

        public _Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof _Pair) {
                _Pair pair = (_Pair) o;
                if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
                if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
                return true;
            }
            return false;
        }
    }

    // Normally used MIN/MAX number
    public final int _1087654321 = 1087654321;
    public final int __1087654321 = -1087654321;
    public final int _987654321 = 987654321;
    public final int __987654321 = -987654321;

    // Commonly used numbers as a divider of mod operation to prevent overflow
    public final int _10_007 = 10007;
    public final int _1_000_000_007 = 1000000007;
    final int MOD = _1_000_000_007;

    public void print(int v) throws IOException {
        print(v+"");
    }

    public void print(String s) throws IOException {
        bw.write(s);
    }

    public void println() throws IOException {
        print("\n");
    }

    public void println(int a) throws IOException {
        print(a+"\n");
    }

    public void println(long a) throws IOException {
        print(a+"\n");
    }

    public void println(double a) throws IOException {
        print(a+"\n");
    }

    public void println(String s) throws IOException {
        bw.write(s+"\n");
    }

    // -1
    public final int MINUS_1 = -1;

    // 4 ways
    public int[] d4i = new int[]{-1, 1, 0, 0};
    public int[] d4j = new int[]{0, 0, -1, 1};

    // 8 ways
    public int[] d8i = new int[]{-1, 1, 0, 0,  -1, -1, 1, 1};
    public int[] d8j = new int[]{0, 0, -1, 1,  -1, 1, 1, -1};

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void fill2D(long[][] _2D, long v) {
        for(long[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void fill2D(double[][] _2D, double v) {
        for(double[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void print2D(int[][] dp) throws IOException {
        print("    ");

        for(int j=0; j<dp[0].length; ++j){
            print(SS(j)+j + " ");
        }
        println();

        for(int i=0; i<dp.length; ++i){
            print(SS(i)+i+"|");
            for(int j=0; j<dp[0].length; ++j){
                print(SS(dp[i][j])+dp[i][j] + " ");
            }
            println();
        }
    }

    public void print2D(long[][] dp) throws IOException {
        print("    ");

        for(int j=0; j<dp[0].length; ++j){
            print(SS(j)+j + " ");
        }
        println();

        for(int i=0; i<dp.length; ++i){
            print(SS(i)+i+"|");
            for(int j=0; j<dp[0].length; ++j){
                print(SS(dp[i][j])+dp[i][j] + " ");
            }
            println();
        }
    }

    public final int LENGHT_OF_NUMBER = 3;
    /**
     * Generate space string for alignment when we print 2D arrays.
     * its length is LENGHT_OF_NUMBER - the length of number
     * Basically, we assume that number a is less than 1000.
     * So, LENGHT_OF_NUMBER is 3 by default.
     * @param a
     * @return
     */
    public String SS(int a) {
        String s = a+"";
        int l = LENGHT_OF_NUMBER - s.length();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<l; ++i) {
            sb.append(" ");
        }

        return sb.toString();
    }

    public String SS(long a) {
        String s = a+"";
        int l = LENGHT_OF_NUMBER - s.length();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<l; ++i) {
            sb.append(" ");
        }

        return sb.toString();
    }

    // Initialize 3D arrays with value v
    public void fill3D(int[][][] _3D, int v) {
        for(int[][] _2D: _3D) {
            for(int[] _1D: _2D) {
                Arrays.fill(_1D, v);
            }
        }
    }

    // GCD
    int GCD(int a, int b) {
        if(0 == b) {
            return a;
        }

        return GCD(b, a%b);
    }

    long GCD(long a, long b) {
        if(0 == b) {
            return a;
        }

        return GCD(b, a%b);
    }

    boolean IsEven(int a) {
        return (a>>1) == ((a>>1)<<1);
    }

    boolean IsOdd(int a) {
        return (a>>1) != ((a>>1)<<1);
    }

    boolean IsEven(long a) {
        return (a>>1) == ((a>>1)<<1);
    }

    boolean IsOdd(long a) {
        return (a>>1) != ((a>>1)<<1);
    }

    public Reader sc = new Reader();
//    Sometimes, Reader class cause unknown problem, when I submit my java code to judge server.
//    For more detail, please see https://algospot.com/forum/read/4731/
//    public Scanner sc = new Scanner(System.in);

    public BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
    public class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String nextLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

//    public void test_matrix_methods() throws IOException {
//        // Test Case 1
//        int m1[][] = { {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16} };
//
//        // Tese Case 2
//        int m2[][] = { {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9} };
//
//        println("Matrix Original");
//        print2D(m1);
//
//        println("Matrix Rotate 90");
//        print2D(rotate90(m1, m1.length));
//
//        println("Matrix Rotate 180");
//        print2D(rotate180(m1, m1.length));
//
//        println("Matrix Rotate 270");
//        print2D(rotate270(m1, m1.length));
//
//        println("Matrix Flip Horizontal");
//        int[][] m1h = clone2D(m1);
//        flip_h(m1h, m1h.length);
//        print2D(m1h);
//
//        println("Matrix Flip Vertical");
//        int[][] m1v = clone2D(m1);
//        flip_v(m1v, m1v.length);
//        print2D(m1v);
//
//        println();
//
//        println("Matrix Original");
//        print2D(m2);
//
//        println("Matrix Rotate 90");
//        print2D(rotate90(m2, m2.length));
//
//        println("Matrix Rotate 180");
//        print2D(rotate180(m2, m2.length));
//
//        println("Matrix Rotate 270");
//        print2D(rotate270(m2, m2.length));
//
//        println("Matrix Flip Horizontal");
//        int[][] m2h = clone2D(m2);
//        flip_h(m2h, m2h.length);
//        print2D(m2h);
//
//        println("Matrix Flip Vertical");
//        int[][] m2v = clone2D(m2);
//        flip_v(m2v, m2v.length);
//        print2D(m2v);
//    }
//
//    public void test_pSum2D_methods() throws IOException {
//        // Test Case 1
//        int m1[][] = { {1, 1, 1, 1},
//                {1, 1, 1, 1},
//                {1, 1, 1, 1},
//                {1, 1, 1, 1} };
//
//        println("Matrix Original");
//        print2D(m1);
//
//        int[][] m1pSum2D = new int[m1.length][m1[0].length];
//        println("pSum2D");
//        pSum2D(m1, m1pSum2D);
//        print2D(m1pSum2D);
//
//        println(area2DbyIdx(m1pSum2D, 1, 1, 3, 3));
//        println(area2DbyIdx(m1pSum2D, 0, 0, 3, 3));
//        println(area2DbyIdx(m1pSum2D, 0, 1, 3, 3));
//        println(area2DbyIdx(m1pSum2D, 1, 0, 3, 3));
//    }
}