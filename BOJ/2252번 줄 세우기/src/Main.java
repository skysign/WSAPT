import java.io.*;
import java.util.*;

public class Main {
    BufferedReader br;
    BufferedWriter bw;

    int N, M;

    ArrayList<ArrayList<Integer>> edges;
    boolean[] visited;

    public void solve(InputStream in, PrintStream out) throws IOException {
        br = new BufferedReader(new InputStreamReader(in));
        bw = new BufferedWriter(new OutputStreamWriter(out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();

        for (int i = 0; i <= N; ++i) {
            edges.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int toV = Integer.parseInt(st.nextToken());
            int frV = Integer.parseInt(st.nextToken());

            edges.get(frV).add(toV);
        }

        visited = new boolean[N +1];

        for (int i = 1; i <= N; ++i) {
            if (!visited[i])
                DFS(i);
        }

        bw.close();
    }

    void DFS(int v) throws IOException {
        visited[v] = true;
        ArrayList<Integer> al = edges.get(v);

        for (Integer toV: al) {
            if (!visited[toV])
                DFS(toV);
        }

        bw.write(String.valueOf(v) + ' ');
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve(System.in, System.out);
    }
}