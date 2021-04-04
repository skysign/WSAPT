import java.util.Scanner;

/**
 * JLIS 합친 LIS / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/JLIS
 * 제출링크 : https://algospot.com/judge/submission/detail/655618
 */
public class Main {
    Scanner sc = new Scanner(System.in);
    int[][] cache;
    int n;
    int m;
    int[] A;
    int[] B;

    public void solve() {
        int T = sc.nextInt();
        int r = 0;

        for(int t=0; t<T; ++t) {
            n = sc.nextInt();
            m = sc.nextInt();
            A = new int[n];
            B = new int[m];

            cache = new int[n+1][m+1];

            for(int a=0; a<n; ++a)
                A[a] = sc.nextInt();
            for(int b=0; b<m; ++b)
                B[b] = sc.nextInt();

            r = jlis(-1, -1);
            System.out.println(r-2);
        }
    }

    public int jlis(int indexA, int indexB) {
        if(cache[indexA+1][indexB+1] != 0)
            return cache[indexA+1][indexB+1];

        int ret = 2;
        cache[indexA+1][indexB+1] = 2;

        long a = (indexA == -1)? Long.MIN_VALUE: A[indexA];
        long b = (indexB == -1)? Long.MIN_VALUE: B[indexB];
        long maxElement = Math.max(a, b);

        for(int nextA=indexA+1; nextA<n; ++nextA)
            if(maxElement<A[nextA]) {
                ret = Math.max(ret, jlis(nextA, indexB) +1);
                cache[indexA+1][indexB+1] = ret;
            }

        for(int nextB=indexB+1; nextB<m; ++nextB)
            if(maxElement<B[nextB]) {
                ret = Math.max(ret, jlis(indexA, nextB) +1);
                cache[indexA+1][indexB+1] = ret;
            }

        return ret;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}