import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * PICNIC 소풍 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/PICNIC
 * 제출링크 : https://algospot.com/judge/submission/detail/654321
 */
public class Main {
    public class Pair implements Comparable {
        public int[] mV;
        public Pair(int l, int r) {
            mV = new int[2];
            mV[0] = l;
            mV[1] = r;
        }

        @Override
        public int compareTo(Object x) {
            Pair o = (Pair)x;
            if(mV[0] == o.mV[0]) {
                if(mV[1] == o.mV[1]) {
                    return 0;
                }
                return mV[1] - o.mV[1];
            }
            return mV[0] - o.mV[0];
        }

        public boolean IsSameMember(Pair p) {
            for(int a : mV)
                for(int b: p.mV)
                    if(a == b)
                        return true;

            return false;
        }
    }

    public void solve() throws IOException {
        Reader sc = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int cntPair = sc.nextInt();
            Pair[] Pairs = new Pair[cntPair];

            for(int p=0; p<cntPair; ++p) {
                Pairs[p] = new Pair(sc.nextInt(), sc.nextInt());
            }
            Arrays.sort(Pairs);

            // N은 항상 짝수 일 수 밖에 없고
            // 찾아야 하는 것은 Pairs의 조합의 수
            int r = match(N>>1, Pairs);
            bw.write(r + "\n");
        }
        bw.flush();
        bw.close();
    }

    public int match(int N, Pair[] Pairs) {
        Integer r = 0;
        Pair[] tmpPS = new Pair[N];

        return combination(Pairs, N, 0, N, tmpPS);
    }

    public int combination(Pair[] PS, int N, int idxItem, int cntItem, Pair[] tmpPS) {
        int r = 0;

        if(0 == cntItem) {
//            printPS(tmpPS);
            return 1;
        }

        for(int i=idxItem; i<PS.length; ++i) {
            tmpPS[N-cntItem] = PS[i];
            if(IsSameMember(tmpPS, N-cntItem+1)) {
                continue;
            }
            else {
                r += combination(PS, N, i+1, cntItem-1, tmpPS);
            }
        }

        return r;
    }

    public void printPS(Pair[] PS) {
        for(Pair p : PS) {
            System.out.printf(" %d %d\n", p.mV[0], p.mV[1]);
        }
        System.out.println();
    }

    public boolean IsSameMember(Pair[] tmpPS, int N) {
        if(N < 2)
            return false;

        for(int i=0; i<N; ++i) {
            for(int j=i+1; j<N; ++j) {
                if(tmpPS[i].IsSameMember(tmpPS[j]))
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

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