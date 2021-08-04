import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N;
    String[] strs;
    Integer[][] dt;
    ArrayList<Integer[]> al;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        dt = new Integer[N][2];
        al = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            strs = br.readLine().split(" ");
            dt[i][0] = Integer.parseInt(strs[0]);
            dt[i][1] = Integer.parseInt(strs[1]);
            al.add(dt[i]);
        }

        al.sort(new Comparator<>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                int r = o1[1].compareTo(o2[1]);

                if (0 != r) {
                    return r;
                }

                return o1[0].compareTo(o2[0]);
            }
        });

        int r = 0;
        int maxEndTime = 0;

        for (int i = 0; i < N; ++i) {
            Integer[] meeting = al.get(i);
            int beginTime = meeting[0];
            int endTime = meeting[1];

            if (maxEndTime > beginTime)
                continue;

            r++;
            maxEndTime = endTime;
        }


        bw.write(String.valueOf(r));
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}