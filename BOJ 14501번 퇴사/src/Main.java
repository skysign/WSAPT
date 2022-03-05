import java.io.*;
/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 14501번 퇴사
 *
 * 유튜브 문제 풀이: https://youtu.be/YcIPGXdLCbc
 *
 * 문제링크: https://www.acmicpc.net/problem/14501
 *
 * 자바소스: https://bit.ly/34fRY17
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;
    int[] T; /** 필요한 상담 일수 */
    int[] P;
    int[] dp;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        dp = new int[N + 1 + 5];

        for (int n = 0; n < N; ++n) {
            strs = br.readLine().split(" ");
            T[n] = Integer.parseInt(strs[0]);
            P[n] = Integer.parseInt(strs[1]);
        }

        for (int j = N-1; j >= 0; --j) {
            int remainedDays = N - j; /** j일에 상담할 수 있는 남은 일수 */
            if (remainedDays >= T[j]) {
                dp[j] = Math.max(P[j] + dp[j + T[j]], dp[j+1]);
            } else {
                dp[j] = dp[j + 1];
            }
        }

        int r = dp[0];
        bw.write(String.valueOf(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}