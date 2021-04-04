import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * PACKING 여행 짐 싸기 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/PACKING
 * 제출링크 : https://algospot.com/judge/submission/detail/656203
 */

public class Main {
    int N;
    int W;
    int[] Ws;
    int[] Vs;
    String[] Ss;
    int[][] dp;
    ArrayList<String> al;

    public void solve() throws IOException {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int r = 0;
            N = sc.nextInt();
            W = sc.nextInt();
            Ws = new int[N];
            Vs = new int[N];
            Ss = new String[N];
            dp = new int[N][W+1];
            al = new ArrayList();
            fill2D(dp, -1);

            for(int i=0; i<N; ++i) {
                String strT = sc.nextLine();
                String[] Ts = strT.split(" ");
                Ss[i] = Ts[0];
                Ws[i] = Integer.parseInt(Ts[1]);
                Vs[i] = Integer.parseInt(Ts[2]);
            }

            r = knapsack(W, 0);
            reconstruct(W, 0);
            println(r + " " + al.size());
            for(String s: al)
                println(s);
        }
    }

    public int knapsack(int w, int idx) {
        if(idx == N)
            return 0;

        if(dp[idx][w] != -1)
            return dp[idx][w];

        int r1 = knapsack(w, idx+1);
        int r2 = 0;

        if(w-Ws[idx] >= 0)
            r2 = knapsack(w-Ws[idx], idx+1) + Vs[idx];

        return dp[idx][w] = Math.max(r1, r2);
    }

    public void reconstruct(int w, int idx) {
        if(idx == N)
            return;

        if(knapsack(w, idx) == knapsack(w, idx+1)) {
            reconstruct(w, idx+1);
        }
        else{
            al.add(Ss[idx]);
            reconstruct(w-Ws[idx], idx+1);
        }
    }

    public void _solve() throws IOException {
        solve();
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main._solve();
    }

    public void println(int a) throws IOException {
        bw.write(a);
    }

    public void println(long a) throws IOException {
        println(a+"");
    }

    public void println(String s) throws IOException {
        bw.write(s+"\n");
    }

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

    public Reader sc = new Reader();
//    Sometimes, Reader class cause unknown problem, when I submit my java code.
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