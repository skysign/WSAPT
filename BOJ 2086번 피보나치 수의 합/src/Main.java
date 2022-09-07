import java.io.*;
import java.util.HashMap;
/**
 * BOJ 2086번 피보나치 수의 합
 *
 * 유튜브 문제 풀이: https://youtu.be/_zezT5g_33Q
 *
 * 문제링크: https://www.acmicpc.net/problem/2086
 *
 * 자바소스: https://bit.ly/3qioRl8
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long a, b;
    String[] strs;
    HashMap<Long, Long> hm;

    static long MOD = 1000000000;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        a = Long.parseLong(strs[0]);
        b = Long.parseLong(strs[1]);
        hm = new HashMap<>();
        hm.put((long) 1, (long) 1);
        hm.put((long) 2, (long) 1);
        hm.put((long) 3, (long) 2);

        long f1 = fibo(a + 1);
        long f2 = fibo(b + 2);
        long r = (f2 - f1 + MOD) % MOD;

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.close();
    }

    public long fibo(long idx) {
        if (hm.containsKey(idx)) {
            return hm.get(idx);
        }
        // idx가 even 짝수
        else if ((idx & 1) == 0) {
            long nIdx = idx / 2;
            long f1 = fibo(nIdx - 1);
            long f2 = fibo(nIdx);
            long tf = ((2 * f1) + f2) * f2;

            tf = tf % MOD;
            hm.put(idx, tf);

            return tf;
        }
        // idx가 odd 홀 수
        else {
            long nIdx = (idx + 1) / 2;
            long f1 = fibo(nIdx);
            long f2 = fibo(nIdx - 1);
            long tf = (f1 * f1) + (f2 * f2);
            tf = tf % MOD;

            tf = tf % MOD;
            hm.put(idx, tf);

            return tf;
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}