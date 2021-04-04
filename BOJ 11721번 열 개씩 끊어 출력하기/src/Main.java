import java.util.Scanner;

/**
 * 11721번 열 개씩 끊어 출력하기 / BOJ
 * 문제링크 : https://www.acmicpc.net/problem/11721
 * 제출링크 : https://www.acmicpc.net/source/17504215
 */
public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
	    String S = sc.nextLine();
        p(S, 0, S.length());
    }

    public static void p(String S, int idx, int l) {
        int end = Math.min(l, idx+10);
        System.out.println(S.substring(idx, end));
        if(l-end > 0)
            p(S, end, l);
    }
}
