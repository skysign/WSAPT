import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long A, B;
    String[] strs;

    // 두 정수 A와 B가 주어졌을 때,
    // A, B가 주어진다. (-2,147,483,648 ≤ A, B ≤ 2,147,483,647)
    // 2,147,483,647 -> long
    // 2,147,483,647

    // A 가 큰가? B 가 큰가? -> Math.min /max A < B

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        A = Long.parseLong(strs[0]);
        B = Long.parseLong(strs[1]);

        long a = Math.min(A, B);
        long b = Math.max(A, B);

        // (처음의 수 + 끝의 수) * (수의 갯수) / 2
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