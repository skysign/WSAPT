import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ASYMTILING 비대칭 타일링 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/ASYMTILING
 * 제출링크 : https://algospot.com/judge/submission/detail/656417
 */
public class Main {
    int N;
    int[] dpFull;

    public void solve() throws IOException {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int r = 0;
            N = sc.nextInt();
            dpFull = new int[N+1];

            Arrays.fill(dpFull, -1);

            prep();
            r = tiling2NAsym(N);
            println(r);
        }
    }

    public void prep() {
        dpFull[0] = 1;
        dpFull[1] = 1;
        if(N>=2)
            dpFull[2] = 2;
    }

    public int tiling2N(int n) {
        if(n < 0)
            return 0;

        if(-1 != dpFull[n])
            return dpFull[n];

        return dpFull[n] = (tiling2N(n-1) + tiling2N(n-2)) % _1_000_000_007;
    }

    public int tiling2NAsym(int n) {
        if(IsOdd(n)) {
            return (tiling2N(n) - tiling2N(n/2) + MOD) % MOD;
        }

        int r = tiling2N(n);
        r = (r - tiling2N(n/2) + MOD) % MOD;
        r = (r - tiling2N(n/2-1) + MOD) % MOD;
        return r;
    }

    // commonly used numbers to prevent overflow as a divider of mod operation
    public final int _10_007 = 10007;
    public final int _1_000_000_007 = 1000000007;
    public final int MOD = _1_000_000_007;

    public void _solve() throws IOException {
        solve();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main._solve();
    }

    public void println(int a) throws IOException {
        println(a+"");
    }

    public void println(long a) throws IOException {
        println(a+"");
    }

    public void print(String s) throws IOException {
        bw.write(s);
    }

    public void println() throws IOException {
        bw.write("\n");
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

    // Initialize 3D arrays with value v
    public void fill3D(int[][][] _3D, int v) {
        for(int[][] _2D: _3D) {
            for(int[] _1D: _2D) {
                Arrays.fill(_1D, v);
            }
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
        return a == ((a>>1)<<1);
    }

    boolean IsEven(long a) {
        return a == ((a>>1)<<1);
    }

    boolean IsOdd(int a) {
        return a != ((a>>1)<<1);
    }

    boolean IsOdd(long a) {
        return a != ((a>>1)<<1);
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
}