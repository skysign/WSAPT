import java.util.ArrayList;
import java.util.Scanner;

/**
 * 13460번 구슬 탈출 2 / BOJ / acmicpc.net
 * 문제링크 : https://www.acmicpc.net/problem/13460
 * 답제출 : https://www.acmicpc.net/source/17221215
 */
public class Main {
    public int M;   // 구슬이 움직이는 판의 크기, 세로
    public int N;   // 가로

    public final int UP = 0;
    public final int DN = 1;
    public final int LL = 2;
    public final int RR = 3;

    public int[] mDi;
    public int[] mDj;

    public int mMovedI;
    public int mMovedJ;

    public char[][] mMap;
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
         * R이 어디 있는지 찾기
         */
        mR = findX('R');

        /**
         * B이 어디 있는지 찾기
         */
        mB = findX('B');

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

    private int lean(int Ri, int Rj, int Bi, int Bj, int n) {
        int[] rs = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

        if(n>10)
            return Integer.MAX_VALUE;

        RBPair rbpair = new RBPair(Ri, Rj, Bi, Bj);
        mVisitedMap.add(rbpair);

        // 위, 아래, 왼쪽, 오른쪽 순서로 이동 시켜 봄
        for(int dir=UP; dir<=RR; ++dir) {
            // 빨강공을 이동 시키고
            int Rmoving = move(dir, Ri, Rj);
            int RiMoved = mMovedI;
            int RjMoved = mMovedJ;

            // 파란공을 이동 시킴
            int Bmoving = move(dir, Bi, Bj);
            int BiMoved = mMovedI;
            int BjMoved = mMovedJ;

            // 구멍에 빠진 공이 있는지 확인
            // 파란공이 빠짐
            if('O' == mMap[BiMoved + mDi[dir]][BjMoved + mDj[dir]]) {
                rs[dir] = Integer.MAX_VALUE;
                continue;
            }
            // 빨간공이 빠짐
            else if ('O' == mMap[RiMoved + mDi[dir]][RjMoved + mDj[dir]]) {
                rs[dir] = n;
                continue;
            }

            // # 벽에 부딪혀서,
            // 빨간공과 파란공 둘다 움직이지 않은 경우
            if((RiMoved == Ri) && (RjMoved == Rj) && (BiMoved == Bi) && (BjMoved == Bj)) {
                continue;
            }

            // 두 공이 같은 위치에 있는 경우
            // 두 공중에 이동거리가 긴거를, 이동방향에서 반대 방향으로 이동 시킴
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

//            boolean bAdjacentNew = AreTwoBallsAdjacent(RiMoved, RjMoved, BiMoved, BjMoved);

            if(false == IsVisisted(RiMoved, RjMoved, BiMoved, BjMoved))
                rs[dir] = lean(RiMoved, RjMoved, BiMoved, BjMoved, n+1);
        }

        mVisitedMap.remove(rbpair);

        return Math.min(Math.min(rs[0], rs[1]), Math.min(rs[2], rs[3]));
    }

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