import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    class RC {
        public int r, c;

        RC(int sr, int sc) {
            set(sr, sc);
        }

        void set(int sr, int sc) {
            this.r = sr;
            this.c = sc;
        }
    }

    AtomicInteger[][] cube;
    Deque<RC> qDeltaMyeon = new ArrayDeque<>();
    Deque<RC> qMyeon = new ArrayDeque<>();
    Deque<RC> Us = new ArrayDeque<>();
    Deque<RC> Ds = new ArrayDeque<>();
    Deque<RC> Fs = new ArrayDeque<>();
    Deque<RC> Bs = new ArrayDeque<>();
    Deque<RC> Ls = new ArrayDeque<>();
    Deque<RC> Rs = new ArrayDeque<>();

    int N = 0;
    String[] strs = null;

    public void solve() throws IOException {
        initCube();

        qDeltaMyeon.add(new RC(1, 0));
        qDeltaMyeon.add(new RC(2, 0));
        qDeltaMyeon.add(new RC(2, 1));
        qDeltaMyeon.add(new RC(2, 2));
        qDeltaMyeon.add(new RC(1, 2));
        qDeltaMyeon.add(new RC(0, 2));
        qDeltaMyeon.add(new RC(0, 1));
        qDeltaMyeon.add(new RC(0, 0));

        for (int i = 0; i < 8; ++i)
            qMyeon.add(new RC(0, 0));

        // U: 윗 면
        Us.add(new RC(3, 0));
        Us.add(new RC(3, 1));
        Us.add(new RC(3, 2));
        Us.add(new RC(3, 3));
        Us.add(new RC(3, 4));
        Us.add(new RC(3, 5));
        Us.add(new RC(3, 6));
        Us.add(new RC(3, 7));
        Us.add(new RC(3, 8));
        Us.add(new RC(3, 9));
        Us.add(new RC(3, 10));
        Us.add(new RC(3, 11));

        // D: 아랫 면
        Ds.add(new RC(5, 0));
        Ds.add(new RC(5, 1));
        Ds.add(new RC(5, 2));
        Ds.add(new RC(5, 3));
        Ds.add(new RC(5, 4));
        Ds.add(new RC(5, 5));
        Ds.add(new RC(5, 6));
        Ds.add(new RC(5, 7));
        Ds.add(new RC(5, 8));
        Ds.add(new RC(5, 9));
        Ds.add(new RC(5, 10));
        Ds.add(new RC(5, 11));

        // F: 앞면
        Fs.add(new RC(2, 3));
        Fs.add(new RC(2, 4));
        Fs.add(new RC(2, 5));
        Fs.add(new RC(3, 6));
        Fs.add(new RC(4, 6));
        Fs.add(new RC(5, 6));
        Fs.add(new RC(6, 5));
        Fs.add(new RC(6, 4));
        Fs.add(new RC(6, 3));
        Fs.add(new RC(5, 2));
        Fs.add(new RC(4, 2));
        Fs.add(new RC(3, 2));

        // B: 뒷 면
        Bs.add(new RC(0, 3));
        Bs.add(new RC(0, 4));
        Bs.add(new RC(0, 5));
        Bs.add(new RC(3, 8));
        Bs.add(new RC(4, 8));
        Bs.add(new RC(5, 8));
        Bs.add(new RC(8, 5));
        Bs.add(new RC(8, 4));
        Bs.add(new RC(8, 3));
        Bs.add(new RC(5, 0));
        Bs.add(new RC(4, 0));
        Bs.add(new RC(3, 0));

        // L: 왼쪽 면
        Ls.add(new RC(0, 3));
        Ls.add(new RC(1, 3));
        Ls.add(new RC(2, 3));
        Ls.add(new RC(3, 3));
        Ls.add(new RC(4, 3));
        Ls.add(new RC(5, 3));
        Ls.add(new RC(6, 3));
        Ls.add(new RC(7, 3));
        Ls.add(new RC(8, 3));
        Ls.add(new RC(9, 3));
        Ls.add(new RC(10, 3));
        Ls.add(new RC(11, 3));

        // R: 왼쪽 면
        Rs.add(new RC(0, 5));
        Rs.add(new RC(1, 5));
        Rs.add(new RC(2, 5));
        Rs.add(new RC(3, 5));
        Rs.add(new RC(4, 5));
        Rs.add(new RC(5, 5));
        Rs.add(new RC(6, 5));
        Rs.add(new RC(7, 5));
        Rs.add(new RC(8, 5));
        Rs.add(new RC(9, 5));
        Rs.add(new RC(10, 5));
        Rs.add(new RC(11, 5));

//        testcases();

        N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; ++n) {
            br.readLine();
            strs = br.readLine().split(" ");

            for (String str : strs) {
//                bw.write(str + '\n');
                char c = str.charAt(0);
                boolean IsCW = (str.charAt(1) == '+') ? true : false;

                switch (c) {
                    case 'U': // U: 윗 면,
                        rotateU(IsCW);
                        break;
                    case 'D': // D: 아랫 면,
                        rotateD(IsCW);
                        break;
                    case 'F': // F: 앞 면,
                        rotateF(IsCW);
                        break;
                    case 'B': // B: 뒷 면,
                        rotateB(IsCW);
                        break;
                    case 'L': // L: 왼쪽 면,
                        rotateL(IsCW);
                        break;
                    case 'R': // R: 오른쪽 면
                        rotateR(IsCW);
                        break;
                }

//                printCube();
            }

            printMyeon(0, 3);
            resetCube();
        }

        bw.close();
    }

    void rotateMyeon(int sr, int sc, boolean IsCW) {
        Iterator<RC> iterDelta = (IsCW) ? qDeltaMyeon.iterator() : qDeltaMyeon.descendingIterator();
        Iterator<RC> iter = (IsCW) ? qMyeon.iterator() : qMyeon.descendingIterator();

        while (iterDelta.hasNext()) {
            RC drc = iterDelta.next();
            RC rc = iter.next();
            rc.set(sr + drc.r, sc + drc.c);
        }

        rotate((IsCW) ? qMyeon.descendingIterator() : qMyeon.iterator());
    }

    void rotate(Iterator<RC> iter) {
        RC rc = iter.next();
        int prevR = rc.r;
        int prevC = rc.c;
        int firstValue = cube[prevR][prevC].intValue();
        int crntR = 0;
        int crntC = 0;

        while (iter.hasNext()) {
            rc = iter.next();
            crntR = rc.r;
            crntC = rc.c;
            cube[prevR][prevC].set(cube[crntR][crntC].intValue());
            prevR = crntR;
            prevC = crntC;
        }

        cube[crntR][crntC].set(firstValue);
    }

    // U: 윗 면,
    // 두 번째 문자는 돌린 방향이다.
    // +인 경우에는 시계 방향 (그 면을 바라봤을 때가 기준),
    // -인 경우에는 반시계 방향
    void rotateU(boolean IsCW) throws IOException {
        rotate(!IsCW ? Us.descendingIterator() : Us.iterator());
        rotate(!IsCW ? Us.descendingIterator() : Us.iterator());
        rotate(!IsCW ? Us.descendingIterator() : Us.iterator());
        rotateMyeon(0, 3, !IsCW);
        rotateMyeon(0, 3, !IsCW);
    }

    // D: 아랫 면,
    void rotateD(boolean IsCW) throws IOException {
        rotate(IsCW ? Ds.descendingIterator() : Ds.iterator());
        rotate(IsCW ? Ds.descendingIterator() : Ds.iterator());
        rotate(IsCW ? Ds.descendingIterator() : Ds.iterator());
        rotateMyeon(6, 3, !IsCW);
        rotateMyeon(6, 3, !IsCW);
    }

    // F: 앞 면,
    void rotateF(boolean IsCW) throws IOException {
        rotate(IsCW ? Fs.descendingIterator() : Fs.iterator());
        rotate(IsCW ? Fs.descendingIterator() : Fs.iterator());
        rotate(IsCW ? Fs.descendingIterator() : Fs.iterator());
        rotateMyeon(3, 3, !IsCW);
        rotateMyeon(3, 3, !IsCW);
    }

    // B: 뒷 면,
    void rotateB(boolean IsCW) throws IOException {
        rotate(!IsCW ? Bs.descendingIterator() : Bs.iterator());
        rotate(!IsCW ? Bs.descendingIterator() : Bs.iterator());
        rotate(!IsCW ? Bs.descendingIterator() : Bs.iterator());
        rotateMyeon(9, 3, !IsCW);
        rotateMyeon(9, 3, !IsCW);
    }

    // L: 왼쪽 면,
    void rotateL(boolean IsCW) throws IOException {
        rotate(IsCW ? Ls.descendingIterator() : Ls.iterator());
        rotate(IsCW ? Ls.descendingIterator() : Ls.iterator());
        rotate(IsCW ? Ls.descendingIterator() : Ls.iterator());
        rotateMyeon(3, 0, !IsCW);
        rotateMyeon(3, 0, !IsCW);
    }

    // R: 오른쪽 면이다.
    void rotateR(boolean IsCW) throws IOException {
        rotate(!IsCW ? Rs.descendingIterator() : Rs.iterator());
        rotate(!IsCW ? Rs.descendingIterator() : Rs.iterator());
        rotate(!IsCW ? Rs.descendingIterator() : Rs.iterator());
        rotateMyeon(3, 6, !IsCW);
        rotateMyeon(3, 6, !IsCW);
    }

    void printMyeon(int sr, int sc) throws IOException {
        for (int r = sr; r < sr + 3; ++r) {
            for (int c = sc; c < sc + 3; ++c) {
                bw.write((char) cube[r][c].get());
            }
            bw.newLine();
        }
        bw.flush();
    }

    void initCube() {
        cube = new AtomicInteger[12][12];

        // 윗 면은 흰색, w
        cube[0][3] = new AtomicInteger('w');
        cube[0][4] = new AtomicInteger('w');
        cube[0][5] = new AtomicInteger('w');
        cube[1][3] = new AtomicInteger('w');
        cube[1][4] = new AtomicInteger('w');
        cube[1][5] = new AtomicInteger('w');
        cube[2][3] = new AtomicInteger('w');
        cube[2][4] = new AtomicInteger('w');
        cube[2][5] = new AtomicInteger('w');

        // 앞 면은 빨간색, r
        cube[3][3] = new AtomicInteger('r');
        cube[3][4] = new AtomicInteger('r');
        cube[3][5] = new AtomicInteger('r');
        cube[4][3] = new AtomicInteger('r');
        cube[4][4] = new AtomicInteger('r');
        cube[4][5] = new AtomicInteger('r');
        cube[5][3] = new AtomicInteger('r');
        cube[5][4] = new AtomicInteger('r');
        cube[5][5] = new AtomicInteger('r');

        // 아랫 면은 노란색, y
        cube[6][3] = new AtomicInteger('y');
        cube[6][4] = new AtomicInteger('y');
        cube[6][5] = new AtomicInteger('y');
        cube[7][3] = new AtomicInteger('y');
        cube[7][4] = new AtomicInteger('y');
        cube[7][5] = new AtomicInteger('y');
        cube[8][3] = new AtomicInteger('y');
        cube[8][4] = new AtomicInteger('y');
        cube[8][5] = new AtomicInteger('y');

        // 뒷 면은 오렌지색, o
        cube[9][3] = new AtomicInteger('o');
        cube[9][4] = new AtomicInteger('o');
        cube[9][5] = new AtomicInteger('o');
        cube[10][3] = new AtomicInteger('o');
        cube[10][4] = new AtomicInteger('o');
        cube[10][5] = new AtomicInteger('o');
        cube[11][3] = new AtomicInteger('o');
        cube[11][4] = new AtomicInteger('o');
        cube[11][5] = new AtomicInteger('o');

        // 뒷변 카피본
        // 9,3~11,5 와 3,9~5,11까지의 점은 사실 같은 점
        // 하지만, 2창원 배열에 정육면체를 펼쳤기 때문에,
        // 2개가 생긴 것 뿐임
        // 윗면과 만나는 점을 기준으로 같은 점을 찾아 보자

        // 0,3과 만나는 점
        cube[3][11] = cube[11][3];
        cube[3][10] = cube[11][4];
        cube[3][9] = cube[11][5];

        cube[4][11] = cube[10][3];
        cube[4][10] = cube[10][4];
        cube[4][9] = cube[10][5];

        cube[5][11] = cube[9][3];
        cube[5][10] = cube[9][4];
        cube[5][9] = cube[9][5];

        // 왼쪽 면은 초록색, g
        cube[3][0] = new AtomicInteger('g');
        cube[3][1] = new AtomicInteger('g');
        cube[3][2] = new AtomicInteger('g');
        cube[4][0] = new AtomicInteger('g');
        cube[4][1] = new AtomicInteger('g');
        cube[4][2] = new AtomicInteger('g');
        cube[5][0] = new AtomicInteger('g');
        cube[5][1] = new AtomicInteger('g');
        cube[5][2] = new AtomicInteger('g');

        // 오른쪽 면은 파란색, b
        cube[3][6] = new AtomicInteger('b');
        cube[3][7] = new AtomicInteger('b');
        cube[3][8] = new AtomicInteger('b');
        cube[4][6] = new AtomicInteger('b');
        cube[4][7] = new AtomicInteger('b');
        cube[4][8] = new AtomicInteger('b');
        cube[5][6] = new AtomicInteger('b');
        cube[5][7] = new AtomicInteger('b');
        cube[5][8] = new AtomicInteger('b');

    }

    void resetCube() {
        // 윗 면은 흰색, w
        cube[0][3].set('w');
        cube[0][4].set('w');
        cube[0][5].set('w');
        cube[1][3].set('w');
        cube[1][4].set('w');
        cube[1][5].set('w');
        cube[2][3].set('w');
        cube[2][4].set('w');
        cube[2][5].set('w');

        // 앞 면은 빨간색, r
        cube[3][3].set('r');
        cube[3][4].set('r');
        cube[3][5].set('r');
        cube[4][3].set('r');
        cube[4][4].set('r');
        cube[4][5].set('r');
        cube[5][3].set('r');
        cube[5][4].set('r');
        cube[5][5].set('r');

        // 아랫 면은 노란색, y
        cube[6][3].set('y');
        cube[6][4].set('y');
        cube[6][5].set('y');
        cube[7][3].set('y');
        cube[7][4].set('y');
        cube[7][5].set('y');
        cube[8][3].set('y');
        cube[8][4].set('y');
        cube[8][5].set('y');

        // 뒷 면은 오렌지색, o
        cube[9][3].set('o');
        cube[9][4].set('o');
        cube[9][5].set('o');
        cube[10][3].set('o');
        cube[10][4].set('o');
        cube[10][5].set('o');
        cube[11][3].set('o');
        cube[11][4].set('o');
        cube[11][5].set('o');

        // 뒷변 카피본
        // 9,3~11,5 와 3,9~5,11까지의 점은 사실 같은 점
        // 하지만, 2창원 배열에 정육면체를 펼쳤기 때문에,
        // 2개가 생긴 것 뿐임
        // 윗면과 만나는 점을 기준으로 같은 점을 찾아 보자

        // 0,3과 만나는 점
        cube[3][11] = cube[11][3];
        cube[3][10] = cube[11][4];
        cube[3][9] = cube[11][5];

        cube[4][11] = cube[10][3];
        cube[4][10] = cube[10][4];
        cube[4][9] = cube[10][5];

        cube[5][11] = cube[9][3];
        cube[5][10] = cube[9][4];
        cube[5][9] = cube[9][5];

        // 왼쪽 면은 초록색, g
        cube[3][0].set('g');
        cube[3][1].set('g');
        cube[3][2].set('g');
        cube[4][0].set('g');
        cube[4][1].set('g');
        cube[4][2].set('g');
        cube[5][0].set('g');
        cube[5][1].set('g');
        cube[5][2].set('g');

        // 오른쪽 면은 파란색, b
        cube[3][6].set('b');
        cube[3][7].set('b');
        cube[3][8].set('b');
        cube[4][6].set('b');
        cube[4][7].set('b');
        cube[4][8].set('b');
        cube[5][6].set('b');
        cube[5][7].set('b');
        cube[5][8].set('b');
    }

    void printCube() throws IOException {
        for (int i = 0; i < cube.length; ++i) {
            for (int j = 0; j < cube[0].length; ++j) {
                AtomicInteger dot = cube[i][j];
                char c = (dot == null) ? '_' : (char) dot.intValue();

                String str;
                if (dot == null) {
//                    str = String.format(" %2d,%2d %c ", i, j, c);
                    str = "         ";
                } else {
                    str = String.format("[%2d,%2d %c]", i, j, c);
                }

                bw.write(str);
            }
            bw.newLine();
            bw.newLine();
        }
        bw.flush();
        bw.newLine();
        bw.newLine();
        bw.newLine();
    }

    void testcases() throws IOException {
        bw.write("Initial cube\n");
        printCube();

        bw.write("print myeon\n");
        printMyeon(0, 3);

        bw.write("rotate F+\n");
        rotateF(true);
        printCube();
        bw.write("rotate F-\n");
        rotateF(false);
        printCube();

        bw.write("rotate B+\n");
        rotateB(true);
        printCube();
        bw.write("rotate B-\n");
        rotateB(false);
        printCube();

        bw.write("rotate U+\n");
        rotateU(true);
        printCube();
        bw.write("rotate U-\n");
        rotateU(false);
        printCube();

        bw.write("rotate D+\n");
        rotateD(true);
        printCube();
        bw.write("rotate D-\n");
        rotateD(false);
        printCube();

        bw.write("rotate L+\n");
        rotateL(true);
        printCube();
        bw.write("rotate L-\n");
        rotateL(false);
        printCube();

        bw.write("rotate R+\n");
        rotateR(true);
        printCube();
        bw.write("rotate R-\n");
        rotateR(false);
        printCube();


//         U: 윗 면, F: 앞 면,
//         U+ F+ F- U-
//         U판 회전 테스트를 위한 A~H
        cube[0][3].set('A');
        cube[0][4].set('B');
        cube[0][5].set('C');
        cube[1][5].set('D');
        cube[2][5].set('E');
        cube[2][4].set('F');
        cube[2][3].set('G');
        cube[1][3].set('H');
        printCube();

        bw.write("rotate U+ F+ F- U-\n");
        rotateU(true);
        printCube();
        rotateF(true);
        printCube();
        rotateF(false);
        printCube();
        rotateU(false);
        printCube();
        resetCube();


//         D: 아랫 면, L: 왼쪽 면
//         D+ L+ L- D-
//         D판 회전 테스트를 위한 A~H
        cube[6][3].set('A');
        cube[6][4].set('B');
        cube[6][5].set('C');
        cube[7][5].set('D');
        cube[8][5].set('E');
        cube[8][4].set('F');
        cube[8][3].set('G');
        cube[7][3].set('H');
        // L판
        cube[3][0].set('1');
        cube[3][1].set('2');
        cube[3][2].set('3');
        cube[4][2].set('4');
        cube[5][2].set('5');
        cube[5][1].set('6');
        cube[5][0].set('7');
        cube[4][0].set('8');
        printCube();
        bw.write("rotate D+ L+ L- D-\n");
        rotateD(true);
        printCube();
        rotateL(true);
        printCube();
        rotateL(false);
        printCube();
        rotateD(false);
        printCube();
        resetCube();

//         B: 뒷 면, R: 오른쪽 면
//         B+ R+ R- B-
//         B판 회전 테스트를 위한 A~H
        cube[3][9].set('A');
        cube[3][10].set('B');
        cube[3][11].set('C');
        cube[4][11].set('D');
        cube[5][11].set('E');
        cube[5][10].set('F');
        cube[5][9].set('G');
        cube[4][9].set('H');
        printCube();
        bw.write("rotate B+ R+ R- B-\n");
        rotateB(true);
        printCube();
        rotateR(true);
        printCube();
        rotateR(false);
        printCube();
        rotateB(false);
        printCube();
        resetCube();


        bw.write("\n");
        bw.write("rotate F CW\n");
        rotateF(true);
        printCube();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}