/**
 * BOJ 11720번 숫자의 합
 * 문제링크 : https://www.acmicpc.net/problem/11720
 * 문제풀이 : https://skysign.tistory.com/252
 */
import java.util.Scanner;

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        sc.next();
        String str = sc.next();
        int r = 0;

        for(int i=0; i<str.length(); ++i) {
            String strDigit = String.valueOf(str.charAt(i));
            r += Integer.parseInt(strDigit);
        }

        System.out.println(r);
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}
