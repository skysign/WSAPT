import java.util.Arrays;
import java.util.Scanner;

/**
 * TRIPATHCNT 삼각형 위의 최대 경로 수 세기 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/TRIPATHCNT
 * 제출링크 : https://algospot.com/judge/submission/detail/655903
 *
 * 문제의 일부분을 2차원 DP로 푼 결과를 가지고,
 * 다시 한번 2차원 DP로 풀어야 하는 문제였습니다.
 */
public class Main {
    Scanner sc = new Scanner(System.in);

    public void solve() {
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int[][] map = new int[N+1][N+1];

            for(int i=1; i<=N; ++i) {
                for(int j=1; j<=i; ++j) {
                    map[i][j] = sc.nextInt();
                }
            }

            int r = 0;
            r = TRIPATHCNT(map, N);
            System.out.println(r);
        }
    }

    public int maxSum_rec(int[][] map, int idxI, int idxJ, int N, int[][] dp) {
        int r = 0;
        int i = idxI;
        int j = idxJ;

        if(i == N) {
            dp[i][j] = map[i][j];
            return dp[i][j];
        }

        if(dp[i][j] != -1)
            return dp[i][j];

        dp[i][j] = Math.max(maxSum_rec(map, idxI+1, idxJ, N, dp),
                maxSum_rec(map, idxI+1, idxJ+1, N, dp)) + map[i][j];

        return dp[i][j];
    }

    public int TRIPATHCNT(int[][] map, int N) {
        int[][] dp = new int[N+1][N+1];
        fill2D(dp, -1);
        int r = maxSum_rec(map, 1, 1, N, dp);

        int[][] dp_path = new int[N+1][N+1];
        fill2D(dp_path, -1);
        Arrays.fill(dp_path[N], 1);

        return countPath(dp, dp_path, 1, 1, N);
    }

    public int countPath(int[][] dp, int[][] dp_path, int idxI, int idxJ, int N) {
        int i = idxI;
        int j = idxJ;

        if(i == N) {
            return dp_path[i][j];
        }

        if(dp_path[i][j] != -1)
            return dp_path[i][j];

        int r = 0;
        if(dp[i+1][j] >= dp[i+1][j+1])
            r += countPath(dp, dp_path, i+1, j, N);
        if(dp[i+1][j] <= dp[i+1][j+1])
            r += countPath(dp, dp_path, i+1, j+1, N);

        dp_path[i][j] = r;

        return r;
    }

    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public void fill3D(int[][][] _3D, int v) {
        for(int[][] _2D: _3D) {
            for(int[] _1D: _2D) {
                Arrays.fill(_1D, v);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}