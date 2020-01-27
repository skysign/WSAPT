import java.util.ArrayList;
import java.util.Scanner;

/**
 * 13460번 구슬 탈출 2 / BOJ / acmicpc.net
 * 문제링크 : https://www.acmicpc.net/problem/13460
 * 답제출 : https://www.acmicpc.net/source/17221215
 *
 * 다른분들의 풀이를 보면 BFS로 푸신 것 같아서, DFS로 풀이를 만들어 봤습니다.
 * 문제가 까다로운 부분이 몇가지 있어서, 풀기 좀 어려웠습니다.
 * - 공이 1개가 아니고 2개 빨간공과 파란공이 있다는 점
 * - 두 공을 굴려서, 이동 시켰을 때, 두 공이 만나는 것을 어떻게 구현할 것인지?
 * - DFS의 stop condition 을 어떻게 정해야 정답이 되는지?
 */
public class Main {
    public int M;   // 구슬이 움직이는 판의 크기, 세로
    public int N;   // 가로

    // 공을 굴리는 방향, 위/아래/왼쪽/오른쪽 입니다.
    // 순서는 중요하지 않고, 4방향으로 모두다 시도하는 것이 중요합니다.
    public final int UP = 0;
    public final int DN = 1;
    public final int LL = 2;
    public final int RR = 3;

    // 4방향으로 공을 굴릴 때, 각 방향별로 코딩을 하면 라인수도 길어지고,
    // 길어진 라인수 만큼, 어느 부분에 코드 수정을 하기도 어려워집니다.
    // 이문제 처럼 4방향 모두 한번씩 가봐야 하는 문제는, 아래와 같이 배열로 이동 방향을 정의해두고,
    // 루프로 0~3까지, 즉 UP 부터 RR 까지 루프로 구현하는게 편리합니다.
    public int[] mDi; // = new int[]{-1, 1, 0, 0};
    public int[] mDj; // = new int[]{0, 0, -1, 1};

    public int mMovedI;
    public int mMovedJ;

    public char[][] mMap;

    // 한번 2개의 공이 방문한 곳은 더이상 방문하지 않아야 합니다.
    // 따라서, 방문했던 위치를 저장해두는 리스트 입니다.
    // 이 문제에서는 구슬을 굴리는 회수가 10으로 제한되어 있어서,
    // ArrayList를 사용하지 않고, 10개만 저장하고 있어도 됩니다.
    public ArrayList<RBPair> mVisitedMap;

    public class RBPair{
        public int mRi;
        public int mRj;
        public int mBi;
        public int mBj;

        public RBPair(int Ri, int Rj, int Bi, int Bj) {
            mRi = Ri;
            mRj = Rj;
            mBi = Bi;
            mBj = Bj;
        }

        public boolean equals(int Ri, int Rj, int Bi, int Bj) {
            if( (mRi == Ri) &&
                    (mRj == Rj) &&
                    (mBi == Bi) &&
                    (mBj == Bj)) {
                return true;
            }

            return false;
        }
    }

    /**
     * 빨간공과 파란공의 의치를 리턴하기 위한 클래스 입니다.
     * @param <I>
     * @param <J>
     */
    public class Pair<I, J> {
        public I mi;
        public J mj;

        public Pair(I i, J j) {
            set(i, j);
        }

        public void set(I i, J j){
            mi = i;
            mj = j;
        }
    }

    Pair<Integer, Integer> mR;
    Pair<Integer, Integer> mB;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        mMap = new char[M][N];
//        mVisitedMap = new int[M][N];
        mDi = new int[]{-1, 1, 0, 0};
        mDj = new int[]{0, 0, -1, 1};

        mVisitedMap = new ArrayList();

        sc.nextLine();

        for(int i=0; i<M; ++i) {
            String str = sc.nextLine();
            for(int j=0; j<N; ++j) {
                mMap[i][j] = str.charAt(j);
            }
        }

        /**
         * 빨간공이 어디 있는지 찾기
         */
        mR = findX('R');

        /**
         * 파란공이 어디 있는지 찾기
         */
        mB = findX('B');

