import java.util.Arrays;
import java.util.Scanner;

/**
 * BOJ 1449번 수리공 항승
 *
 * 문제링크 : https://www.acmicpc.net/problem/1449
 *
 * 유튜브 문제 풀이
 * https://youtu.be/aYWRY9ZVKI0
 *
 * 자바소스 :
 * https://bit.ly/336xl33
 */

public class Main {
    public int N, L;
    public int[] dt;

    public void solve() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        dt = new int[N];

        for(int i = 0; i < N; ++i)
            dt[i] = sc.nextInt();

        Arrays.sort(dt);

        int s = dt[0];
        int rtn = 1;

        for(int i = 1; i < N; ++i) {
            int d = dt[i];

            if(d < s + L) {

            }
            else {
                rtn++;
                s = d;
            }
        }

        System.out.println(rtn);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
