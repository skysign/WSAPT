import java.util.Scanner;

/**
 * LIS Longest Increasing Sequence / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/LIS
 * 제출링크 : https://algospot.com/judge/submission/detail/655189
 */
public class Main {
    Scanner sc = new Scanner(System.in);
    int[] cache;
    int n = 0;
    int[] S;

    public void solve() {
        int T = sc.nextInt();
        int r = 0;

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            n = N;
            S = new int[N];

            for(int i=0; i<N; ++i) {
                S[i] = sc.nextInt();
            }

//            r = LIS(S, N);
//            System.out.println(r);

            cache = new int[N+1];
            r = lis3(-1);
            System.out.println(r-1);
        }
    }

    public int LIS(int[] map, int N) {
        int[] dp = new int[N+1];

        for(int i=0; i<N; ++i) {
            int max = 1;
            for(int k=0; k<i; ++k) {
                if(map[k] < map[i])
                    max = Math.max(max, dp[k]+1);
            }
            dp[i] = max;
        }

        int r = 0;
        for(int i=0; i<N; ++i) {
            r = Math.max(r, dp[i]);
        }

        return r;
    }

    /**
     * 책 234페이지, 코드 8.12를 자바버전으로 구현한 메서드 입니다.
     * 코드 보시는 분들이 헷갈리지 않도록, ret를 제외하고는, 코드 8.12에서 사용된
     * 변수명과 아래 메서드에서 사용된 변수명이 같다면, 역할도 동일합니다.
     * @param start
     * @return
     */
    public int lis3(int start) {
        if(cache[start+1] != 0)
            return cache[start+1];

        int r = 1;
        cache[start+1] = r;

        for(int next=start+1; next<n; ++next) {
            if((start == -1) || (S[start] < S[next])) {
                r = Math.max(r, lis3(next)+1);
                cache[start+1] = r;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}