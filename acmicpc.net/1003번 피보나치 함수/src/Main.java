import java.util.Scanner;

/**
 * 1003번 피보나치 함수 / BAEKJOON ONLINE JUDGE / acmicpc.net
 * 문제링크 : https://www.acmicpc.net/problem/1003
 * 제출링크 : https://www.acmicpc.net/source/17178118
 */
public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int N = sc.nextInt();
            answer(N);
        }

        /**
         * #테스트 결과
         * 11 개, 0~10까지 테스트 해보면 아래와 같은 결과를 얻을 수 있습니다.
         * 11
         * 0
         * 1
         * 2
         * 3
         * 4
         * 5
         * 6
         * 7
         * 8
         * 9
         * 10
         * 0(1) 1(0)
         * 0(0) 1(1)
         * 0(1) 1(1)
         * 0(1) 1(2)
         * 0(2) 1(3)
         * 0(3) 1(5)
         * 0(5) 1(8)
         * 0(8) 1(13)
         * 0(13) 1(21)
         * 0(21) 1(34)
         * 0(34) 1(55)
         */
//        for(int t=0; t<T; ++t) {
//            int N = sc.nextInt();
//            fibonacci(N);
//            System.out.printf("0(%d) 1(%d)\n", mN0, mN1);
//            mN0 = 0;
//            mN1 = 0;
//        }
    }

    public void answer(int N) {
        int[] d0 = new int[N+1];
        int[] d1 = new int[N+1];

        int r0;
        int r1;

        d0[0] = 1;
        d1[0] = 0;

        if(N+1 >= 2) {
            d0[1] = 0;
            d1[1] = 1;
        }

        for(int i=2; i<=N; ++i) {
            d0[i] = d0[i-1] + d0[i-2];
            d1[i] = d0[i-1] + d1[i-1];
        }

        System.out.printf("%d %d\n", d0[N], d1[N]);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }

    /**
     * 위으 테스트 결과를 얻기 위한 코드 입니다.
     */
//    public int mN0 = 0;
//    public int mN1 = 0;
//
//    public int fibonacci(int n) {
//        if (n == 0) {
//            mN0++;
//            return 0;
//        } else if (n == 1) {
//            mN1++;
//            return 1;
//        } else {
//            return fibonacci(n-1) + fibonacci(n-2);
//        }
//    }
}
