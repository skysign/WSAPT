import java.io.*;

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