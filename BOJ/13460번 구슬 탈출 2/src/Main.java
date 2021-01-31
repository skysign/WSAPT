import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[][] map;
    int N, M;

    int Ri, Rj, Bi, Bj;
    boolean[][][][] visited;

    static int LT = 0;
    static int RT = 1;
    static int UP = 2;
    static int DN = 3;
    int[] LRUD = { LT, RT, UP, DN};
    int[] dIs = { 0, 0,-1, 1};
    int[] dJs = {-1, 1, 0, 0};

    public void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i=0; i<N; ++i) {
            String strLine = br.readLine();

            for (int j=0; j<M; ++j) {
                map[i][j] = strLine.charAt(j);

                if ('R' == map[i][j]) {
                    Ri = i;
                    Rj = j;

                    map[i][j] = '.';
                }

                if ('B' == map[i][j]) {
                    Bi = i;
                    Bj = j;

                    map[i][j] = '.';
                }
            }
        }

        int r = tilt();

        if (r == Integer.MAX_VALUE)
            r = -1;

        bw.write(String.valueOf(r));
        bw.close();
    }

    int tilt() {
        int r = Integer.MAX_VALUE;
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{Ri, Rj, Bi, Bj});
        visited[Ri][Rj][Bi][Bj] = true;

        for (int depth=1; depth<=10; ++depth) {
            for (int size=que.size(); size>0; --size) {
                int[] RijBij = que.pop();

                int ri = RijBij[0];
                int rj = RijBij[1];
                int bi = RijBij[2];
                int bj = RijBij[3];

                for(int dir: LRUD) {
                    int di = dIs[dir];
                    int dj = dJs[dir];

                    boolean bRedFirst = whichBallIsRolledFirstly(dir, ri, rj, bi, bj);

                    if (bRedFirst) {
                        putBall('B', bi, bj);

                        int[] Rij = rollBall(ri, rj, di, dj);

                        if (isBallFell(Rij)) {
                            int[] Bij = rollBall(bi, bj, di, dj);
                            if (isBallFell(Bij)) {
                                clearBall(bi, bj);
                                continue;   // RED, BLUE 동시 구멍에 빠짐
                            }

                            return depth;
                        }
                        else {
                            putBall('R', Rij);
                            clearBall(bi, bj);

                            int[] Bij = rollBall(bi, bj, di, dj);

                            if (isBallFell(Bij)) {
                                clearBall(Rij);
                                continue;   // BLUE 구멍에 빠짐
                            }

                            clearBall(Rij);

                            if (visited[Rij[0]][Rij[1]][Bij[0]][Bij[1]])
                                continue;

                            que.add(new int[]{Rij[0], Rij[1], Bij[0], Bij[1]});
                            visited[Rij[0]][Rij[1]][Bij[0]][Bij[1]] = true;
                        }
                    }
                    else {
                        putBall('R', ri, rj);

                        int[] Bij = rollBall(bi, bj, di, dj);

                        if (isBallFell(Bij)) {
                            clearBall(ri, rj);
                            continue;
                        }
                        else {
                            putBall('B', Bij);
                            clearBall(ri, rj);

                            int[] Rij = rollBall(ri, rj, di, dj);

                            if (isBallFell(Rij)) {
                                return depth;
                            }

                            clearBall(Bij);

                            if (visited[Rij[0]][Rij[1]][Bij[0]][Bij[1]])
                                continue;

                            que.add(new int[]{Rij[0], Rij[1], Bij[0], Bij[1]});
                            visited[Rij[0]][Rij[1]][Bij[0]][Bij[1]] = true;
                        }
                    }
                }
            }
        }

        return r;
    }

    void clearBall(int[] ij) {
        putBall('.', ij[0], ij[1]);
    }

    void clearBall(int i, int j) {
        putBall('.', i, j);
    }

    void putBall(char c, int i, int j) {
        map[i][j] = c;
    }

    void putBall(char c, int[] ij) {
        int i = ij[0];
        int j = ij[1];
        putBall(c, i, j);
    }

    // return true(RED) false(BLUE)
    boolean whichBallIsRolledFirstly(int dir, int ri, int rj, int bi, int bj) {
        if (dir == LT || dir == RT) {
            if (ri == bi) {
                if (dir == LT) {
                    return rj == Math.min(rj, bj);
                }
                // right direction, 이면 j값이 큰거 부터 굴리기
                return rj == Math.max(rj, bj);
            }
        }
        else {  // dir == DN or UP
            if (rj == bj) {
                if (dir == UP) {
                    return ri == Math.min(ri, bi);
                }
                // right direction, 이면 j값이 큰거 부터 굴리기
                return ri == Math.max(ri, bi);
            }
        }

        return true;
    }

    int[] rollBall(int i, int j, int di, int dj) {
        while (map[i+di][j+dj] == '.' || map[i+di][j+dj] == 'O') {
            i += di;
            j += dj;

            if (map[i][j] == 'O')
                break;
        }

        return new int[]{i, j};
    }

    boolean isBallFell(int[] ij) {
        int i = ij[0];
        int j = ij[1];
        return (map[i][j] == 'O');
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }
}