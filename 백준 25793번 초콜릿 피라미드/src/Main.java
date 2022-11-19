import java.io.*;
/**
 * 백준 25793번 초콜릿 피라미드
 *
 * 유튜브 문제 풀이: https://youtu.be/q0KBwLYI024
 *
 * 문제링크: https://www.acmicpc.net/problem/25793
 *
 * 자바소스: http://bit.ly/3V87dxZ
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T, R, C;
    String[] strs;

    // 일반적인 컴퓨터에서, 1테라 넘는 메모리는 힙 부족으로 할당 불가능
//    long[][][] dp = new long[1000000 + 1][1000000 + 1][2];

    public void solve() throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            strs = br.readLine().split(" ");
            int tmpR = Integer.parseInt(strs[0]);
            int tmpC = Integer.parseInt(strs[1]);

            R = Math.min(tmpR, tmpC);
            C = Math.max(tmpR, tmpC);

            long[] ans = pyramid(R, C);


            bw.write(String.valueOf(ans[0]));
            bw.write(String.valueOf(" "));
            bw.write(String.valueOf(ans[1]));
            bw.newLine();

        }

        bw.close();
    }

// 루핑으로 문제를 풀수 있지만, 시간 초과 발생함
//    long[] pyramid(int r, int c) {
//        long w = 0;
//        long d = 0;
//
//        for (long R = Math.min(r, c), C = Math.max(r, c);
//            R >= 1;
//            --R, --C) {
//            w += R * C + (R - 1) * (C - 1);
//            d += R * (C - 1) + (R - 1) * C;
//        }
//
//        return new long[]{w, d};
//    }

    long[] pyramid(int r, int c) throws IOException {
        long white = 0, dark = 0;
        long n = r;
        long d = c - r;

        white = 2 * sum2_1_N(n) - (2 * sum_1_N(n)) + n;
        white += d * (r + 2 * sum_1_N(r - 1));

        dark = 2 * (sum2_1_N(n) - sum_1_N(n));
        dark += d * (r + 2 * sum_1_N(r - 1));

        return new long[]{white, dark};
    }

    //    1 ~ N 까지의 함
    long sum_1_N(long n) {
        return (1 + n) * n / 2;
    }

    //    1 ~ N 각 수의 제곱의 함
    long sum2_1_N(long n) {
        return n * (n + 1) * (2 * n + 1) / 6;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}