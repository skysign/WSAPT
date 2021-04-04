import java.util.Scanner;

/**
 * TRIANGLEPATH 삼각형 위의 최대 경로 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/TRIANGLEPATH
 * 제출링크 : https://algospot.com/judge/submission/detail/655087
 *
 * maxSum() 이중 루프를 이용한 DP 풀이
 * maxSum_rec() recursion과 dP를 이용한 풀이
 */
public class Main {
    Scanner sc = new Scanner(System.in);

    public void solve() {
        int T = sc.nextInt();
        int r = 0;

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int[][] map = new int[N+1][N+1];
            for(int i=1; i<=N; ++i) {
                for(int j=1; j<=i; ++j) {
                    map[i][j] = sc.nextInt();
                }
            }

            r = maxSum(map, N);
            System.out.println(r);

            int[][] dp = new int[N+1][N+1];
            r = maxSum_rec(map, 1, 1, N, dp);
            System.out.println(r);
        }
    }

    public int maxSum(int[][] map, int N) {
        int[][] dp = new int[N+1][N+1];

        for(int i=1; i<=N; ++i) {
            for(int j=1; j<=i; ++j) {
                dp[i][j] = Math.max(dp[i-1][j] + map[i][j], dp[i-1][j-1] + map[i][j]);
            }
        }

        int max = 0;
        for(int j=1; j<=N; ++j)
            max = Math.max(max, dp[N][j]);

        return max;
    }

    public int maxSum_rec(int[][] map, int idxI, int idxJ, int N, int[][] dp) {
        int r = 0;
        int i = idxI;
        int j = idxJ;

        if(i == N) {
            dp[i][j] = map[i][j];
            return dp[i][j];
        }

        if(dp[i][j] != 0)
            return dp[i][j];

        dp[i][j] = Math.max(maxSum_rec(map, idxI+1, idxJ, N, dp),
                maxSum_rec(map, idxI+1, idxJ+1, N, dp)) + map[i][j];

        return dp[i][j];
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}