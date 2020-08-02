import java.util.Scanner;

/**
 * BOJ 11055번 가장 큰 증가 부분 수열
 *
 * 문제링크 : https://www.acmicpc.net/problem/11055
 *
 * 유튜브 문제 풀이
 *
 * 자바소스 :
 *
 */

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int nLength = sc.nextInt();
        int[] dt = new int[nLength];
        int[] dp = new int[nLength];

        for(int i=0; i<nLength; ++i) {
            dt[i] = sc.nextInt();
        }

        dp[0] = dt[0];
        int r = dp[0];

        for(int i=1; i<nLength; ++i) {
            for(int j=0; j<i; ++j) {
                if(dt[j] < dt[i]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }

            dp[i] += dt[i];
            r = Math.max(r, dp[i]);
        }

        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
