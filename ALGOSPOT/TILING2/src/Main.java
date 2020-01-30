import java.util.Scanner;

/**
 * TILING2 타일링 / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/TILING2
 * 제출링크 : https://algospot.com/judge/submission/detail/653683
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();

	    for(int t=0; t<T; ++t) {
	        int N = sc.nextInt();
	        _2xn(N);
        }
    }

    public static void _2xn(int N) {
        int[] d = new int[N+1];

        d[1] = 1;

        if(N>1) {
            d[2] = 2;
        }

        for(int i=3; i<=N; ++i) {
            d[i] = (d[i-1] + d[i-2]) % 1000000007;
        }

        System.out.println(d[N]);
    }
}
