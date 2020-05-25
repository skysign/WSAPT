import java.util.Scanner;

/**
 * BOJ 11052번 카드 구매하기
 * 문제링크 : https://www.acmicpc.net/problem/11052
 * 제출링크 : https://www.acmicpc.net/source/20009261
 * 문제풀이 : https://skysign.tistory.com/242
 */
public class Main {
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    int N = sc.nextInt();
	    int[] dt = new int[N+1];
        int[] dp = new int[N+1];

        for(int i=0; i<N; ++i) {
            dt[i+1] = sc.nextInt();
        }

        dp[1] = dt[1];

        for(int i=2; i<=N; ++i) {
            for(int j=1; j<=N; ++j) {
                if(i-j > 0) {
                    dp[i] = Math.max(dp[i-j] + dt[j], dp[i]);
                }
            }

            if(i<=N)
                dp[i] = Math.max(dp[i], dt[i]);
        }

        System.out.println(dp[N]);
    }
}
