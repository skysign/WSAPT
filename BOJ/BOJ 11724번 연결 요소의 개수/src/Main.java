import java.util.*;
/**
 * BOJ 11724번 연결 요소의 개수
 *
 * 유튜브 문제 풀이
 * https://youtu.be/yvWmjljjYJY
 *
 * 문제링크 : https://www.acmicpc.net/problem/11724
 *
 * 자바소스 : https://bit.ly/3mGMEaO
 */
public class Main {
    public int nVertex;
    public int nEdge;
    public int[][] map;
    public boolean[] connected;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        nVertex = sc.nextInt();
        nEdge = sc.nextInt();
        sc.nextLine();

        map = new int[nVertex +1][nVertex +1];
        connected = new boolean[nVertex +1];

        for(int i = 0; i < nEdge; ++i) {
            int fr = sc.nextInt();
            int to = sc.nextInt();
            sc.nextLine();

            map[fr][to] = 1;
            map[to][fr] = 1;
        }

        for(int i = 1; i<=nVertex; ++i) {
            for(int j = 1; j<=nVertex; ++j) {
                if (1 == map[i][j]) {
                    Deque<Integer> queue = new ArrayDeque<>();
                    queue.add(i);

                    bfs(queue);
                }
            }
        }


        int r = 0;

        for (boolean b: connected) {
            if (false == b)
                r++;
        }

        r -= 1;
        System.out.println(r);
    }

    public void bfs(Deque<Integer> queue) {
        while(queue.size() > 0) {
            int fr = queue.pop();

            for(int to = 1; to <= nVertex; ++to) {
                if (1 == map[fr][to]) {
                    map[fr][to] = 0;
                    map[to][fr] = 0;

                    if(!queue.contains(to)) {
                        queue.add(to);
                        connected[to] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}