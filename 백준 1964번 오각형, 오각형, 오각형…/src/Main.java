import java.io.*;

/**
 * 백준 1964번 오각형, 오각형, 오각형…
 *
 * 유튜브 문제 풀이: https://youtu.be/x9MhiK_nTVE
 *
 * 문제링크: https://www.acmicpc.net/problem/1964
 *
 * 자바소스: http://bit.ly/3DTh0km
 */

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());

        int ans = 0;
        int sum = 5;
        int alpha = 7;

        for (int n = 2; n <= N; ++n) {
            sum += alpha;
            alpha += 3;
            sum = sum % 45678;
        }

        ans = sum;

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}