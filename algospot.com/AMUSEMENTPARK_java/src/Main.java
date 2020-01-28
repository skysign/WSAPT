import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AMUSEMENTPARK 놀이 공원 / algospot.com
 * 문제링크 : https://algospot.com/judge/problem/read/AMUSEMENTPARK
 * 제출링크 : CPP 코드 https://algospot.com/judge/submission/detail/653294
 *
 * 정확한 이유는 잘 모르겠지만, 자바코드로 작성해서 올리면, '시간초과'로 정답 처리가 되지 않습니다.
 * 맞게 코딩한 것 같은대, 계속 정답처리가 안되서, 같은 방식으로 cpp로 코딩했더니,
 * 정답 처리가 되네요. cpp 코드는 AMUSEMENTPARK_cpp 폴더 참고하세요.
 */
public class Main {
    int[][] mMap;

    public void solve() throws IOException {
//        Scanner sc = new Scanner(System.in);
        Reader sc = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = sc.nextInt();
        int N = sc.nextInt();
        int MelonPos = sc.nextInt();
        mMap = new int[M][N];

        // 정답 저장
        ArrayList<Integer> mR = new ArrayList();
        int[] posIs = new int[M*N+1];
        int[] posJs = new int[M*N+1];

        int cntPos = 0;

        for(int i=0; i<M; ++i) {
            for(int j=0; j<N; ++j) {
                mMap[i][j] = sc.nextInt();
                if(mMap[i][j] > 0) {
                    posIs[mMap[i][j]] = i;
                    posJs[mMap[i][j]] = j;
                    cntPos++;
                }
            }
        }

        for(int mPos=MelonPos, oPos=1; mPos<=cntPos; ++mPos, ++oPos) {
            if(true == AreTheyAdjacent(posIs[oPos], posJs[oPos], posIs[mPos], posJs[mPos])) {
                mR.add(oPos);
            }
            else if(false == AreTheyBlocked(posIs[oPos], posJs[oPos], posIs[mPos], posJs[mPos])) {
                mR.add(oPos);
            }
        }

        bw.write(mR.size()+"\n");
        for(int r : mR) {
            bw.write(r+"\n");
        }
        bw.close();
    }

    public boolean AreTheyAdjacent(int Oi, int Oj, int Mi, int Mj) {
        int[] di = new int[]{-1, 1, 0, 0,  -1, -1, 1, 1};
        int[] dj = new int[]{0, 0, -1, 1,  -1, 1, 1, -1};

        for(int k=0; k<di.length; ++k) {
            if((Oi == (Mi + di[k])) && (Oj == (Mj + dj[k])))
                return true;
        }

        return false;
    }

    public boolean AreTheyBlocked(int Oi, int Oj, int Mi, int Mj) {
        // i 가 같음
        if(Oi == Mi) {
            int fixedI = Oi;
            int begJ = Math.min(Oj, Mj) +1;
            int endJ = Math.max(Oj, Mj);
            for(int j = begJ; j < endJ; ++j) {
                if(mMap[fixedI][j] != 0) {
                    return true;
                }
            }
        }

        // j 가 같음
        if(Oj == Mj) {
            int fixedJ = Oj;
            int begI = Math.min(Oi, Mi) +1;
            int endI = Math.max(Oi, Mi);
            for(int i = begI; i < endI; ++i) {
                if(mMap[i][fixedJ] != 0) {
                    return true;
                }
            }
        }

        int di = Math.abs(Oi - Mi);
        int dj = Math.abs(Oj - Mj);

        // di와 dj 가 둘다 소수이면
        // 중간에 시선을 막는 부분이 존재 하지 않음
        if(AreTheyPrimeNumber(di, dj))
            return false;

        // Orange와 Melon이 대각선 방향에 있음
        // Orange에서 Melon방향으로 다가갈 때,
        // 중간이 막혀 있으면 blocked(true)
        int gcd = gcd(di, dj);

        di = (Mi - Oi) / gcd;
        dj = (Mj - Oj) / gcd;

        for(int step=1, i = Oi, j = Oj; step<gcd; ++step) {
            i += di;
            j += dj;
            if(mMap[i][j] != 0)
                return true;
        }

        return false;
    }

    public boolean AreTheyPrimeNumber(int a, int b) {
        int min = Math.min(a, b);
        for(int i=2; i<=min; ++i) {
            if ((a%i == 0) && (b%i == 0)) {
                return false;
            }
        }

        return true;
    }

    public int gcd(int a,int b) {
        if(b==0)
            return a;

        return gcd(b, a%b);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
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
