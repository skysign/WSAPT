import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int M;
    int N;
    String[] strs;
    boolean[] primes;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        M = Integer.parseInt(strs[0]);
        N = Integer.parseInt(strs[1]);
        primes = new boolean[N + 1];

        Arrays.fill(primes, true);

        primes[0] = false;
        primes[1] = false;

        for (int i = 0; i <= N; ++i) {
            if (!primes[i])
                continue;

            for (int j = 2; j * i <= N; ++j) {
                primes[j * i] = false;
            }
        }

        for (int i = M; i <= N; ++i) {
            if (primes[i]) {
                bw.write(String.valueOf(i) + '\n');
            }
        }

        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}