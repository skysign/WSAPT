import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * BOJ 2644번 촌수계산 (DFS 풀이)
 *
 * 유튜브 문제 풀이
 * https://youtu.be/nLdVqod-Z80
 *
 * 문제링크 : https://www.acmicpc.net/problem/2644
 *
 * 자바소스 : https://bit.ly/2Fq2MgS
 */
public class Main {
    int[][] map;
    int[] visited;
    int N;

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
        r = dfs(P1, P2, 0);

        System.out.println(r);
    }

    public int dfs(int src, int dst, int nChon) {
        if (src == dst)
            return nChon;

        visited[src] = 1;

        int r = -1;

        for(int i=1; i<=N; ++i) {
            if ((map[src][i] == 1) && (visited[i] == 0)) {
                int subR = dfs(i, dst, nChon +1);
                if (subR != -1)
                    r = (r >= 0)? Math.min(r, subR): subR;
            }
        }

        return r;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}