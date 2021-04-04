import java.util.Scanner;

/**
 * HOTSUMMER 에어컨을 끈다고 전력난이 해결될까? / ALGOSPOT
 * 문제링크 : https://algospot.com/judge/problem/read/HOTSUMMER
 * 제출링크 : https://algospot.com/judge/submission/detail/653877
 */
public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0; t<T; ++t) {
            int sum = sc.nextInt();
            int N=9;
            int asum = 0;
            for(int i=0; i<N; ++i) {
                asum += sc.nextInt();
            }

            System.out.println((asum <= sum)? "YES": "NO");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}