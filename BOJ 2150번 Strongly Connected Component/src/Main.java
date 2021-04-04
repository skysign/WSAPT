import java.io.*;
import java.util.*;
/**
 * BOJ 2150번 Strongly Connected Component
 *
 * 유튜브 문제 풀이 : https://youtu.be/d46QTzFGLTQ
 *
 * 문제링크 : https://www.acmicpc.net/problem/2150
 *
 * 자바소스 : https://bit.ly/38vdSwQ
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N, K;
    boolean [][] map;
    boolean [][] reverseMap;
    boolean[] visited;
    ArrayList<ArrayList<Integer>> allPouches;
    Integer[][] sortedPouches;

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean [N+1][N+1];
        reverseMap = new boolean [N+1][N+1];
        visited = new boolean [N+1];
        allPouches = new ArrayList();

        for (int k=1; k<=K; ++k) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            map[v1][v2] = true;
            reverseMap[v2][v1] = true;
        }

        Stack<Integer> stk = new Stack();

        for (int n=1; n<=N; ++n) {
            if (visited[n] == false) {
                DFS_SCC(n, stk);
            }
        }

        Arrays.fill(visited, false);

        while (stk.size() > 0) {
            int toV = stk.pop();

            if (visited[toV] == false) {
                ArrayList<Integer> pouch = new ArrayList();
                allPouches.add(pouch);
                DFS_SCC_reverse(toV, pouch);
            }
        }

        sortedPouches = new Integer[allPouches.size()][];
        for (int idx=0; idx<allPouches.size(); ++idx) {
            ArrayList<Integer> pouch = allPouches.get(idx);
            sortedPouches[idx] = pouch.toArray(new Integer[0]);
            Arrays.sort(sortedPouches[idx]);
        }

        Arrays.sort(sortedPouches, new MyComparator());

        bw.write(String.valueOf(sortedPouches.length) +'\n');

        for (Integer[] arr: sortedPouches) {
            for (int i=0; i<arr.length; ++i) {
                bw.write(String.valueOf(arr[i]) +' ');
            }

            bw.write(String.valueOf(-1) +'\n');
        }

        bw.flush();
        bw.close();
    }

    class MyComparator implements Comparator<Integer[]> {
        @Override
        public int compare(Integer[] t1, Integer[] t2) {
            if (t1[0] > t2[0])
                return 1;

            return -1;
        }
    }

    void DFS_SCC_reverse(int v, ArrayList<Integer> pouch) {
        visited[v] = true;
        pouch.add(v);

        for (int toV=1; toV<=N; ++toV) {
            if (!visited[toV] && reverseMap[v][toV]) {
                DFS_SCC_reverse(toV, pouch);
            }
        }
    }

    void DFS_SCC(int v, Stack<Integer> stk) throws IOException {
        visited[v] = true;

        for (int toV=1; toV<=N; ++toV) {
            if (!visited[toV] && map[v][toV]) {
                DFS_SCC(toV, stk);
            }
        }

        stk.push(v);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}