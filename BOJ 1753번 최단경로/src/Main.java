import java.io.*;
import java.util.*;
/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 1753번 최단경로
 *
 * 유튜브 문제 풀이 : https://youtu.be/nvxBhRf_OuE
 *
 * 문제링크 : https://www.acmicpc.net/problem/1753
 *
 * 자바소스 : https://bit.ly/39F4Zkp
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int V, E, k;
    int[] dsts;
    List[] edges;
    PriorityQueue<Integer[]> pque;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        edges = new ArrayList[V+1];
        dsts = new int[V+1];
        Arrays.fill(dsts, Integer.MAX_VALUE);

        for (int i=1; i<V+1; ++i) {
            edges[i] = new ArrayList<Integer[]>();
        }

        for (int i=0; i<E; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[u].add(new Integer[]{v, w});
        }

        pque = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });

        pque.add(new Integer[]{k, 0});
        dsts[k] = 0;

        while (pque.size() > 0) {
            Integer[] frw = pque.poll();
            int fr = frw[0].intValue();
            int w = frw[1];

            // Priority Queue이기 때문에, Que에서 나왔을 때도,
            // fr이 que에서 나오기 전에,
            // w가 더 작은 fr들이 먼저 que에서 먼저 나와서
            // dsts[fr] 값이 낮어저 있을 수 있음
            if (dsts[fr] < w)
                continue;

            // fr에서 out-degree edge가 없으면, hm은 null
            ArrayList<Integer[]> al = (ArrayList<Integer[]>) edges[fr];
            if (al.size() == 0)
                continue;

            for (Integer[] toW : al) {
                int to  = toW[0];
                int tow = toW[1];

                if (dsts[to] > dsts[fr] + tow) {
                    dsts[to] = dsts[fr] + tow;
                    pque.add(new Integer[]{to, dsts[to]});
                }
            }
        }

        for (int i=1; i<V+1; ++i) {
            if (dsts[i] == Integer.MAX_VALUE)
                bw.write("INF");
            else
                bw.write(String.valueOf(dsts[i]));

            bw.newLine();
        }

        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}