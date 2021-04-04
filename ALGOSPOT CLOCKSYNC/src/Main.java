import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * CLOCKSYNC Synchronizing Clocks / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/CLOCKSYNC
 * 제출링크 : https://algospot.com/judge/submission/detail/654405
 */

public class Main {
    public final int MAX_CLOCKS = 16;
    public int[][] mSwitches = new int[][]{
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };

    Reader sc = new Reader();
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solve() throws IOException {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int[] clocks = new int[MAX_CLOCKS];
            for(int i=0; i<clocks.length; ++i)
                clocks[i] = sc.nextInt();

            int r = solve2(clocks);
            bw.write(r + "\n");
        }

        bw.flush();
        bw.close();
    }

    public int solve2(int[] clocks) throws IOException {
        int r = push_switch(clocks, 0);

        return (r > 0)? r: -1;
    }

    private int push_switch(int[] clocks, int idxSwitch) {
        if(idxSwitch == mSwitches.length) {
            if(AreAllClocks12(clocks))
                return 0;
            return Integer.MIN_VALUE;
        }

        int rtn = Integer.MIN_VALUE;

        // 4번 누르면 처음으로 되돌아온다.
        for(int i=0; i<4; ++i) {
            rtn = Math.max(rtn, i + push_switch(clocks, idxSwitch+1));
            if(rtn > 0) {
                return rtn;
            }
            changeClocks(idxSwitch, clocks);
        }

        return rtn;
    }

    public void changeClocks(int idxSwitch, int[] clocks) {
        for(int idxClock: mSwitches[idxSwitch]) {
            clocks[idxClock] += 3;
            if(clocks[idxClock] == 15)
                clocks[idxClock] = 3;
        }
    }

    public boolean AreAllClocks12(int[] clocks) {
        boolean b = true;

        for(int i=0; i<clocks.length && b; ++i) {
            b = (clocks[i] == 12);
        }

        return b;
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