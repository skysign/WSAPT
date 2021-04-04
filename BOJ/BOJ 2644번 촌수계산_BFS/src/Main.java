import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
/**
 * BOJ 2644번 촌수계산_BFS
 *
 * 유튜브 문제 풀이
 *
 *
 * 문제링크 :
 *
 * 자바소스 :
 */
public class Main {
    int N;
    int[][] map;
    int[] visited;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String[] ss = br.readLine().split(" ");
        int P1 = Integer.parseInt(ss[0]);
        int P2 = Integer.parseInt(ss[1]);
        int M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        visited = new int[N+1];

        for(int i=0; i<M; ++i) {
            ss = br.readLine().split(" ");
            int nParent = Integer.parseInt(ss[0]);
            int nChild = Integer.parseInt(ss[1]);

            map[nParent][nChild] = 1;
            map[nChild][nParent] = 1;
        }

        int r = -1;
        if (P1 == P2)
            r = 0;
        else {
            Deque<Integer> queue = new ArrayDeque();
            queue.add(P1);
            visited[P1] = 1;
            r = bfs(queue, P2, 0);
        }

        System.out.println(r);
    }

    public int bfs(Deque<Integer> queue, int dst, int nChon) {
        int r = -1;
        Deque<Integer> queueNext = new ArrayDeque();

        while(queue.size() > 0) {
            int from = queue.pop();

            if (from == dst) {
                r = (r >= 0)? Math.min(r, nChon): nChon;
            }
            else {
                for(int i=1; i<=N; ++i) {
                    if((map[from][i] == 1) && (visited[i] == 0)) {
                        if(!queueNext.contains(i)) {
                            queueNext.add(i);
                        }
                    }
                }
            }

            visited[from] = 1;
        }

        if (queueNext.size() > 0) {
            int subR = bfs(queueNext, dst, nChon +1);
            if (subR != -1)
                r = (r >= 0)? Math.min(r, subR): subR;
        }

        return r;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}