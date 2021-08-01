import java.io.*;
import java.util.HashMap;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] as;
    int M;
    int[] bs;
    String[] strs;
    HashMap<Integer, Integer> hs = new HashMap<>();

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        as = new int[N];
        strs = br.readLine().split(" ");
        for (int i = 0; i < N; ++i) {
            as[i] = Integer.parseInt(strs[i]);
        }

        M = Integer.parseInt(br.readLine());
        bs = new int[M];
        strs = br.readLine().split(" ");
        for (int i = 0; i < M; ++i) {
            bs[i] = Integer.parseInt(strs[i]);
        }

        for (int i = 0; i < N; ++i) {
            if (hs.containsKey(as[i])) {
                hs.put(as[i], hs.get(as[i]) + 1);
            } else {
                hs.put(as[i], 1);
            }
        }

        for (int i = 0; i < M; ++i) {
            if (hs.containsKey(bs[i])) {
                bw.write(String.valueOf(hs.get(bs[i])) + ' ');
            } else {
                bw.write("0 ");
            }
        }

        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}