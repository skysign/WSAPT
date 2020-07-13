import java.util.Scanner;

/**
 * BOJ 2156번 포도주 시식
 * 문제링크 : https://www.acmicpc.net/problem/2156
 *
 * 유튜브 문제 풀이
 * https://youtu.be/ZirZOCx9Rko
 *
 * 자바소스 :
 */

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        int[] dt = new int[n];
        int[] dp = new int[n+1];

        for (int i = 0; i < n; i++) {
            dt[i] = sc.nextInt();
        }

        dp[1] = dt[0];

        if(n <= 1) {
            System.out.println(dp[n]);
            return;
        }

        dp[2] = dp[1] + dt[1];

        if(n <= 2) {
            System.out.println(dp[n]);
            return;
        }

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-3] + dt[i-2] + dt[i-1];
            dp[i] = Math.max(dp[i], dp[i-2] + dt[i-1]);
            dp[i] = Math.max(dp[i], dp[i-1]);
        }

        System.out.println(dp[n]);

        sc.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
