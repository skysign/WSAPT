import java.util.Scanner;

/**
 * 13460번 구슬 탈출 2 / BAEKJOON ONLINE JUDGE / acmicpc.net
 * 우선 빨간공만 고려해서, 기울이는 회수를 알아내는 풀이입니다.
 * 문제링크 : https://www.acmicpc.net/problem/13460
 */
public class Main {
    public int mHoleI = -1;
    public int mHoleJ = -1;
    public int mRI = -1;
    public int mRJ = -1;
    public char[][] mMap;
    public int[][] mVisitedMap;

    public int M;
    public int N;
    public final int L = 0;
    public final int R = 1;
    public final int T = 2;
    public final int B = 3;

    public void solve() {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        mMap = new char[M][N];
        mVisitedMap = new int[M][N];

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
        findR('R');

        /**
         * R이 어디 있는지 찾기
         */
        findR('R');

        int r = lean(mRI, mRJ, 1);
        if(r == Integer.MAX_VALUE) {
            r = -1;
        }
        if(r > 10){
            r = -1;
        }

        System.out.println(r);
    }

    /*
    빨간공과 파란공 모두를 동시에 고려하기는 어려우니까, 빨간공을 구멍에 넣는것 부터 먼저 풀어보겠습니다.
     */
    public int lean(int begI, int begJ, int n) {
        mVisitedMap[begI][begJ] = 1;
        /**
         * 상 하 좌 우 에 갈수 있는 길을 가봄
         */
        int r1 = leanV(begI, begJ, -1, n);
        int r2 = leanV(begI, begJ, +1, n);
        int r3 = leanH(begI, begJ, -1, n);
        int r4 = leanH(begI, begJ, +1, n);

        return Math.min(Math.min(r1, r2), Math.min(r3, r4));
    }

    public int leanV(int begI, int begJ, int delta, int n) {
        int i = begI + delta;
        int j = begJ;

        while(mMap[i][j] == '.' && (0<i) && (i<M)) {
            i = i + delta;
        }

        if(mMap[i][j] == 'O') {
            return n;
        }
        else if(mMap[i][j] == '#') {
            i -= delta;
        }

        if(((i != begI) || (j != begJ)) && (mVisitedMap[i][j] == 0)) {
            return lean(i, j, n+1);
        }

        return Integer.MAX_VALUE;
    }

    public int leanH(int begI, int begJ, int delta, int n) {
        int i = begI;
        int j = begJ + delta;

        while(mMap[i][j] == '.' && (0<j) && (j<N)) {
            j = j + delta;
        }

        if(mMap[i][j] == 'O') {
            return n;
        }
        else if(mMap[i][j] == '#') {
            j -= delta;
        }

        if(((i != begI) || (j != begJ)) && (mVisitedMap[i][j] == 0)) {
            return lean(i, j, n+1);
        }

        return Integer.MAX_VALUE;
    }

    public void findR(char x) {
        for(int i=1; i<mMap.length-1; ++i) {
            for(int j=1; j<mMap[0].length-1; ++j) {
                if(mMap[i][j] == x) {
                    mRI = i;
                    mRJ = j;
                    System.out.println("R is found");
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}