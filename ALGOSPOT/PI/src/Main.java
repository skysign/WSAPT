import java.util.Scanner;

/**
 * PI 원주율 외우기 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/PI
 * 제출링크 : https://algospot.com/judge/submission/detail/655695
 *
 * 만들어본 테스트 케이스 입니다.
 * 4
 * 3135722443
 * 57722313
 * 31355722
 * 57224433
 *
 * 20
 * 14
 * 14
 * 20
 *
 * 더 긴 테스트 케이스가 필요하신 분은, 아래 참고하세요.
 * https://algospot.com/judge/problem/read/PI 의 댓글에서 테스트케이스 링크 찾았습니다.
 * https://pastebin.com/rhMYwAN3
 */
public class Main {
    Scanner sc = new Scanner(System.in);

    public void solve() {
        int T = sc.nextInt();
        sc.nextLine();
        int r = 0;

        for(int t=0; t<T; ++t) {
            String str = sc.nextLine();
            int[] dp = new int[str.length()];

            r = pi(str.toCharArray(), 0, str.length(), dp);
            System.out.println(r);
        }
    }

    public int begL = 3;
    public int maxL = 5+1;

    public final int MAX = 987654321;

    public int pi(char[] S, int idx, int N, int[] dp) {
        if(idx == N)
            return 0;

        if(dp[idx] != 0)
            return dp[idx];

        int r = MAX;

        for(int L=begL; L<maxL; ++L) {
            if(idx+L <= N) {
                int r1 = difficulty(S, idx, idx+L);
                int r_pi = pi(S, idx+L, N, dp);
                r = Math.min(r, r1 + r_pi);
            }
        }

        dp[idx] = r;
        return r;
    }

    public int difficulty(char S[], int beg, int end) {
        boolean bD1 = true;
        for(int i=beg+1; (i<end) && (true == bD1); ++i) {
            if(S[i-1] != S[i])
                bD1 = false;
        }

        if(bD1)
            return 1;

        boolean bD2 = true;
        int delta = 1;
        for(int i=beg+1; (i<end) && (true == bD2); ++i) {
            if(S[i-1] != S[i]+delta)
                bD2 = false;
        }

        if(bD2)
            return 2;

        bD2 = true;
        delta = -1;
        for(int i=beg+1; (i<end) && (true == bD2); ++i) {
            if(S[i-1] != S[i]+delta)
                bD2 = false;
        }

        if(bD2)
            return 2;

        boolean bD4 = true;
        for(int i=beg+2; (i<end) && (true == bD4); ++i) {
            if(S[i-2] != S[i])
                bD4 = false;
        }

        if(bD4)
            return 4;
// #5
        boolean bD5 = true;
        delta = S[beg+1] - S[beg];
        for(int i=beg+1; (i<end) && (true == bD5); ++i) {
            if(!(S[i-1]+delta == S[i]))
                bD5 = false;
        }

        if(bD5)
            return 5;

        return 10;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}