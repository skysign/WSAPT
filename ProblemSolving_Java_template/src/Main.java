import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * BOJ 
 * 문제링크 : 
 * 제출링크 : 
 * 문제풀이 : 
 */

public class Main {
    int N;
    int[][] dp;

    public void solve() throws IOException {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int r = 0;
            println(r);
        }
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
//            }`
//        }

    public void _solve() throws IOException {
        solve();
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main._solve();
    }

    /**
     * quick sort
     * @param dt
     * @param idxLow 처음에는 0
     * @param idxHigh 처음에는 dt.length -1
     */
    void quick_sort(int[] dt, int idxLow, int idxHigh) {
        if (idxLow < idxHigh) {
            int idxPivot = quick_partition(dt, idxLow, idxHigh);

            quick_sort(dt, idxLow, idxPivot-1);
            quick_sort(dt, idxPivot+1, idxHigh);
        }
    }

    int quick_partition(int[] dt, int idxLow, int idxHigh) {
        int idxPivot = idxHigh;
        int pivot = dt[idxPivot];

        idxHigh--;

        int idxL = idxLow;

        for (int idxR=idxLow; idxR<=idxHigh; ++idxR) {
            if (dt[idxR] < pivot) {
                swap(dt, idxL, idxR);
                idxL++;
            }
        }

        swap(dt, idxL, idxPivot);

        return idxL;
    }

//    void swap(int[] d, int i, int j) {
//        int t = d[i];
//
//        d[i] = d[j];
//        d[j] = t;
//    }

    public class Vertex<TV> {
        public TV mV;
        ArrayList<UndirectedEdge> mEdges = new ArrayList();

        Vertex(TV v) {
            mV = v;
        }

        public void addEdge(UndirectedEdge<TV> edge) {
            mEdges.add(edge);
        }

        public Vertex<TV> getConnectedVertexFromEdge(int i) {
            Vertex<TV> v1 = this.mEdges.get(i).mV1;
            Vertex<TV> v2 = this.mEdges.get(i).mV2;

            if (this == v1)
                return v2;

            return v1;
        }
    }

    public class UndirectedEdge<TV> {
        public Vertex<TV> mV1;
        public Vertex<TV> mV2;

        UndirectedEdge(Vertex<TV> v1, Vertex<TV> v2) {
            mV1 = v1;
            mV2 = v2;
            mV1.addEdge(this);
            mV2.addEdge(this);
        }
    }

    // toArray() example
    void testToArray() throws IOException {
        ArrayList<Integer> al = new ArrayList();
        al.add(1);
        al.add(3);
        al.add(5);
        al.add(7);

        // al.size() 과 new Integer[0]의 길이를 비교해서,
        // al.size()가 크면, 같은 타입으로, 배열을 새로 할당하고, 길이는 al.size()로 함
        // al.size()가 작으면, 할당된 배열을 그대로 사용함
        // ex) new Integer[5] 라고 하면, arr의 크기는 5임
        Integer[] arr = al.toArray(new Integer[0]);
        for(int x: arr) {
            println(x);
        }

        //
        int[] arr2 = al.stream().mapToInt(i -> i).toArray();
        for(int x: arr2) {
            println(x);
        }
    }

    // 소수 prime number
    boolean IsPrimeNnumber(int n) {
        if (n < 2)
            return false;

        for (int i=2; i*i<=n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    // begin 조합 Combination
    void testCombination() throws IOException {
        // 4개 중에 2개 고르기
        int n = 4;
        int r = 2;

        // 조합의 갯수
        println(countCombination(n, r));


        // 조합의 입력 데이터 만들기
        int[] arr = new int[n];
        for(int i=1; i<=n; ++i) {
            arr[i-1] = i;
        }

        boolean[] visited = new boolean[n];
        ArrayList<int[]> al = new ArrayList<>();
        combination(arr, visited, 0, n, 2, r, al);

        for(int[] comb: al) {
            for(int x: comb) {
                print(x);
                print(' ');
            }
            println();
        }
    }


    // 출처 : https://bcp0109.tistory.com/15
    /**
     * 조합의 갯수
     * @param n 전체 갯수
     * @param r 고르는 갯수
     */
    int countCombination(int n, int r) {
        if (r == 0 || r == n)
            return 1;

        return countCombination(n-1, r-1) + countCombination(n-1, r);
    }

    // 출처 : https://seungbong8-8.tistory.com/15
    // nCr = n!/(r! * (n-r)!)
    /**
     * 조합
     * @param arr
     * @param visited
     * @param start
     * @param n
     * @param r
     * @param R
     * @param al
     */
    void combination(int[] arr, boolean[] visited, int start, int n, int r, int R, ArrayList<int[]> al) {
        if (r == 0) {
            print(arr, visited, n, R, al);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1, R, al);
            visited[i] = false;
        }
    }

    void print(int[] arr, boolean[] visited, int n, int R, ArrayList<int[]> al) {
        int[] comb = new int[R];

        for (int i = 0, idx = 0; i < n; i++) {
            if (visited[i] == true) {
//                System.out.print(arr[i] + " ");
                comb[idx] = arr[i];
                idx++;
            }
        }

        al.add(comb);
//        System.out.println();
    }
    // end Combination

    // begin Permutation
    /**
     * 순열
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

        public void addChild(MNode<TV> nodeChild) {
            nodeChild.setParent(this);
            mChidren.add(nodeChild);
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
        public Node<TV> mParent;

        Node(TV v) {
            setParent(null);
            set(v);
        }

        public void set(TV v) {
            mV = v;
        }

        public void setParent(Node<TV> parent) {
            mParent = parent;
        }

        public Node<TV> getParent() {
            return mParent;
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
        public K L() { return getKey(); }
        public void setKey(K kk) { key = kk; }
        private V value;
        public V getValue() { return value; }
        public V R() { return getValue(); }
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

    public BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(int v) throws IOException {
        print(v+"");
    }

    public void print(char c) throws IOException {
        bw.write(c);
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

    // Travel 4 ways, start from 12h, and rotate as clockwise
    public int[] d4i = new int[]{1, 0, -1, 0};
    public int[] d4j = new int[]{0, 1, 0, -1};

    // Travel 8 ways, start from 12h, and rotate as clockwise
    //                          12     3       6       9
    public int[] d8i = new int[]{1, 1, 0, -1, -1, -1,  0,  1};
    public int[] d8j = new int[]{0, 1, 1,  1,  0, -1, -1, -1};

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

    // LCM
    int LCM(int a, int b) {
        return a * (b / GCD(a, b));
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