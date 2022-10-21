import java.io.*;
import java.util.ArrayList;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, K;
    int[] A;

    int k0 = 0;
    int ans = 0;

    int idxRobot = 0;
    String[] strs;

    class Robot {
        public Kan mKan;
        public int midxRobot;

        public Robot(Kan kan) {
            mKan = kan;
            midxRobot = ++idxRobot;
        }

        public void moveNextKan() throws Exception {
            if (mKan.next.mNaegu > 0 && mKan.next.mRobot == null) {
                mKan.mRobot = null;
                mKan.next.loadRobot(this);
            }
        }
    }

    class Kan {
        public int mNum, mNaegu;
        public Kan prev;
        public Kan next;
        public Robot mRobot;

        public Kan(int num, int naegu) {
            mNum = num;
            mNaegu = naegu;
            mRobot = null;
        }

        public void loadRobot(Robot robot) throws Exception {
            if (mRobot != null)
                throw new Exception("mRobot != null");

            if (mNaegu <= 0)
                throw new Exception("mNaegu <= 0");

            mRobot = robot;
            mRobot.mKan = this;
            --mNaegu;

            if (mNaegu == 0) {
                ++k0;
            }
        }
    }

    Kan kanOli;
    Kan kanNaeli;
    Kan kan2N;

    public void printBelt() throws IOException {
//        bw.write(String.format("DnaKye: %d", ans));
//        bw.write(String.valueOf('\n'));
//
//        Kan crnt = kanOli;
//
//        while (crnt != kanNaeli) {
//            bw.write(String.format("%4d ", crnt.mNum));
//            crnt = crnt.next;
//        }
//
//        bw.write(String.format("%4d ", kanNaeli.mNum));
//
//        crnt = kanNaeli.next;
//
//        while (crnt != kanOli) {
//            bw.write(String.format("%4d ", crnt.mNum));
//            crnt = crnt.next;
//        }
//
//        bw.write(String.valueOf('\n'));
//
//        crnt = kanOli;
//
//        while (crnt != kanNaeli) {
//            bw.write(String.format("%4d ", crnt.mNaegu));
//            crnt = crnt.next;
//        }
//
//        bw.write(String.format("%4d ", kanNaeli.mNaegu));
//
//        crnt = kanNaeli.next;
//
//        while (crnt != kanOli) {
//            bw.write(String.format("%4d ", crnt.mNaegu));
//            crnt = crnt.next;
//        }
//
//        bw.write(String.valueOf('\n'));
//
//        crnt = kanOli;
//
//        while (crnt != kanNaeli) {
//            if (crnt.mRobot != null) {
//                bw.write(String.format("r%03d ", crnt.mRobot.midxRobot));
//            }
//            else {
//                bw.write(String.format("     "));
//            }
//
//            crnt = crnt.next;
//        }
//
//        if (kanNaeli.mRobot != null) {
//            bw.write(String.format("r%03d ", crnt.mRobot.midxRobot));
//        }
//        else {
//            bw.write(String.format("     "));
//        }
//
//        crnt = kanNaeli.next;
//
//        while (crnt != kanOli) {
//            if (crnt.mRobot != null) {
//                bw.write(String.format("r%03d ", crnt.mRobot.midxRobot));
//            }
//            else {
//                bw.write(String.format("     "));
//            }
//
//            crnt = crnt.next;
//        }
//
//        bw.write(String.valueOf('\n'));
//        bw.flush();
    }

    public void solve() throws Exception {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);
        K = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");
        A = new int[strs.length];

        for (int i = 0; i < strs.length; ++i) {
            A[i] = Integer.parseInt(strs[i]);
        }

        kanOli = new Kan(1, A[1 - 1]);
        Kan prevKan = kanOli;
        Kan crnt = null;

        for (int n = 2; n < 2 * N; ++n) {
            crnt = new Kan(n, A[n - 1]);
            prevKan.next = crnt;
            crnt.prev = prevKan;
            prevKan = crnt;

            if (N == n) {
                kanNaeli = crnt;
            }
        }

        kan2N = new Kan(2 * N, A[2 * N - 1]);
        crnt.next = kan2N;
        kan2N.prev = crnt;

        kan2N.next = kanOli;
        kanOli.prev = kan2N;

        printBelt();

        while (K > k0) {
            ++ans;

            // 내리는 칸에 있는 로봇을 내린다.
            if (kanNaeli.mRobot != null) {
                kanNaeli.mRobot = null;
            }

            // 컨베이어 벨트 1칸 회전
            kanOli = kanOli.prev;
            kanNaeli = kanNaeli.prev;

            // 내리는 칸에 있는 로봇을 내린다.
            if (kanNaeli.mRobot != null) {
                kanNaeli.mRobot = null;
            }

            // 이동 가능하면 로봇 한칸 이동
            // 로봇은 1~N 번 칸에만 위치할 수 있음
            crnt = kanNaeli.prev; // 내리는 칸에 있는 로봇은 이미 내렸기 때문에, 내리는 칸의 이전칸
            while (crnt != kanOli) {
                if (crnt.mRobot != null) {
                    crnt.mRobot.moveNextKan();
                }

                crnt = crnt.prev;
            }

            // 내리는 칸에 있는 로봇을 내린다.
            if (kanNaeli.mRobot != null) {
                kanNaeli.mRobot = null;
            }

            // 로봇을 올리는 칸에 올린다.
            if (kanOli.mNaegu > 0 && kanOli.mRobot == null) {
                Robot robot = new Robot(kanOli);
                kanOli.loadRobot(robot);
            }

            printBelt();
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.solve();
    }
}