import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

// I - Coins / atcoder.jp
// 문제 링크 : https://atcoder.jp/contests/dp/tasks/dp_i
// 문제 해설 : https://jinpyo.kim/EducationalDP-solution
// Submission : https://atcoder.jp/contests/dp/submissions/9282200

public class Main {
    public static int N;
    public static HashSet<Integer> hs;
    public static void main(String[] args) throws IOException {
//        Scanner sc  = new Scanner(System.in);
        Reader sc  = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        N = sc.nextInt();
        double[] ps = new double[N+1];
        double[][] dp = new double[N+1][N+1];

        for(int i=1; i<=N; ++i) {
            ps[i] = sc.nextDouble();
        }

        dp[0][0] = 1;

        // i동전까지 고려 했을 때, Head가 j번 나올 확률
        // Head가 J번 나온다는 것은 2가지 확률의 합을 의미합니다.
        // i-1 동전까지 j-1번 Head가 나올 확률에 * i동전이 Head가 나올 확률
        // i-1 동전까지 j번 Head가 나올 확률에 * i동전이 Tail이 나올 확률
        // 이 두 확률의 합입니다.
        for(int i=1; i<=N; ++i) {
            for(int j=0; j<=i; ++j) {
                if(j-1>=0)
                    dp[i][j] += dp[i-1][j-1] * ps[i];
                dp[i][j] += dp[i-1][j] * (1-ps[i]);
            }
        }

//        d0(dp);

        int k = (N>>1) + 1;
        double p = 0;
        for(int j=k; j<=N; ++j) {
            p += dp[N][j];
        }

        pw.printf("%1.10f\n", p);
        pw.close();
    }

    public static void d0(double[][] dp) {
        System.out.print("    ");

        for(int j=0; j<dp[0].length; ++j){
            System.out.printf("%3d ", j);
        }
        System.out.println();

        for(int i=0; i<dp.length; ++i){
            System.out.printf("%d|", i);
            for(int j=0; j<dp[0].length; ++j){
                System.out.printf("%f ", dp[i][j]);
            }
            System.out.println();
        }
    }

    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
    static class Reader
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
