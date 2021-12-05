import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int cntJang;
    long[] jang;
    long ju, bu;
    String[] strs;

    public void solve() throws IOException {
        cntJang = Integer.parseInt(br.readLine());
        jang = new long[cntJang];
        strs = br.readLine().split(" ");

        for (int i = 0; i < cntJang; ++i) {
            jang[i] = Integer.parseInt(strs[i]);
        }

        strs = br.readLine().split(" ");
        ju = Integer.parseInt(strs[0]);
        bu = Integer.parseInt(strs[1]);

        long r = cntJang;

        for (int i = 0; i < cntJang; ++i) {
            jang[i] -= ju;

            if (jang[i] > 0) {
                r += (jang[i] / bu);

                if (jang[i] % bu > 0) {
                    r++;
                }
            }
        }

        bw.write(String.valueOf(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}