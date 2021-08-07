import java.io.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] dt;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        dt = new String[N];

        for (int i = 0; i < N; ++i) {
            dt[i] = br.readLine();
        }

        quickSort(dt, 0, N - 1);

        for (String s : dt) {
            bw.write(s + '\n');
        }

        bw.close();
    }

    void quickSort(String[] dt, int begin, int end) {
        if (!(begin < end))
            return;

        int idxPivot = begin;
        String pivot = dt[end];

        for (int idxRight = idxPivot; idxRight < end; ++idxRight) {
            if (myCompare(dt[idxRight], pivot) < 0) {
                swap(dt, idxPivot, idxRight);
                idxPivot++;
            }
        }

        swap(dt, idxPivot, end);
        quickSort(dt, begin, idxPivot - 1);
        quickSort(dt, idxPivot + 1, end);
    }

    public int myCompare(String s1, String s2) {
        if (s1.length() < s2.length())
            return -1;

        if (s1.length() > s2.length())
            return 1;

        int v1 = 0, v2 = 0;

        for (int i = 0; i < s1.length(); ++i) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (isNumber(c1)) {
                v1 += (c1 - '0');
            }

            if (isNumber(c2)) {
                v2 += (c2 - '0');
            }
        }

        if (v1 < v2)
            return -1;

        if (v1 > v2)
            return 1;

        return s1.compareTo(s2);
    }

    public boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    public void swap(String[] d, int i, int j) {
        String tmp = d[i];
        d[i] = d[j];
        d[j] = tmp;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}