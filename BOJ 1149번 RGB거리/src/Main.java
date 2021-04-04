/**
 * 1149번 RGB거리 / BAEKJOON ONLINE JUDGE / acmicpc.net
 * 문제링크 : https://www.acmicpc.net/problem/1149
 * 제출링크 : https://www.acmicpc.net/source/17183603
 */
import java.util.Scanner;

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] c = new int[N][3];

        for(int i=0; i<N; ++i) {
            c[i][0] = sc.nextInt();
            c[i][1] = sc.nextInt();
            c[i][2] = sc.nextInt();
        }

        for(int i=1; i<N; ++i) {
            c[i][0] += Math.min(c[i-1][1], c[i-1][2]);
            c[i][1] += Math.min(c[i-1][0], c[i-1][2]);
            c[i][2] += Math.min(c[i-1][0], c[i-1][1]);
        }

        int r = Math.min(c[N-1][0], c[N-1][1]);
        r = Math.min(c[N-1][2], r);

        System.out.println(r);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}