import java.util.Scanner;

/**
 * JUMPGAME 외발 뛰기 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/JUMPGAME
 * 제출링크 : https://algospot.com/judge/submission/detail/654848
 */
public class Main {
    Scanner sc = new Scanner(System.in);

    public void solve() {
        int T = sc.nextInt();
        int r = 0;

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];

            for(int i=0; i<N; ++i) {
                for(int j=0; j<N; ++j) {
                    map[i][j] = sc.nextInt();
                }
            }

            boolean[][] dp = new boolean[N][N];
            boolean b = dojump(0, 0, N, map, dp);

            System.out.println((b)? "YES": "NO");
        }
    }

    public boolean dojump(int i, int j, int N, int[][] map, boolean[][] dp) {
        int di = i;
        int dj = j;

        boolean b = ((0<= di) && (di < N) && (0<= dj) && (dj < N));
        if(!b)
            return false;

        if(dp[di][dj])
            return false;

        dp[di][dj] = true;

        if((di == N-1) && (dj == N-1))
            return true;

        return dojump(di +map[i][j], dj, N, map, dp) || dojump(di , dj +map[i][j], N, map, dp);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}