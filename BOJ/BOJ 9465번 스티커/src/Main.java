import java.util.Scanner;

/**
 * BOJ 9465번 스티커
 *
 * 문제링크 : https://www.acmicpc.net/problem/9465
 *
 * 유튜브 문제풀이 : https://youtu.be/l_v00Wxk5y4
 *
 * 제출링크 : https://www.acmicpc.net/source/20705196
 */

public class Main {
    int[][] dp;
    int[][] dt;

    public void solve() {
        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();

        for(int n=0; n<N; ++n) {
            final int rows = 2;
            final int cols = sc.nextInt();

            dt = new int[rows][cols];
            for(int i=0; i<rows; ++i) {
                for(int j=0; j<cols; ++j) {
                    dt[i][j] = sc.nextInt();
                }
            }

            dp = new int[rows+1][cols+1];

            dp[1][1] = dt[0][0];
            dp[2][1] = dt[1][0];

            for(int col = 2; col<=cols; ++col) {
                dp[0][col-1] = Math.max(dp[1][col-2], dp[2][col-2]);
                dp[1][col] = Math.max(dp[0][col-1], dp[2][col-1]) + dt[0][col-1];
                dp[2][col] = Math.max(dp[0][col-1], dp[1][col-1]) + dt[1][col-1];
            }

            System.out.println(Math.max(dp[1][cols], dp[2][cols]));
        }
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
