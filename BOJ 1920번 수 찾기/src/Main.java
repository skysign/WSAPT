import java.io.*;
import java.util.Arrays;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] A;
    int M;
    int[] B;
    String[] strs;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        strs = br.readLine().split(" ");

        for (int i=0; i<N; ++i) {
            A[i] = Integer.parseInt(strs[i]);
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];

        strs = br.readLine().split(" ");

        for (int i=0; i<M; ++i) {
            B[i] = Integer.parseInt(strs[i]);
        }

        Arrays.sort(A);

        for (int i=0; i<M; ++i) {
            int r = binarySearch(0, N-1, B[i]);
            bw.write(String.valueOf(r));
            bw.newLine();
        }

        bw.close();
    }

    public int binarySearch(int b, int e, int v) {
        while (b <= e) {
            int m = (b+e)/2;

            if (v == A[m]) {
                return 1;
            }

            if (v < A[m]) {
                e = m-1;
            }
            else {
                b = m+1;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}