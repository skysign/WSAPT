import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * QUADTREE 쿼드 트리 뒤집기 / ALGOSPOT / 런타임 오류 나는 코드
 * 문제링크 : https://algospot.com/judge/problem/read/QUADTREE
 *
 * 입력받는 속도를 빠르게 하기 위해서, Reader()클래스를 사용하면,
 * ALGOSPOT 채점서버에서, '런타임 오류'로 오답 처리됩니다.
 */
public class Main {
//    Scanner sc = new Scanner(System.in);
    Reader sc = new Reader();

    public void solve() throws IOException {
        int T = sc.nextInt();
//        sc.nextLine();

        for(int t=0; t<T; ++t) {
            String in = sc.nextLine();
            String r = run(in);
            System.out.println(r);
        }
    }

    public String run(String S) {
        if((S.charAt(0) != 'x') || (S.length() < 1))
            return S;

        String a = "";
        StringBuilder r = new StringBuilder("");
        a = reverse(S, r);
        return r.toString();
    }

    public String reverse(String S, StringBuilder strRtn) {
        StringBuilder[] ss = new StringBuilder[]{
                new StringBuilder(""),
                new StringBuilder(""),
                new StringBuilder(""),
                new StringBuilder("")};
        // 맨앞에 x 가 있기 때문에, x를 잘라내고.
        S = S.substring(1);
        S = parse(S, ss[0]);
        S = parse(S, ss[1]);
        S = parse(S, ss[2]);
        S = parse(S, ss[3]);


        strRtn.append('x');
        strRtn.append(ss[2]);
        strRtn.append(ss[3]);
        strRtn.append(ss[0]);
        strRtn.append(ss[1]);
//        strRtn = 'x' + ss[2] + ss[3] + ss[0] + ss[1];
        return S;
    }

    public String parse(String S, StringBuilder strRtn) {
        if(S.length() < 1)
            return "";

        String strRemained = "";

        if(S.charAt(0) == 'x') {
            strRemained = reverse(S, strRtn);
        }
        else {
            strRtn.append(S.charAt(0));
            strRemained = S.substring(1);
        }

        return strRemained;
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