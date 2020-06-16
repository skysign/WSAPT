/**
 * BOJ 1309번 동물원
 * 문제링크 : https://www.acmicpc.net/problem/1309
 * 제출링크 : https://www.acmicpc.net/source/20383098
 * 문제풀이 : https://skysign.tistory.com/254
 */
import java.util.Scanner;

public class Main {
    final int MOD = 9901;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] dp = new int[N+1][3];

        dp[0][0] = 1;
        for(int i=1; i<=N; ++i) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;
        }

        int r = dp[N][0] + dp[N][1] + dp[N][2];
        r %= MOD;

        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
