import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * BOARDCOVER 게임판 덮기 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/BOARDCOVER
 * 제출링크 : https://algospot.com/judge/submission/detail/654332
 * RTE가 왜 나는지 모르겠네요...
 * ALGOSPOT은 테스트 케이스를 공개하지 않아서, 디버깅은 더 안하려고 합니다.
 */

public class Main {
    public final int ROTATE = 4;
    public final int BLOCKLENGTH = 3;
    /**
     * ㄱ 시계방향으로 회전
     * 시작점은 항상 0, 0에서 시작
     * 0  0,0 0,1 1,1
     * 90 0,0 1,0 1,-1
     * 180 0,0 1,0 1,1
     * 270 0,0 0,1 0,1
     */
    public int[][][] Lblocks;
    public void solve() throws IOException {
        Reader sc = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        Lblocks = new int[1][1][1]{{{1}}};
//        int[] ttt = new int[]

        Lblocks = new int[][][]{
                {{0,0}, {0,1}, {1,1}},
                {{0,0}, {1,0}, {1,-1}},
                {{0,0}, {1,0}, {1,1}},
                {{0,0}, {0,1}, {1,0}}
        };

        int T = sc.nextInt();
        int r = 0;

        for(int t=0; t<T; ++t) {
            int M = sc.nextInt();
            int N = sc.nextInt();
            sc.nextLine();
            boolean[][] map = new boolean[M][N];
            for(int i=0; i<M; ++i) {
                String line = sc.nextLine();
                for(int j=0; j<N; ++j) {
                    map[i][j] = (line.charAt(j) == '#')? false: true;
                }
            }

            r = coverBoard(map, M, N);
            bw.write(r + "\n");
        }

        bw.flush();
        bw.close();
    }

    public int coverBoard(boolean[][] map, int M, int N) {
        int di = -1, dj = -1;

        for(int i=0; i<M; ++i) {
            for(int j=0; j<N; ++j) {
                if(map[i][j]) {
                    di = i;
                    dj = j;
                    break;
                }
            }
            if(dj != -1)
                break;
        }

        if(-1 == dj)
            return 1;

        int r = 0;

        for(int rot=0; rot<ROTATE; ++rot) {
            if(putBlock(map, rot, di, dj, false)) {
                r += coverBoard(map, M, N);
                pullBlock(map, rot, di, dj);
            }
        }

        return r;
    }

    public boolean pullBlock(boolean[][] map, int rot, int i, int j) {
        return putBlock(map, rot, i, j, true);
    }

    public boolean putBlock(boolean[][] map, int rot, int i, int j, boolean b) {
        boolean bCanPut = true;

        for(int bl=0; (bl<BLOCKLENGTH) && bCanPut; ++bl) {
            int di = i + Lblocks[rot][bl][0];
            int dj = j + Lblocks[rot][bl][1];

            if(!((0<=di) && (di<map.length))) {
                bCanPut = false;
                continue;
            }
            if(!((0<=dj) && (dj<map[0].length))) {
                bCanPut = false;
                continue;
            }
            if(map[di][dj] == b) {
                bCanPut = false;
                continue;
            }
        }

        if(bCanPut) {
            for(int bl=0; bl<BLOCKLENGTH; ++bl) {
                int di = i + Lblocks[rot][bl][0];
                int dj = j + Lblocks[rot][bl][1];
                map[di][dj] = b;
            }
        }

        return bCanPut;
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