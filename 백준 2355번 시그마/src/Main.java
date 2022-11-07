import java.io.*;

/**
 * 백준 2355번 시그마
 *
 * 유튜브 문제 풀이: https://youtu.be/NdNhKmnVs0c
 *
 * 문제링크: https://www.acmicpc.net/problem/2355
 *
 * 자바소스: http://bit.ly/3DH3Ve7
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 두 정수 A와 B가 주어졌을 때,
    // A < B?  A > B?

    // 두 정수 사이에 있는 수의 합을 구하는 프로그램을 작성하시오. 사이에 있는 수들은 A와 B도 포함한다.
    // loop ->
    // 공식을 사용해서 계산

    // 첫째 줄에 두 정수 A, B가 주어진다. (-2,147,483,648 ≤ A, B ≤ 2,147,483,647)
    // 2,147,483,646
    // 2,147,483,647
    // + integer overflow -> long

    long A, B;
    String[] strs;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        A = Long.parseLong(strs[0]);
        B = Long.parseLong(strs[1]);

        long a = Math.min(A, B);
        long b = Math.max(A, B);

        // (처음의 수 + 끝의 수) * (수의 개수) / 2
        long ans = (a + b) * (b - a +1) / 2;

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}