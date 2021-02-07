import java.io.*;
import java.util.*;
/**
 * BOJ 2156번 포도주 시식
 *
 * 유튜브 문제 풀이 :
 *
 * 문제링크 : https://www.acmicpc.net/problem/13460
 *
 * 자바소스 : https://bit.ly/3jqkyzU
 */
public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[][] map;
    int N, M;

    int Ri, Rj, Bi, Bj;
    boolean[][][][] visited;

    enum Direction {
        LT(0), RT(1), UP(2), DN(3);

        public int mV;

        Direction(int v) {
            mV = v;
        }
    }

    int[] dIs = { 0, 0,-1, 1};
    int[] dJs = {-1, 1, 0, 0};

    public void solve() throws IOException, CloneNotSupportedException {
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

        bw.write(String.valueOf(r));
        bw.close();
    }

    int tilt() throws CloneNotSupportedException {
        Deque<Ball[]> que = new ArrayDeque<>();
        Ball[] _balls = new Ball[2];
        _balls[0] = new Ball(Ri, Rj, 'R');
        _balls[1] = new Ball(Bi, Bj, 'B');

        que.add(_balls);
        visited[Ri][Rj][Bi][Bj] = true;

        for (int depth=1; depth<=10; ++depth) {
            for (int size=que.size(); size>0; --size) {
                Ball[] orignalBalls = que.pop();

                for (Direction dir: Direction.values()) {
                    // 공들을 복사
                    // 공을 굴려서, 공의 위치가 변함
                    // 따라서, 공을 굴리기 전에 복사해야함
                    Ball[] balls = new Ball[orignalBalls.length];

                    for (int i=0; i<orignalBalls.length; ++i) {
                        balls[i] = (Ball)orignalBalls[i].clone();
                    }

                    Arrays.sort(balls, comps[dir.mV]);

                    // 공을 굴리면서, map에 mark한다
                    for (Ball ball: balls) {
                        rollBall(ball, dir);
                        if (!ball.mbO) {
                            markBall(ball);
                        }
                    }

                    // O에 빠진 공 체크
                    boolean bRedO = false;
                    boolean bBlueO = false;

                    for (Ball ball: balls) {
                        if (ball.mbO) {
                            if (ball.mColor == 'R') {
                                bRedO = true;
                            }
                            else if (ball.mColor == 'B') {
                                bBlueO = true;
                            }
                        }
                    }

                    // 빨간공만 O에 빠졌는지 확인
                    if ((bRedO == true) && (bBlueO == false) ) {
                        return depth;
                    }

                    // map을 다시 써야 하니까
                    // map 표시했던 공굴린 위치를 지운다.
                    for (Ball ball: balls) {
                        if (!ball.mbO) {
                            unmarkBall(ball);
                        }
                    }

                    if (bBlueO == false) {
                        if (!IsVisited(balls)) {
                            checkVisitied(balls);
                            que.add(balls);
                        }
                    }
                }
            }
        }

        return -1;
    }

    private void checkVisitied(Ball[] balls) {
        int ri = 0, rj = 0, bi = 0, bj = 0;

        for (Ball ball: balls) {
            if (ball.mColor == 'R') {
                ri = ball.mI;
                rj = ball.mJ;
            }
            else if (ball.mColor == 'B') {
                bi = ball.mI;
                bj = ball.mJ;
            }
        }

        visited[ri][rj][bi][bj] = true;
    }

    private boolean IsVisited(Ball[] balls) {
        int ri = 0, rj = 0, bi = 0, bj = 0;

        for (Ball ball: balls) {
            if (ball.mColor == 'R') {
                ri = ball.mI;
                rj = ball.mJ;
            }
            else if (ball.mColor == 'B') {
                bi = ball.mI;
                bj = ball.mJ;
            }
        }

        return visited[ri][rj][bi][bj];
    }

    Comparator<Ball> ltFirst = new Comparator<Ball>() {
        @Override
        public int compare(Ball b1, Ball b2) {
            if (b1.mJ < b2.mJ)
                return -1;

            if (b1.mJ > b2.mJ)
                return 1;

            return 0;
        }
    };

    Comparator<Ball> rtFirst = new Comparator<Ball>() {
        @Override
        public int compare(Ball b1, Ball b2) {
            if (b1.mJ < b2.mJ)
                return 1;

            if (b1.mJ > b2.mJ)
                return -1;

            return 0;
        }
    };

    Comparator<Ball> upFirst = new Comparator<Ball>() {
        @Override
        public int compare(Ball b1, Ball b2) {
            if (b1.mI < b2.mI)
                return -1;

            if (b1.mI > b2.mI)
                return 1;

            return 0;
        }
    };

    Comparator<Ball> dnFirst = new Comparator<Ball>() {
        @Override
        public int compare(Ball b1, Ball b2) {
            if (b1.mI < b2.mI)
                return 1;

            if (b1.mI > b2.mI)
                return -1;

            return 0;
        }
    };

    Comparator<Ball>[] comps = new Comparator[]{ltFirst, rtFirst, upFirst, dnFirst};

    class Ball implements Cloneable {
        public int mI, mJ;
        public char mColor;
        public boolean mbO;

        public void set(int i, int j, char color) {
            mI = i;
            mJ = j;
            mColor = color;
            mbO = false;
        }

        public Ball(int i, int j, char color) {
            set(i, j, color);
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private void rollBall(Ball ball, Direction dir) {
        int di = dIs[dir.mV];
        int dj = dJs[dir.mV];

        ball.mbO = false;

        while (map[ball.mI+di][ball.mJ+dj] == '.' || map[ball.mI+di][ball.mJ+dj] == 'O') {
            ball.mI += di;
            ball.mJ += dj;

            if (map[ball.mI][ball.mJ] == 'O') {
                ball.mbO = true;
                break;
            }
        }
    }

    private void markBall(Ball ball) {
        if (!ball.mbO) {
            map[ball.mI][ball.mJ] = ball.mColor;
        }
    }

    private void unmarkBall(Ball ball) {
        if (!ball.mbO) {
            map[ball.mI][ball.mJ] = '.';
        }
    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        Main main = new Main();
        main.solve();
    }
}