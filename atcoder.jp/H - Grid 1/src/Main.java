import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// H - Grid 1 / atcoder.jp
// 문제 링크 : https://atcoder.jp/contests/dp/tasks/dp_h
// Submission : https://atcoder.jp/contests/dp/submissions/9270190

public class Main {
    public static int H;
    public static int W;

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
//        Scanner sc = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        H = sc.nextInt();
        W = sc.nextInt();
        long[][] dp = new long[H+1][W+1];
        boolean[][] map = new boolean[H+1][W+1];

        for(int i=1; i<=H; ++i) {
            for(int j=1; j<=W; ++j) {
                byte c = sc.readByte();
                map[i][j] = (c=='.')? true: false;
            }
            sc.readByte();
        }

        dp[1][1] = 1;

        long modulo = (long)(Math.pow(10, 9))+7;

        for(int i=1; i<=H; ++i) {
            for(int j=1; j<=W; ++j) {
                dp[i][j] += (map[i-1][j])? dp[i-1][j]: 0;
//                dp[i][j] %= modulo;
                dp[i][j] += (map[i][j-1])? dp[i][j-1]: 0;
                dp[i][j] %= modulo;
            }
        }

        p.println(dp[H][W]);

        p.close();
    }

    static class Reader
    {
        static private int BUFFER_SIZE = 1 << 16;
        static private DataInputStream din;
        static private byte[] buffer;
        static private int bufferPointer, bytesRead;

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
            while ((c = readByte()) != -1)
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
            byte c = readByte();
            while (c <= ' ')
                c = readByte();
            boolean neg = (c == '-');
            if (neg)
                c = readByte();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = readByte()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = readByte();
            while (c <= ' ')
                c = readByte();
            boolean neg = (c == '-');
            if (neg)
                c = readByte();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = readByte()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = readByte();
            while (c <= ' ')
                c = readByte();
            boolean neg = (c == '-');
            if (neg)
                c = readByte();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = readByte()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = readByte()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        static private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        public static byte readByte() throws IOException
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

        public static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
        public static int skip() throws IOException{
            int b;
            while((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }
    }
}
