import java.io.*;
import java.util.*;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N, K;
    int[] dt;
    int[] dag;
    ArrayList[] dagVisited;
    int[][] rs;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dt = new int[N+1];
        dag = new int[N+1];
        dagVisited = new ArrayList[N+1];
        rs = new int[N+1][K+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; ++i) {
            dt[i] = Integer.parseInt(st.nextToken());
        }

        int r = solve2();

        bw.write(Integer.toString(r));
        bw.flush();
        bw.close();
    }


    int solve2() {
        for (int i = 1; i <=N; ++i) {
            dagVisited[i] = new ArrayList<Integer>();
            dag[i] = getLengthOfDAG(i, dagVisited[i]);
        }

        fill2D(rs, -1);

        ArrayList<Integer> visited = new ArrayList<Integer>();
        return knapsack(N, K, visited);
    }

    int getLengthOfDAG(int begin, ArrayList<Integer> visited) {
        Deque<Integer> que = new ArrayDeque<>();
        que.add(begin);
        visited.add(begin);

        int r = 1;

        while (que.size() > 0) {
            int fr = que.pop();
            int to = dt[fr];

            if (visited.contains(to)) {
                break;
            }

            r++;

            if (dt[to] != 0) {
                que.add(to);
                visited.add(to);
            }
        }

        return r;
    }

    int knapsack(int n, int k, ArrayList<Integer> visited) {
        if (n <= 0)
            return 0;

        if (k <= 0)
            return 0;

        if (rs[n][k] != -1)
            return rs[n][k];

        int r1 = 0;

        if (dag[n] <= k && IsIntersectionEmpty(visited, dagVisited[n])) {
            ArrayList<Integer> neweVisited = new ArrayList();
            neweVisited.addAll(dagVisited[n]);
            neweVisited.addAll(visited);
            r1 = knapsack(n-1, k -dag[n], neweVisited) + dag[n];
        }

        int r2 = knapsack(n-1, k, visited);

        return rs[n][k] = Math.max(r1, r2);
    }

    boolean IsIntersectionEmpty(ArrayList<Integer> al1, ArrayList<Integer> al2) {
        for (int item: al1) {
            if (al2.contains(item))
                return false;
        }

        return true;
    }

    // Initialize 2D arrays with value v
    public void fill2D(int[][] _2D, int v) {
        for(int[] _1D: _2D) {
            Arrays.fill(_1D, v);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}