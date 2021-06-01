import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, M, FR, TO;
    long[] dst;
    String[] strs;
    PriorityQueue[] alEdge;
    ArrayList[] alPath;
    PriorityQueue<Long[]> pq;
    boolean[] visited;

    public void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        alEdge = new PriorityQueue[N+1];
        alPath = new ArrayList[N+1];
        for (int i=1; i<N+1; ++i) {
            alPath[i] = new ArrayList<Long>();
            alPath[i].add(Long.valueOf(i));
        }

        dst = new long[N+1];
        visited = new boolean[N+1];
        Arrays.fill(dst, INF);

        pq = new PriorityQueue<Long[]>(new Comparator<Long[]>() {
            @Override
            public int compare(Long[] o1, Long[] o2) {
                return  o1[1].compareTo(o2[1]);
            }
        });

        for (int i=0; i<M; ++i) {
            strs = br.readLine().split(" ");
            Long fr = Long.parseLong(strs[0]);
            Long to = Long.parseLong(strs[1]);
            Long v  = Long.parseLong(strs[2]);

            if (alEdge[fr.intValue()] == null)
                alEdge[fr.intValue()] = new PriorityQueue<Long[]>(new Comparator<Long[]>() {
                    @Override
                    public int compare(Long[] o1, Long[] o2) {
                        return  o1[1].compareTo(o2[1]);
                    }
                });

            alEdge[fr.intValue()].add(new Long[]{to, v});
        }

        strs = br.readLine().split(" ");
        FR   = Integer.parseInt(strs[0]);
        TO   = Integer.parseInt(strs[1]);

        dst[FR] = 0;

        pq.add(new Long[]{Long.valueOf(FR), Long.valueOf(0)});

        while (pq.size() > 0) {
            Long[] frv = pq.poll();
            Long fr = frv[0];

            if (alEdge[fr.intValue()] == null)
                continue;

            for (Object o: alEdge[fr.intValue()]) {
                Long[] to2 = (Long[])o;
                Long to = to2[0];
                Long v  = to2[1];

                if (dst[to.intValue()] > dst[fr.intValue()] +v) {
                    dst[to.intValue()] = dst[fr.intValue()] +v;

                    alPath[to.intValue()].clear();
                    alPath[to.intValue()].addAll(alPath[fr.intValue()]);
                    alPath[to.intValue()].add(to);
                }

                if (visited[to.intValue()] == false) {
                    to2[1] = dst[to.intValue()];
                    pq.add(to2);
                    visited[to.intValue()] = true;
                }
            }
        }

        Long r = dst[TO];

        bw.write(String.valueOf(r));
        bw.newLine();
        bw.write(String.valueOf(alPath[TO].size()));
        bw.newLine();

        for (Object o: alPath[TO]) {
            Long v = (Long)o;
            bw.write(String.valueOf(v) + " ");
        }

        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    static long INF = Long.MAX_VALUE;

    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }
}