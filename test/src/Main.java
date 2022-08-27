import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int maxN = 0;
    int[] ns;
    int[] dp = new int[10 + 1];
    String[] strs;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        ns = new int[N];

        for (int i = 0; i < N; ++i) {
            ns[i] = Integer.parseInt(br.readLine());
            maxN = Math.max(maxN, ns[i]);
        }

        dp[0] = 0;
        dp[1] = 1;  // adding 1 to dp[0]
        dp[2] = (dp[0] + 1) + (dp[1]);  // adding 1 to dp[1], adding 2 to dp[0]
        dp[3] = (dp[3-3] + 1) + (dp[1]) + (dp[2]); // adding 3 to dp[0], adding 2 to dp[1], adding 1 to dp[2]

        for (int i = 4; i <= maxN; ++i) {
            dp[i] = (dp[i - 1]) + (dp[i - 2]) + (dp[i - 3]);
        }

        for (int n : ns) {
            bw.write(String.valueOf(dp[n]) + '\n');
        }

        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}