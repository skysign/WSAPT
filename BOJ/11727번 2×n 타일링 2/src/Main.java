import java.util.Scanner;

/**
 * BOJ 11727번 2×n 타일링 2
 * 문제링크 : https://www.acmicpc.net/problem/11727
 * 제출링크 : https://www.acmicpc.net/source/20008021
 * 문제풀이 : https://skysign.tistory.com/241
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N +1];

        dp[1] = 1;

        if (N > 1) {
            dp[2] = 3;
        }

        for (int i=3; i<=N; ++i) {
            dp[i] = (2*dp[i-2] + dp[i-1]) % 10007;
        }

        System.out.println(dp[N]);
    }
}
