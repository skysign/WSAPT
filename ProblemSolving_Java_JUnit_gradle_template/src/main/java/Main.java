import java.io.*;
import java.util.*;

public class Main {
    BufferedReader br;
    BufferedWriter bw;

    int N;

    public void solve(InputStream in, PrintStream out) throws IOException {
        br = new BufferedReader(new InputStreamReader(in));
        bw = new BufferedWriter(new OutputStreamWriter(out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int r = N * 2;

        bw.write(Integer.toString(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve(System.in, System.out);
    }
}