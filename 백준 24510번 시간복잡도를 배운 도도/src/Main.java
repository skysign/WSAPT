import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String str;

    public void solve() throws IOException {
//        strs = br.readLine().split(" ");
        N = Integer.parseInt(br.readLine());

        int ans = 0;

        for (int n = 0; n < N; ++n) {
            str = br.readLine();
            int length = str.length();

            // "for" -> ""
            //  length 3 -> length 0
            // (3-0) / 3 -> for의 개수
            str = str.replaceAll("for", "");

            int count = (length - str.length()) / 3;

            length = str.length();

            str = str.replaceAll("while", "");

            count += (length - str.length()) / 5;

            ans = Math.max(ans, count);
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}