        // 판을 기울여서, 공을 굴리기 시작합니다.
        // lean()을 한번 호출하는 것이 4개의 방향으로 다 한번씩 가보는 것이기 때문에,
        // n을 1로 시작합니다.
        int r = lean(mR.mi, mR.mj, mB.mi, mB.mj, 1);
        if(r > 10)
            r = -1;
        System.out.println(r);
    }

    boolean IsVisisted(int Ri, int Rj, int Bi, int Bj) {
        for(RBPair p : mVisitedMap) {
            if(p.equals(Ri, Rj, Bi, Bj))
                return true;
        }

        return false;
    }

    /**
     * 빨간공과 파란공의 위치를 인자로 받고, 판을 기울여서 2개의 공을 굴리는 메서드입니다.
     * @param Ri 빨간공 위치
     * @param Rj
     * @param Bi 파란공 위치
     * @param Bj
     * @param n 판을 기울인 회수
     * @return Integer.MAX_VALUE 구멍에 파란공이 먼저 들어감 or
     *                           모든 경우에 빨간공이 구멍이 들어갈 수 없음 or
     *                           10회 판을 기울였지만, 빨간공이 구멍이 들어갈 수 없음
     *         1 <= return <10   return 회 기울이면, 빨간공이 구멍에 들어감
     */
    private int lean(int Ri, int Rj, int Bi, int Bj, int n) {
        // lean을 한번 하면 4가지(위/아래/왼쪽/오른쪽) 결과를 얻을 수 있습니다.
        // lea() 메서드에서 리턴할 때, 가장 작은 값을 리턴해야 함으로,
        // Integer.MAX_VALUE 로 초기화합니다.
        int[] rs = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

        // 문제에서 10회 이상 기울였는대, 구멍에 빠지지 않았다면, 불가능 한 것으로 리턴합니다.
        // 혹시 제출했는대, 4%에서 멈춰있다면, 10회 이상은 불가능 처리를 안했을 확률이 높습니다.
        if(n>10)
            return Integer.MAX_VALUE;

        // 한번 2공이 방문했던 곳은, 이후에 추가로 방문하지 않기 위해서, 저장해 둡니다.
        RBPair rbpair = new RBPair(Ri, Rj, Bi, Bj);
        mVisitedMap.add(rbpair);

        // 빨간공과 파란공을 위, 아래, 왼쪽, 오른쪽 순서로 이동 시킴니다.
        for(int dir=UP; dir<=RR; ++dir) {
            // 빨강공을 이동 시키고
            int Rmoving = move(dir, Ri, Rj);
            int RiMoved = mMovedI;
            int RjMoved = mMovedJ;

            // 파란공을 이동 시킴
            int Bmoving = move(dir, Bi, Bj);
            int BiMoved = mMovedI;
            int BjMoved = mMovedJ;

            // * 구멍에 빠진 공이 있는지 확인
            // 파란공이 구멍에 빠지면, 빨간공이 구멍에 빠진것과 관계없이,
            // 실패이기 때문에, 파란공이 구멍에 빠졌는지 먼저 확인합니다.
            if('O' == mMap[BiMoved + mDi[dir]][BjMoved + mDj[dir]]) {
                rs[dir] = Integer.MAX_VALUE;
                continue;
            }
            // 빨간공이 빠졌는지 확인
            else if ('O' == mMap[RiMoved + mDi[dir]][RjMoved + mDj[dir]]) {
                rs[dir] = n;
                continue;
            }

            // # 벽에 부딪혀서,
            // 빨간공과 파란공 둘다 움직이지 않은 경우, 이 위치에서는 다시 판을 기울일 필요가 없습니다.
            // rs 는 모두 Integer.MAX_VALUE로 초기화 되어 있기 때문에,
            // continue를 사용해서, 다음번 방향으로 진행합니다.
            if((RiMoved == Ri) && (RjMoved == Rj) && (BiMoved == Bi) && (BjMoved == Bj)) {
                continue;
            }

            // 공을 굴렸는대, 두 공이 같은 위치에 있는 경우 입니다.
            // 이 경우를 해결하기가 좀 어려웠었는대요,
            // 실제로는, 2개의 공은 같은 위치에 있을 수 없기 때문입니다.
            // move()메서드는 공의 이동 거리를 리턴해줍니다.
            // 두 공중에 이동거리가 긴거를, 이동방향에서 한칸 반대 방향으로 이동 시키면
            // 두 공이 겹치는 문제를 해결할 수 있습니다.
            if((RiMoved == BiMoved) && (RjMoved == BjMoved)) {
                if(Rmoving < Bmoving) {
                    BiMoved -= mDi[dir];
                    BjMoved -= mDj[dir];
                }
                else {
                    RiMoved -= mDi[dir];
                    RjMoved -= mDj[dir];
                }
            }

            // 이부분이 brute force로 recursive 호출이 되는 lean() 함수를 멈춰주는,
            // stop condition 입니다. 방문했던 위치는 다시 방문하지 않도록 해주는 중요한 부분입니다.

            // 보통의 길찾기 문제에서는, 맵위에 1개가 이동하는대,
            // 이 문제에서는, 2개의 공이 동시에 이동 하기 때문에,
            // 파란공의 위치에 따라서, 빨간공이 새로운 위치로 이동 할 수 도 있습니다.
            // 따라서, 2개의 공의 위치를 모두 사용하여, stop condition을 구현해야 합니다.
            // 이렇게 하지 않으면, 아래 테스트 케이스를 모두 통과할 수 없습니다.
            // 테스트 케이스 https://www.acmicpc.net/board/view/17710
            if(false == IsVisisted(RiMoved, RjMoved, BiMoved, BjMoved))
                rs[dir] = lean(RiMoved, RjMoved, BiMoved, BjMoved, n+1);
        }

        mVisitedMap.remove(rbpair);

        return Math.min(Math.min(rs[0], rs[1]), Math.min(rs[2], rs[3]));
    }

    /**
     * 방향을 인자로 받아, 해당 방향으로 공을 굴린다.
     * @param dir
     * @param i 공의 i 위치
     * @param j 공의 j 위치
     * @return 공의 이동 거리
     */
    public int move(int dir, int i, int j) {
        int cnt = 0;
        while(mMap[i][j] != '#' && mMap[i][j] != 'O') {
            i += mDi[dir];
            j += mDj[dir];
            cnt++;
        }

        i -= mDi[dir];
        j -= mDj[dir];
        cnt--;

        mMovedI = i;
        mMovedJ = j;

        return cnt;
    }

    /**
     * 맵에서 빨간공 R 또는 파란공 B의 위치를 찾는다.
     * @param x
     * @return
     */
    public Pair<Integer, Integer> findX(char x) {
        for(int i=1; i<mMap.length-1; ++i) {
            for(int j=1; j<mMap[0].length-1; ++j) {
                if(mMap[i][j] == x) {
//                    System.out.println(x + " is found");
                    return new Pair<Integer, Integer>(i, j);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}