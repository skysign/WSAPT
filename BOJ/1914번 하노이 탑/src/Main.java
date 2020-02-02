import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * 1914번 하노이 탑 / BOJ
 * 문제링크 : https://www.acmicpc.net/problem/1914
 * 제출링크 : https://www.acmicpc.net/source/17360722
 *
 * IO속도를 올리기 위해서, BufferedWriter()나 Reader 클래스(아래 링크 참고)를 사용하면,
 * 메모리 초과로 오답 처리 됩니다. 주의하세요.
 * https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */

public class Main {
    Scanner sc = new Scanner(System.in);
//    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solve() throws IOException {
        int N = sc.nextInt();

//        long r = 0;
//        r = run(N, false); // 원판을 하나씩 옮기면서, 몇개 옮겨하는지 계산한다.
//        r = (long)Math.pow(2, N) - 1; // long 으로 2^N -1을 계산한다. N이 좀 커지면 long도 overflow가 발생한다.
                                        // N = 100인 경우에 답은 1267650600228229401496703205375
//        bw.write(r + "\n");
//        if(N<=20)
//            run(N, true);

        BigInteger r = BigInteger.valueOf(2);
        r = r.pow(N).subtract(BigInteger.valueOf(1));
        System.out.println(r);
//        bw.write(r + "\n");
        if(N<=20)
            run(N, true);

//        bw.flush();
//        bw.close();
    }

    public long run(int N, boolean bLog) throws IOException {
        long r = movePan(N, 1, 2,3, bLog);
        return r;
    }

    public long movePan(int OnePan, int from, int mid, int to, boolean bLog) throws IOException {
        if(1 == OnePan) {
            if(bLog) {
//                String log = String.format("%d %d\n", from, to);
//                bw.write(log);
                System.out.println(from + " " + to);
            }
            return 1;
        }

        long r = 0;
//        mid = getMid(from, to);
        r += movePan(OnePan -1, from, to, mid, bLog);
        if(bLog) {
//            String log = String.format("%d %d\n", from, to);
//            bw.write(log);
            System.out.println(from + " " + to);
        }
        r += movePan(OnePan -1, mid, from, to, bLog);

        return r;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}