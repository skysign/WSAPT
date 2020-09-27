import java.util.*;
/**
 * BOJ 1260번 DFS와 BFS
 *
 * 문제링크 : https://www.acmicpc.net/problem/1260
 *
 * 유튜브 문제 풀이
 * https://youtu.be/rand1XTwEnE
 *
 * 자바소스 :
 */
public class Main {
    public int N;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt() + 1;
        int M = sc.nextInt();
        int start = sc.nextInt();
        int[][] map = new int[N][N];
        int[][] visited = new int[N][N];

        /**
         * map[fr][to] == 1
         * edge fr -> to exist
         */

        for(int i=0; i<M; ++i) {
            int fr = sc.nextInt();
            int to = sc.nextInt();
            map[fr][to] = 1;
            map[to][fr] = 1;
        }

        DFS(map, visited, start);

        System.out.printf("\n");

        visited = new int[N][N];
        Deque<Integer> que = new ArrayDeque();
        que.add(start);
        BFS(map, visited, que);
    }

    public void DFS(int[][] map, int[][] visited, int fr) {
        System.out.printf("%d ", fr);

        // vertex `fr` is visited, so we don't need any incoming edge to vertex `fr`
        for(int i = 0; i < N; ++i)
            visited[i][fr] = 1;

        for(int to = 0; to < N; ++to) {
            if (map[fr][to] == 1 && visited[fr][to] == 0) {
                DFS(map, visited, to);
            }
        }
    }

    public void BFS(int[][] map, int[][] visited, Deque<Integer> que) {
        while(que.size() > 0) {
            Integer fr = que.pop();
            System.out.printf("%d ", fr);

            // vertex `fr` is visited, so we don't need any incoming edge to vertex `fr`
            for(int i = 0; i < N; ++i)
                visited[i][fr] = 1;

            for(int to = 0; to < N; ++to) {
                if (map[fr][to] == 1 && visited[fr][to] == 0) {
                    if (!que.contains(to))
                        que.add(to);
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}