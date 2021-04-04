import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    int[] dt;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        dt = new int[N];

        for (int i=0; i<N; ++i) {
            dt[i] = Integer.parseInt(br.readLine());
        }

        quick_sort(dt, 0, N-1);

        int r = 0;

        for (int d: dt) {
            bw.write(String.valueOf(d));
            bw.newLine();
        }

        bw.close();
    }

    void quick_sort(int[] dt, int idxLow, int idxHigh) {
        if (idxLow < idxHigh) {
            int idxPivot = quick_partition(dt, idxLow, idxHigh);

            quick_sort(dt, idxLow, idxPivot-1);
            quick_sort(dt, idxPivot+1, idxHigh);
        }
    }

    int quick_partition(int[] dt, int idxLow, int idxHigh) {
        int idxPivot = idxHigh;
        int pivot = dt[idxPivot];

        idxHigh--;

        int idxL = idxLow;

        for (int idxR=idxLow; idxR<=idxHigh; ++idxR) {
            if (dt[idxR] < pivot) {
                swap(dt, idxL, idxR);
                idxL++;
            }
        }

        swap(dt, idxL, idxPivot);

        return idxL;
    }

    void swap(int[] d, int i, int j) {
        int t = d[i];

        d[i] = d[j];
        d[j] = t;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}