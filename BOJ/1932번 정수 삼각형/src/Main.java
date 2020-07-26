import java.util.Scanner;

/**
 * BOJ 1932번 정수 삼각형
 * 문제링크 : https://www.acmicpc.net/problem/1932
 *
 * 유튜브 문제 풀이
 *
 *
 * 자바소스 :
 */

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int nHeight = sc.nextInt();
        int[][] dt = new int[nHeight][nHeight];
        int[][] dp = new int[nHeight][nHeight];

        for(int i=0; i<nHeight; ++i) {
            for(int j=0; j<=i; ++j) {
                dt[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = dt[0][0];

        for(int i=1; i<nHeight; ++i) {
            for(int j=0; j<=i; ++j) {
                if(j > 0)
                    dp[i][j] = dp[i-1][j-1] + dt[i][j];
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + dt[i][j]);
            }
        }

        int r = dp[nHeight-1][0];
        for(int j=1; j<nHeight; ++j) {
            r = Math.max(r, dp[nHeight-1][j]);
        }

        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
