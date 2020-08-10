import java.util.Scanner;

/**
 * BOJ 11722번 가장 긴 감소하는 부분 수열
 *
 * 문제링크 : https://www.acmicpc.net/problem/11722
 *
 * 유튜브 문제 풀이
 * https://youtu.be/66BcgtGgZac
 *
 * 자바소스 : https://bit.ly/3irBggS
 */

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int nLength = sc.nextInt();
        int[] dt = new int[nLength];
        int[] dp = new int[nLength];

        for(int i=0; i<nLength; ++i)
            dt[i] = sc.nextInt();

        dp[0] = 1;
        int r = dp[0];

        for(int i=1; i<nLength; ++i) {
            for(int j=0; j<i; ++j) {
                if(dt[j] > dt[i])
                    dp[i] = Math.max(dp[i], dp[j]);
            }

            dp[i] += 1;
            r = Math.max(r, dp[i]);
        }

        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
