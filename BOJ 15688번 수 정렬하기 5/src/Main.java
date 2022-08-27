import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] dt;
    String[] strs;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        dt = new int[N];

        for (int i = 0; i < N; ++i)
            dt[i] = Integer.parseInt(br.readLine());

        quickSort(dt, 0, N - 1);

        for (int v : dt)
            bw.write(String.valueOf(v) + '\n');

        bw.close();
    }

    /**
     * begin : inclusive
     * end : inclusive
     */
    public void quickSort(int[] dt, int begin, int end) {
        if (!(begin < end))
            return;

        int pivot = dt[end];
        int idxPivot = begin;

        for (int idxR = begin; idxR < end; ++idxR) {
            if (dt[idxR] < pivot) {
                swap(dt, idxPivot, idxR);
                idxPivot++;
            }
        }

        swap(dt, idxPivot, end);

        quickSort(dt, begin, idxPivot -1);
        quickSort(dt, idxPivot+1, end);
    }

    public void swap(int[] dt, int i, int j) {
        int t = dt[i];
        dt[i] = dt[j];
        dt[j] = t;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}