import java.io.*;
import java.util.Scanner;

/**
 * BOGGLE 보글 게임 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/BOGGLE
 * 제출링크 : https://algospot.com/judge/submission/detail/653737
 */
public class Main {
    public static void main(String[] args) throws IOException {
	    Main main  = new Main();
	    main.getInput();
    }

    public void getInput() throws IOException {
//        Scanner sc = new Scanner(System.in);
        Reader sc = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = sc.nextInt();
//        sc.nextLine();

        for(int t=0; t<T; ++t) {
            char[][] map = new char[5][5];
            int M = 5;
            int N = 5;
            for(int i=0; i<M; ++i) {
                String line = sc.nextLine();
                for(int j=0; j<N; ++j) {
                    map[i][j] = line.charAt(j);
                }
            }
            int cntWords = sc.nextInt();
            String[] words = new String[cntWords];
//            sc.nextLine();
            for(int i=0; i<cntWords; ++i) {
                String word = sc.nextLine();
                boolean b = findWord(map, M, N, word, 0);
//                System.out.printf("%s %s\n", word, (b)? "YES": "NO");
                String ans = word + " ";
                ans += (b)? "YES": "NO";
                ans += "\n";
                bw.write(ans);
            }
        }

        bw.close();
    }

    public boolean findWord(char[][] map, int M, int N, String word, int idxWord) {
        boolean[][][] visitiedMap = new boolean[M][N][word.length()];

        for(int i=0; i<M; ++i) {
            for(int j=0; j<N; ++j) {
                if(findWord2(map, M, N, i, j, word, idxWord, visitiedMap))
                    return true;
            }
        }

        return false;
    }

    public boolean findWord2(char[][] map, int M, int N, int i, int j, String word, int idxChar, boolean[][][] visitedMap) {
        int[] dis = new int[]{-1, -1, -1, 0, 1, 1,  1, 0};
        int[] djs = new int[]{-1,  0,  1, 1, 1, 0, -1, -1};

        visitedMap[i][j][idxChar] = true;

        if(word.charAt(idxChar) != map[i][j])
            return false;

        if(idxChar == (word.length()-1))
            return true;

        for(int k=0; k<dis.length; ++k) {
            int di = i + dis[k];
            int dj = j + djs[k];

            if((0<= di) && (di < M) && (0<=dj) && (dj < N)) {
                if(visitedMap[di][dj][idxChar+1]) {
                    continue;
                }

                if(findWord2(map, M, N, di, dj, word, idxChar+1, visitedMap))
                    return true;
            }
        }

        return false;
    }

    // https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
    class Reader
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