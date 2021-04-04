import java.util.Arrays;
import java.util.Scanner;
/**
 * WILDCARD Wildcard / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/WILDCARD
 * 제출링크 : https://algospot.com/judge/submission/detail/655038
 */
public class Main {
    public final int FILENAME_LENGTH = 100;
    Scanner sc = new Scanner(System.in);

    public void solve() {
        int T = sc.nextInt();
        sc.nextLine();
        int r = 0;

        for(int t=0; t<T; ++t) {
            String W = sc.nextLine();
            int N = sc.nextInt();
            sc.nextLine();

            String[] Ss = new String[N];

            for(int i=0; i<N; ++i) {
                Ss[i] = sc.nextLine();
            }

            Arrays.sort(Ss);

            for(int i=0; i<N; ++i) {
                String S = Ss[i];
                int[][] dp = new int[FILENAME_LENGTH+1][FILENAME_LENGTH+1];
                r = match(W, 0, S, 0, dp);
                if(r>0)
                    System.out.println(S);
            }
        }
    }

    public int match(String W, int idxW, String S, int idxS, int[][] dp) {
        char[] Ws = W.toCharArray();
        char[] Ss = S.toCharArray();
        int dw = idxW;
        int ds = idxS;

        if(dp[idxW][idxS] != 0)
            return dp[idxW][idxS];

        while((dw < W.length()) && (ds < S.length()) &&
                ((Ws[dw] == '?') || (Ws[dw] == Ss[ds]))) {
            dw++;
            ds++;
        }

        if(dw == W.length()) {
            if(ds == S.length()) {
                dp[idxW][idxS] = 1;
                return dp[idxW][idxS];
            }
            else {
                dp[idxW][idxS] = -1;
                return dp[idxW][idxS];
            }
        }

        if(Ws[dw] == '*'){
            for(int i=ds; i<=S.length(); ++i) {
                int r = match(W, dw+1, S, i, dp);
                if(r>0) {
                    return 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}