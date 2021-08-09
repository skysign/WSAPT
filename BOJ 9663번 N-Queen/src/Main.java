import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int r = 0;
    boolean[] yline;
    boolean[] xline;
    boolean[] d1line;
    boolean[] d2line;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        yline = new boolean[N];
        xline = new boolean[N];
        d1line = new boolean[N * 2 - 1];
        d2line = new boolean[N * 2 - 1];

//        long start = System.currentTimeMillis();

        N_Queen(0);

//        long finish = System.currentTimeMillis();
//        long timeElapsed = finish - start;

        bw.write(String.valueOf(r));
//        bw.newLine();
//        bw.write(String.valueOf((double) timeElapsed / 1000));
        bw.close();
    }

    public void N_Queen(int y) {
        if (y == N) {
            ++r;
            return;
        }

        for (int x = 0; x < N; ++x) {
            if (!yline[y] && !xline[x] && !d1line[y + x] && !d2line[x - y + (N - 1)]) {
                yline[y] = true;
                xline[x] = true;
                d1line[x + y] = true;
                d2line[x - y + (N - 1)] = true;

                N_Queen(y + 1);

                yline[y] = false;
                xline[x] = false;
                d1line[x + y] = false;
                d2line[x - y + (N - 1)] = false;
            }
        }
    }

//    boolean isAvailable(int y, int x) {
//        return ;
//    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}