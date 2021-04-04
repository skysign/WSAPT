import java.util.Scanner;
/**
 * BOJ 2133번 타일 채우기
 *
 * 문제링크 : https://www.acmicpc.net/problem/2133
 *
 * 유튜브 문제 풀이
 * https://youtu.be/E_-dFdvy288
 *
 * 자바소스 : https://bit.ly/2QDEEtd
 */
public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int _N = Math.max(8+1, N+1);
        int[] dp = new int[_N];

        dp[0] = 0; // 제외 하고
        dp[1] = 0; // 3x1 채울 수 없는 크기
        dp[2] = 3; // 3x2
        dp[3] = 0;
        dp[4] = dp[2]*3 + 2;
        // dp[2]*3 에서, *3은 dp[4-2] 즉, dp[2]값 입니다
        dp[5] = 0;
        dp[6] = dp[4]*3 + dp[2]*2 + 2;
        // dp[i] = dp[i-2] * dp[(i - (i-2))];
        dp[7] = 0;
        dp[8] = dp[6]*3 + dp[4]*2 + dp[2]*2 +2;
        // dp[6]*3 에서, *3은 3x2를 채울 수 있는 경우의 수, 즉 dp[2]을 의미한다
        // dp[4]*2 에서, *2는 3x4를 채우는 예외 모양 2가지
        // dp[2]*2 에서, *2는 3x6을 채우는 예외 모양 2가지
        // +2 에서 +2는, 3x(j) j는 짝 수, 4<=j 일 때, 예외 모양이 2개씩 늘어나게 된다
        // 늘어나는 예외 모양에 대한 설명 https://lmcoa15.tistory.com/42

        // 점화식
        // dp[i] += dp[i-2] * 3
        // dp[i] = for(j=i-2; j>=2; j-=2) dp[i] = dp[j] * 2;
        // dp[i] += 2;

        for(int i=10; i<=N; i+=2) {
            dp[i] += dp[i-2] * 3;
            for(int j=i-4; j>=2; j-=2)
                dp[i] += dp[j] * 2;
            dp[i] += 2;
        }

        System.out.println(dp[N]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
