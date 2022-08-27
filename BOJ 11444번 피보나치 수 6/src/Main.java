import java.io.*;
import java.util.HashMap;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N;
    HashMap<Long, Long> hm;
    static long MOD = 1000000007;

    public void solve() throws IOException {
        N = Long.parseLong(br.readLine());
        hm = new HashMap<>();
        hm.put((long) 0, (long) 0);
        hm.put((long) 1, (long) 1);
        hm.put((long) 2, (long) 1);
        hm.put((long) 3, (long) 2);

        long r = fibo(N);

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