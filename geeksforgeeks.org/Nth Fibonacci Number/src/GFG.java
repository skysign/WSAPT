import java.util.Scanner;

// Nth Fibonacci Number  / geeksforgeeks.org
// 문제 링크 : https://practice.geeksforgeeks.org/problems/nth-fibonacci-number/0
// 문제 해설 : https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/

// ps: 배민에서 밥시켜 놓고, 오늘 공부한게 별로 없어서...
// 놀기는 뭐하고... 뭐 그냥 이거나 풀자 해서...
// 풀어 보는 피보나치 수열입니다.

public class GFG {
    // 이렇게 풀어도 답은 나오는대,
    // 시간 초과로 submit 이 안되는 군요.. - -;
    public static int fib(int n) {
        if(1 == n)
            return 1;
        if(2 == n)
            return 1;

        return fib(n-1) + fib(n-2);
    }

    // 리컬시브로 풀어서 속도가 느리니까,
    // a, a_1, a_2 3개의 변수를 사용해서 보다 빠르게 풀어 보겠습니다.
    public static long fib2(int n) {
        int a = 0;
        int a_1 = 1;
        int a_2 = 1;

        if(n == 1)
            return a_1;

        if(n == 2)
            return a_2;

        // 문제 설명에서, modulo 1000000007을 하라고 하는대요,
        // fib2가 실행이 끝나고 리턴할 때, %1000000007을 하는게 아니구요,
        // 중간에 a 값을 만들때, 계속 modulo 1000000007을 해야 합니다.
        for(int i=3; i<=n; ++i) {
            a = (a_1 + a_2)%1000000007;
            a_2 = a_1;
            a_1 = a;
        }

        return a;
    }

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();
	    while(T-->0) {
	        int N = sc.nextInt();
	        long r = fib2(N);
            System.out.println(r);
        }
    }
}
