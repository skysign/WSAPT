import java.util.Scanner;

/**
 * 1463번 1로 만들기 / BAEKJOON ONLINE JUDGE / acmicpc.net
 * 문제링크 : https://www.acmicpc.net/problem/1463
 * 제출링크 : https://www.acmicpc.net/source/17152022
 */
public class Main {
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int N = sc.nextInt();
	    int[] dp = new int[N+1];

	    for(int n=2; n<=N; ++n) {
	        int t1 = Integer.MAX_VALUE;
            int t2 = Integer.MAX_VALUE;
            int t3 = Integer.MAX_VALUE;

            if(0 == n%3) {
                t1 = dp[n/3];
            }
            if(0 == n%2) {
                t2 = dp[n/2];
            }
            t3 = dp[n-1];

            dp[n] = Math.min(Math.min(t1, t2), t3) + 1;
        }

	    System.out.println(dp[N]);
    }
}
