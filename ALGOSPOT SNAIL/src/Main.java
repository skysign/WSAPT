import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * SNAIL 달팽이 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/SNAIL
 * 제출링크 : https://algospot.com/judge/submission/detail/655979
 */
public class Main {
    Reader sc = new Reader();
    int N;
    int M;
    double[][] cache;

    public void solve() throws IOException {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            N = sc.nextInt(); // 올라갈 높이
            M = sc.nextInt(); // M일간 올라가고, M일간 비올확률 75%
            // 모든날에 비가온다고 가정하고
            cache = new double[M+1][2*M+1];
            fill2D(cache, -1);
            double r = climb(0, 0);
            System.out.printf("%.8f\n", r);
//            System.out.println(r);
        }
    }

    /**
     * N 일전까지, M 미터 올라갈 수 있는 확률
     * @param days
     * @param climbed
     * @return
     */
    public double climb(int days, int climbed) {
        if(M == days) {
            return (climbed >= N)? 1: 0;
        }

        if(cache[days][climbed] >= 0.0) {
            return cache[days][climbed];
        }

        return cache[days][climbed] = (0.25*climb(days+1, climbed+1) + 0.75*climb(days+1, climbed+2));
    }

    // 2D 어레이 초기화
    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void fill2D(double[][] _2D, int v) {
        for(double[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

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