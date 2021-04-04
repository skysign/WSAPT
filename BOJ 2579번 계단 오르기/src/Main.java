import java.util.Scanner;

/**
 * 2579번 계단 오르기 / BAEKJOON ONLINE JUDGE / acmicpc.net
 * 문제링크 : https://www.acmicpc.net/problem/2579
 * 제출링크 : https://www.acmicpc.net/source/17179222
 */

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] st = new int[N+1];
        int[] d = new int[N+1];

        for(int i=1; i<=N; ++i) {
            st[i] = sc.nextInt();
        }


        /**
         * i-1 → i
         * i-2 → i
         * 3칸을 연속으로 밟으면 안된다, 라는 룰을 지킬 수가 없어요
         * 따라서, 점화식은 아래와 같아야 합니다.
         * i-3 → i-1 → i
         * i-2 → i
         * 여기서 중요한 점음
         * i-3 은 0부터 i-3 까지 거처온 최대값, i-1 은 계단의 값
         * i-2 은 0부터 i-2 까지 거처온 최대값
         */
        d[1] = st[1];
        d[2] = Math.max(st[2-1], d[0]) + st[2];
        d[3] = Math.max(st[3-1] + d[3-3], d[3-2]) + st[3];
        for(int i=4; i<=N; ++i) {
            d[i] = Math.max(st[i-1] + d[i-3], d[i-2]) + st[i];
        }

        System.out.println(d[N]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}