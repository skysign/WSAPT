import java.util.Scanner;

/**
 * 9095번 1, 2, 3 더하기 / BAEKJOON ONLINE JUDGE / acmicpc.net
 * 문제링크 : https://www.acmicpc.net/problem/9095
 * 제출링크 : https://www.acmicpc.net/source/17177201
 */
public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            int r = ans(N);
            System.out.println(r);
        }
    }

    public int ans(int N) {
        /**
         * d_1 = 1 : 1
         * d_2 = 1+1 : 2
         * d_3 = 1+1+1, 2+1, 1+2, 3: 4
         * d_4 = 1+1+1+1, 1+2+1, 2+1+1, 1+1+2, 2+2, 1+3, 3+1 : 7
         * d_5 = 13 개
         * d_4 하고 d_5는 아래 점화식이 성립하는 것을 손으로 풀어서 확인해 봤구요
         * 왠지 느낌에, d_i = d_i-1 + d_i-2 + d_i-1 인 것 같습니다. d_7 으로 확인해 보고, 44가 나오면
         * 이 점화식으로 풀어 보겠습니다.
         */
        int[] dp = new int[N+1];

        if(N+1 >= 1+1)
            dp[1] = 1;

        if(N+1 >= 2+1)
            dp[2] = 2;

        if(N+1 >= 3+1)
            dp[3] = 4;

        for(int n=4; n<=N; ++n) {
            dp[n] = dp[n-1] + dp[n-2] + dp[n-3];
        }

        return dp[N];
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
