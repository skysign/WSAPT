import java.util.Scanner;

// A - Frog 1 / atcoder.jp
// 문제 링크 : https://atcoder.jp/contests/dp/tasks/dp_a

public class Main {
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int N = sc.nextInt();
	    int[] stones = new int[N];

	    for(int i=0; i<N; ++i) {
	        stones[i] = sc.nextInt();
        }

	    int r = minimumTotalCost(stones, N);
	    System.out.println(r);
    }

    public static int minimumTotalCost(int[] s, int N) {
        int[] v = new int[N];
        v[0] = 0;
        v[1] = Math.abs(s[0]-s[1]);

        for(int i=2; i<N; ++i) {
            v[i] = Math.min(Math.abs(s[i-1]-s[i]) +v[i-1], Math.abs(s[i-2]-s[i]) +v[i-2]);
        }

        return v[N-1];
    }
}
