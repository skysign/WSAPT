import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * NUMB3RS 두니발 박사의 탈옥 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/NUMB3RS
 * 제출링크 : https://algospot.com/judge/submission/detail/656159
 */
public class Main {
//    public Scanner sc = new Scanner(System.in);
    public Reader sc = new Reader();
    public int N;
    public int D;
    public int P;
    public int[] prefixSum;
    public int[][] map;
    public double[][] dp;
    public int[] DSTs;
    public int DST;

    public void solve() throws IOException {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            N = sc.nextInt();
            D = sc.nextInt();
            P = sc.nextInt();

            map = new int[N][N];
            prefixSum = new int[N];

            for(int i=0; i<N; ++i) {
                for(int j=0; j<N; ++j) {
                    map[i][j] = sc.nextInt();
                }
            }

            prep();

            /* 2초가 제한시간인대, 1952ms 겨우 패스 했네요. */
            int cntTown = sc.nextInt();
            for(int i=0; i<cntTown; ++i) {
                DST = sc.nextInt();
                dp = new double[D+1][N];
                double r = calProb_rec(0, P);
                System.out.printf("%.8f ", r);
            }
            System.out.println();

            /* 답은 나오지만, 시간초과로 오답 처리 됩니다.
            calProb(1, P, 1);

            int cntTown = sc.nextInt();
            for(int i=0; i<cntTown; ++i) {
                int idxTown = sc.nextInt();
                System.out.printf("%.8f", dp[D][idxTown]);
                System.out.print(" ");
            }
            System.out.println();
            */
        }
    }

    /**
     * 책의 코드 8.23 을 자바로 구현한 메서드입니다.
     * @param days
     * @param idxTown
     * @return
     */
    public double calProb_rec(int days, int idxTown) {
        int i = idxTown;

        if(D == days) {
            return (idxTown == DST)? 1.0: 0.0;
        }

        if(dp[days][idxTown] > 0.0) {
            return dp[days][idxTown];
        }

        for(int j=0; j<N; ++j) {
            if(1 == map[i][j]) {
                double r = calProb_rec(days+1, j);
                dp[days][idxTown] += (r / prefixSum[i]);
            }
        }

        return dp[days][idxTown];
    }

    public void prep() {
        for(int i=0; i<N; ++i) {
            for(int j=0; j<N; ++j) {
                prefixSum[i] += map[i][j];
            }
        }
    }

    /**
     * 책의 코드 8.22 와 같은 역할을 하는 코드입니다.
     * 답은 나오지만, 시간초과로, 오답 처리 됩니다.
     * 마지막 D일 째에, 두니발 박사가 있을 확률이 dp[D][] 에 저장되어 있습니다.
     * @param d 날짜
     * @param idx 출발하는 마을인덱스
     * @param prevProb 출발하는 마을 인덱스에 있을 확률
     */
    public void calProb(int d, int idx, double prevProb) {
        double _1_double = 1;
        int i=idx;

        for(int j=0; j<N; ++j) {
            if(1 == map[i][j]) {
                dp[d][j] += _1_double/prefixSum[i] * prevProb;
                if(d < D) {
                    calProb(d+1, j, dp[d][j]);
                }
            }
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