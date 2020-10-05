import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
/**
 * BOJ 3184번 양
 *
 * 유튜브 문제 풀이
 * https://youtu.be/agDzpOA5670
 *
 * 문제링크 : https://www.acmicpc.net/problem/3184
 *
 * 자바소스 : https://bit.ly/34tCUcr
 */
public class Main {
    public int R;
    public int C;
    char[][] map;
    int[][] visited;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine(); /** To remove the new line next to C */

        int cntArea = 0;

        map = new char[R][C];
        visited = new int[R][C];

        for(int i = 0; i < R; ++i) {
            String strLine = sc.nextLine();
            for(int j = 0; j < C; ++j) {
                map[i][j] = strLine.charAt(j);
            }
        }

        /**
         * rlt index 0 : 남은 양의 마리수의 합
         * rlt index 1 : 남은 늑대 마리수의 합
         */
        int[] rlt = new int[2];

        for(int y=0; y<R; ++y) {
            for(int x=0; x<C; ++x) {
                if(IsVisitable(y, x)) {
                    ++cntArea;
                    floodfill(y, x, cntArea, rlt);
                }
            }
        }

        System.out.printf("%d %d", rlt[0], rlt[1]);
    }

    public void floodfill(int y, int x, int nArea, int[] rlt) {
        int nO = 0;
        int nV = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x});

        while(queue.size() > 0) {
            int[] S = queue.pop();
            int sY = S[0];
            int sX = S[1];
            visited[sY][sX] = nArea;

            if(map[sY][sX] == 'o')
                nO++;
            if(map[sY][sX] == 'v')
                nV++;

            for(int idx = 0; idx < d4i.length; ++idx) {
                int dY = sY + d4i[idx];
                int dX = sX + d4j[idx];
                if(IsInMap(dY, dX) && IsVisitable(dY, dX)) {
                    int[] newYX = new int[]{dY, dX};
                    if(IsContain(queue, newYX) == false)
                        queue.add(newYX);
                }
            }
        }

        if(nO > nV) {
            rlt[0] += nO;
        }
        else {
            rlt[1] += nV;
        }
    }

    public boolean IsContain(Deque<int[]> queue, int[] newYX) {
        for(int[] YX: queue) {
            if(YX[0] == newYX[0])
                if(YX[1] == newYX[1])
                    return true;
        }

        return false;
    }

    public boolean IsVisitable(int y, int x) {
        if(map[y][x] != '#' && visited[y][x] == 0)
            return true;

        return false;
    }

    public boolean IsInMap(int y, int x) {
        if((0 <= y && y < R) && (0 <=x && x < C))
            return true;

        return false;
    }

    // 4 ways
    public int[] d4i = new int[]{-1, 1, 0, 0};
    public int[] d4j = new int[]{0, 0, -1, 1};

    public static void main(String[] args) {
	    Main main = new Main();
	    main.solve();
    }
}