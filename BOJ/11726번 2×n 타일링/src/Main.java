import java.util.Scanner;

/**
 * BOJ 11726번 2×n 타일링
 * 문제링크 : https://www.acmicpc.net/problem/11726
 * 제출링크 : https://www.acmicpc.net/source/17183178
 * 문제풀이 : https://skysign.tistory.com/179
 */
public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] d = new int[N+1];

        d[1] = 1;

        if(N>=2)
            d[2] = 2;

        for(int i=3; i<=N; ++i) {
            d[i] = (d[i-1] + d[i-2]) % 10007;
        }

        System.out.println(d[N]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}