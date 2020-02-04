import java.util.Scanner;

/**
 * LIS Longest Increasing Sequence / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/LIS
 * 제출링크 : https://algospot.com/judge/submission/detail/655116
 */
public class Main {
    Scanner sc = new Scanner(System.in);

    public void solve() {
        int T = sc.nextInt();
        int r = 0;

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int[] map = new int[N+1];
            for(int i=1; i<=N; ++i) {
                map[i] = sc.nextInt();
            }

            r = LIS(map, N);

            System.out.println(r);
        }
    }

    public int LIS(int[] map, int N) {
        int[] dp = new int[N+1];

        for(int i=1; i<=N; ++i) {
            int max = 1;
            for(int k=1; k<i; ++k) {
                if(map[k] < map[i])
                    max = Math.max(max, dp[k]+1);
            }
            dp[i] = max;
        }

        int r = 0;
        for(int i=1; i<=N; ++i) {
            r = Math.max(r, dp[i]);
        }

        return r;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}