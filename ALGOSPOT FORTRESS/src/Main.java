import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * FORTRESS 요새 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/FORTRESS?c=17380
 * 예제입력은 답이 나오는대, 답안 제출하면 오답이네요..ㅡ.ㅡ;
 * ALGOSPOT은 테스트 케이스를 공개하지 않아서, 오답 디버깅은 하지 않겠습니다.
 */
public class Main {
    int N;
    int[][] dp;
    int[] Xs;
    int[] Ys;
    int[] Rs;

    public void solve() throws IOException {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            N = sc.nextInt();
            Xs = new int[N];
            Ys = new int[N];
            Rs = new int[N];

            for(int i=0; i<N; ++i) {
                Xs[i] = sc.nextInt();
                Ys[i] = sc.nextInt();
                Rs[i] = sc.nextInt();
            }

            int r = FORTRESS(N, Xs, Ys, Rs);
            println(r);
        }
    }

    final int X = 0;
    final int Y = 1;
    final int R = 2;

    public int FORTRESS(int N, int[] xs, int[] ys, int[] rs) {
        FNode<int[]> root = new FNode(new int[]{xs[0], ys[0], rs[0]});

        for(int i=1; i<N; ++i) {
            FNode<int[]> node = new FNode(new int[]{xs[i], ys[i], rs[i]});
            // 문제 '입력' 단락의 마지막 문장,
            // '입력에 주어지는 첫 번째 성벽은 외벽이며, 외벽은 입력에 주어지는 모든 다른 성벽을 포함합니다.'
            // 이것에 따라서, 0번재 root node 밑에 다른 노드들을 추가합니다.
            root.addRec(node);
        }

        int r1 = root.maxDepth();
        if(1 == root.mChildren.size())
            return r1;

        int n = root.mChildren.size();
        int[] rtns = new int[n];

        for(int i=0; i<n; ++i) {
            FNode<int[]> childNode = (FNode<int[]>)root.mChildren.get(i);
            rtns[i] = childNode.maxDepth() +1;
        }

        Arrays.sort(rtns);
        int r2 = rtns[n-1] + rtns[n-2];

        return Math.max(r1, r2);
    }

    public class FNode<TValue> extends MNode {
        FNode(TValue v) {
            super(v);
        }

        public boolean IsChild(FNode<TValue> node) {
            return IsChild((int[])node.mV);
        }

        public boolean IsChild(int[] child) {
            int[] mine = (int[])mV;

            int x1 = mine[X];
            int y1 = mine[Y];
            int r1 = mine[R];
            int x2 = child[X];
            int y2 = child[Y];
            int r2 = child[R];

            return (r1 > r2) && (sqr(x2-x1) + sqr(y2-y1) < sqr(r1-r2));
        }

        public void addRec(FNode<TValue> node) {
            boolean bAdd = false;
            for(Object obj : mChildren) {
                FNode<TValue> childNode = (FNode<TValue>)obj;
                if(childNode.IsChild(node)) {
                    childNode.addRec(node);
                    bAdd = true;
                    break;
                }
            }

            for(int i=0; !bAdd & i<mChildren.size(); ++i) {
                FNode<TValue> childNode = (FNode<TValue>)mChildren.get(i);
                if(node.IsChild(childNode)) {
                    node.addRec(childNode);
                    mChildren.set(i, node);
                    bAdd = true;
                    break;
                }
            }

            if(!bAdd) {
                this.add(node);
            }
        }

        public void add(FNode<TValue> node) {
            mChildren.add(node);
        }
    }

    public int sqr(int v) {
        return v * v;
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

    public class MNode<TValue> extends Node {
        ArrayList<? extends Node> mChildren;

        MNode(TValue v) {
            super(v);
            mChildren = new ArrayList<>();
        }

        @Override
        public int maxDepth() {
            if(0 == mChildren.size())
                return 0;

            int r = 0;
            for(Node<TValue> node : mChildren) {
                MNode<TValue> childNode = (MNode<TValue>)node;
                r = Math.max(r, childNode.maxDepth() + 1);
            }

            return r;
        }
    }

    public class BNode<TValue> extends Node {
        BNode<TValue> mL;
        BNode<TValue> mR;

        BNode(TValue v) {
            super(v);
        }

        @Override
        public int maxDepth() {
            return 0;
        }
    }

    public abstract class Node<TValue> {
        public TValue mV;

        Node(TValue v) {
            set(v);
        }

        public void set(TValue v) {
            mV = v;
        }

        abstract public int maxDepth();
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

    public int[][] clone2D(int[][] src) {
        int N = src.length;
        int[][] dst = new int[N][N];

        for (int i=0; i<N; i++) {
            System.arraycopy(src[i],0, dst[i],0, N);
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
        private V value;
        public V getValue() { return value; }
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