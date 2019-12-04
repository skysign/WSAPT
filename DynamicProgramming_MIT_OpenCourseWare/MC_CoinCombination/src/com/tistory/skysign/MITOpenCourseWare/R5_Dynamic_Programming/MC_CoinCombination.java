package com.tistory.skysign.MITOpenCourseWare.R5_Dynamic_Programming;

// 코드 보시기 전에, 아래 유튜브 DP영상을 꼭 보시고 코드를 봐주세요.
// https://www.youtube.com/watch?v=PafJOaMzstY

public class MC_CoinCombination {
    public void d0(int[][] dp) {
        System.out.print("    ");

        for(int j=0; j<dp[0].length; ++j){
            System.out.printf("%3d ", j);
        }
        System.out.println();

        for(int i=0; i<dp.length; ++i){
            System.out.printf("%3d|", i);
            for(int j=0; j<dp[0].length; ++j){
                System.out.printf("%3d ", dp[i][j]);
            }
            System.out.println();
        }
    }

    // S 가 동전의 종류
    // M이 동전 종류의 갯수
    // change 가 잔돈
    public int MC(int[] S, int M, int change) {
        // 배열의 인덱스가 코인의 종류, 잔돈의 크기와 같게 하기 위해서,
        // +1 씩 해줍니다.
        // 코인의 종류가 3개 이면, dp[3][] 까지 계산합니다.
        int[][] dp = new int[M+1][change+1];

        // #1 코인 0 이 있다고 가정하고,
        // change(0)을 만들 수 있는건, 0코인이 하나만 가능, 0,0 조합이나, 0,1 조합은 없다고 가정
        for(int i=0; i<M+1; ++i) {
            dp[i][0] = 1;
        }

        // #2 0코인을 가지고, 1~change 를 만드는 조합은 없으므로, 전부다 0
        for(int j=1; j<change+1; ++j) {
            dp[0][j] = 0;
        }

        // dp 배열의 의미에 대해서 알아 보겠습니다.
        // 첫줄(dp[0][])인 0 코인을 가지고 만들 수 있는 조합은 앞의 #1 과 #2 에서 초기화 했습니다.
        // 사실 #2는 0코인이라기 보다는, 코인1개와 잔돈이 같을 때, 동전조합의 갯수를 +1 해주기 위해서 사용합니다.
        // 이부분은 좀 복잡하니까, 뒤에서 좀더 설명하겠습니다.
        // dp[i][j] 는 0코인 부터 S[i-1]코인을 가지고, j을 만들 수 있는 조합의 수를 나타냅니다.

        // 예를 들어, {0, 1, 2} 코인을 가지고 잔돈(5)를 만들 수 있는 조합의 수를 찾아 내려면,
        // {0, 1}로 잔돈 5을 만들 수 있는 동전조합의 수 <-1 t1의 의미
        // {0, 1, 2}로 잔돈(5-2)를 만들 수 있는 동전 조합의 수 <- t2의 의미
        //    다만,t2를 계산할 때, dp[2][1] 과 같은 경우에 읍수가 나오는 경우가 있어서 > 0클 때만 dp[i][j-S[i1]]을 더합니다.

        // dp[][0] 컬럼에 1을 미리 assign 하는 것이 문제를 쉽게 풀 수 있는 방법입니다.
        // 예를 들어, dp[2][2]를 계산할 때, 즉, 0, 1, 2 코인을 가지고, 잔돈(2)를 만드는 방법
        // t1은 dp[1][2] 이 되어 1 (즉, (1, 1)조합의수 1개)
        // t2는 dp[2][0] 이 되어서, 1 (즉, (2) 조합의수 1개)
        // 따라서, dp[2][2]는 2가 됩니다.

        // 이어서 dp[2][3]과 dp[2][4]를 설명해 보면,
        // t1은 dp[1][3] 이 되고, 1(즉, (1, 1, 1)조합의수 1개)
        // t2는 dp[2][1] 이 되고, 1(즉, (2)조합의수 1개)
        // 따라서, dp[2][3]은 2가 되구요.
        // dp[2][4]는
        // t1은 dp[1][4] (1, 1, 1)조합의수 1개
        // t2는 dp[2][2] (1,1) (2) 조합의 수 2개 -> 이부분은 이렇게 볼 수 있는대, (1,1)(2) 가 (1,1,2)(2,2) 되는 것이죠
        // 따라서, dp[2][4]는 3이 됩니다.

        // 이 부분이 dp 프로그래밍의 핵심적인 부분입니다.
        // i 는 컬럼으로, 동전의 종류별
        // j 는 잔돈입니다.
        for(int i=1; i<M+1; ++i) {
            for(int j=1; j<change+1; ++j) {
                int t1 = dp[i-1][j];
                int t2 = (j - S[i-1] >= 0)? dp[i][j - S[i-1]]: 0;
                dp[i][j] =  t1 + t2;
            }
        }

        d0(dp);

        return dp[M][change];
    }

    public static void main(String[] args) {
//	    int[]S = {1, 2, 3};
//	    int M = S.length;
//	    int change = 3;

//        int[]S = {1, 2, 3, 4, 5};
//        int M = S.length;
//        int change = 5;

        int[]S = {1, 2, 3, 4, 5};
        int M = S.length;
        int change = 7;

	    int r;
	    MC_CoinCombination mc = new MC_CoinCombination();

	    r = mc.MC(S, M, change);
	    System.out.println(r);
    }
}
