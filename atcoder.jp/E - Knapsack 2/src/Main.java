import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

// E - Knapsack 2 / atcoder.jp
// 문제 링크 : https://atcoder.jp/contests/dp/tasks/dp_e
// 문제 해설 : https://jinpyo.kim/EducationalDP-solution
// Submission : https://atcoder.jp/contests/dp/submissions/9204970

public class Main {
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

        public String readLine() throws IOException
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

    public static void main(String[] args) throws IOException {
//         Scanner sc = new Scanner(System.in);
        Reader sc = new Reader();
        int N = sc.nextInt();
        int W = sc.nextInt();
        int[] ws = new int[N+1];
        int[] vs = new int[N+1];
        PrintWriter pw = new PrintWriter(System.out);

        for(int i=1; i<=N; ++i) {
            ws[i] = sc.nextInt();
            vs[i] = sc.nextInt();
        }

        long r = Knapsack2(N, W, ws, vs);

        pw.println(r);
        pw.close();
    }

    public static long Knapsack2(int N, int W, int[] ws, int[] vs) {
        // Java 8에서 새로 추가된 Stream을 사용해서, 합을 구해봅니다.
        int V = IntStream.of(vs).sum();
        // 문제의 'Constraints'에서 주어진것 처럼 Wi의 값이 매우 크기 때문에,
        // int를 사용해서 풀면, overflow 되는 문제가 발생합니다.
        long[][] dp = new long[N+1][V+1];

        for(int n=0; n<=N; ++n) {
            for(int v=0; v<=V; ++v) {
                // dp를 추후에 계산할 때, overflow가 되어서, - 값으로 바뀌는 것을 피하기 위해서
                // 모든 value의 합인 V 를 빼주게 됩니다.
                dp[n][v] = Long.MAX_VALUE - V;
                //
            }
        }

        long value = 0;
        dp[0][0] = 0;
        for(int i=1; i<=N; ++i) {
            for(int v=0; v<=V; ++v) {
                dp[i][v] = Math.min(dp[i-1][v], dp[i][v]);
                if(v-vs[i]>=0)
                    dp[i][v] = Math.min(dp[i][v], dp[i-1][v-vs[i]] + ws[i]);
                if(dp[i][v] <= W) {
                    value = Math.max(value, v);
                }
            }
        }

        return value;
    }
}
