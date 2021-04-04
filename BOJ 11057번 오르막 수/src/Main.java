import java.util.Scanner;

/**
 * BOJ 11057번 오르막 수
 * 문제링크 : https://www.acmicpc.net/problem/11057
 * 제출링크 : https://www.acmicpc.net/source/20649662
 * 문제풀이 : https://skysign.tistory.com/256
 */

public class Main {
    public int N;

    final int MOD = 10007;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int r = 0;
        int[][] dp = new int[N+1][10];
        dp[0][0] = 1;

        for(int row=1; row<=N; ++row) {
            for(int col=0; col<10; ++col) {
                for(int prevCol=0; prevCol<10; ++prevCol)
                    if(prevCol <= col) {
                        dp[row][col] += dp[row-1][prevCol];
                    }

                dp[row][col] %= MOD;
            }
        }

        for(int idx=0; idx<10; ++idx) {
            r += dp[N][idx];
            r %= MOD;
        }

        System.out.println(r);
    }

    // Recursive로 풀면 답은 나오지만 시간 초과
    public int[] nxts = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    public int orumak(int n, int prevDigit) {
        if(0 == n)
            return nxts[prevDigit];

        int r = 0;

        for(int idx=prevDigit; idx<nxts.length; ++idx) {
            r += orumak(n-1, idx);
            r %= MOD;
        }

        return r;
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
