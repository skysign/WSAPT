import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;

    public void solve() throws IOException {
        strs = br.readLine().split(" ");
        N = Integer.parseInt(strs[0]);

        // N 단 196419641964계에서 N-1단계 보다 늘어나는 점의 개수
        // (N + 1) * 3 - 2

        int ans = 0;

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}