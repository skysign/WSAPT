import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Scanner;

public class Main {
    public static int EVEN = 0;
    public static int ODD = 1;
    public long nTaro = 0;
    public long nJiro = 0;

    public void solve() throws IOException {
//        Scanner sc = new Scanner(System.in);
        Reader sc = new Reader();
        int N = sc.nextInt();
        long[] as = new long[N];

        for(int i=0; i<N; i++) {
            as[i] = sc.nextLong();
        }

        long[] d = new long[N+1];
        getBest(N, d, as);

        long[] rs = getEvenOddSum(d);
        long r;
        if(N == ((N>>1)<<1)) {
            r = rs[EVEN] - rs[ODD];
        }
        else {
            r = rs[ODD] - rs[EVEN];
        }
        System.out.println(r);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    /**
     *
     * @param N 은 d의 마지막 배열 원소를 가리키는 인덱스
     * @param d
     * @param as
     */
    public void getBest(int N, long[] d, long[] as) {
        int i = N - 1;
        if(as.length == 1) {
            d[N] = as[0];
            return;
        }

        long head = as[0];
        long tail = as[as.length-1];

        // 원래 N개 인대, 배열의 인덱스와 N을 맞추기 위해서,
        // N+1 할당하는 것으로 코딩
        // -1은 거기서 1개 작은 숫자를 할당해야 히기에 -1함
        long[] dHead = new long[N+1 -1];
        long[] dTail = new long[N+1 -1];

        getBest(N-1, dHead, Arrays.copyOfRange(as, 0+1, as.length));
        getBest(N-1, dTail, Arrays.copyOfRange(as, 0, as.length-1));

        long[] hSum = getEvenOddSum(dHead);
        long[] tSum = getEvenOddSum(dTail);

        long headMax;
        long tailMax;

        // N가 even number 짝수
        if(N == ((N>>1)<<1)) {
            headMax = head + hSum[EVEN] - hSum[ODD];
            tailMax = tail + tSum[EVEN] - tSum[ODD];
        }
        // i가 odd number 홀수
        else {
            headMax = head + hSum[ODD] - hSum[EVEN];
            tailMax = tail + tSum[ODD] - tSum[EVEN];
        }

        // i번째 에서는 Head를 선택하는게 유리
        if(headMax == Math.max(headMax, tailMax)) {
            System.arraycopy(dHead, 0, d, 0, N);
            d[N] = head;
        }
        // i번째 에서는 Tail을 선택하는게 유리
        else {
            System.arraycopy(dTail, 0, d, 0, N);
            d[N] = tail;
        }
    }

    public long[] getEvenOddSum(long[] as) {
        long[] rs = new long[2];

        for(int i=0; i<as.length; ++i) {
            // even number 짝수
            if(i == ((i>>1)<<1)) {
                rs[0] += as[i];
            }
            // odd number 홀수
            else {
                rs[1] += as[i];
            }
        }

        return rs;
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