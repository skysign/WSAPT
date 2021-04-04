import java.util.Scanner;

/**
 * BOJ 13398번 연속합 2
 *
 * 문제링크 : https://www.acmicpc.net/problem/13398
 *
 * 유튜브 문제 풀이
 * https://youtu.be/OZS0DGY9L_M
 * https://youtu.be/cYvGKRpDkzY
 *
 * 자바소스 : https://bit.ly/3kN4uJ1
 */

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Integer[] dt = new Integer[N];

        for(int i=0; i<N; ++i)
            dt[i] = sc.nextInt();

        int[] dpL2R = new int[N];
        int r = yeonSokHap(dt, dpL2R);

        int[] dpR2L = new int[N];
        yeonSokHapR2L(dt, dpR2L);

        for(int idxX=0; idxX<N; ++idxX) {
            if(idxX>0)
                r = Math.max(r, dpL2R[idxX-1]);

            if(idxX<N-1)
                r = Math.max(r, dpR2L[idxX+1]);

            if(idxX>0 && idxX<N-1)
                r = Math.max(r, dpL2R[idxX-1] + dpR2L[idxX+1]);
        }

        System.out.println(r);
    }

    public void yeonSokHapR2L(Integer[] dt, int[] dpR2L) {
        int N = dt.length;
        dpR2L[N-1] = dt[N-1];

        for(int i=N-2; i>=0; --i) {
            dpR2L[i] = Math.max(dpR2L[i+1] + dt[i], dt[i]);
        }
    }

    public int yeonSokHap(Integer[] dt, int[] dpL2R) {
        dpL2R[0] = dt[0];
        int r = dpL2R[0];

        for(int i=1; i<dt.length; ++i) {
            dpL2R[i] = Math.max(dpL2R[i-1] + dt[i], dt[i]);
            r = Math.max(r, dpL2R[i]);
        }

        return r;
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
