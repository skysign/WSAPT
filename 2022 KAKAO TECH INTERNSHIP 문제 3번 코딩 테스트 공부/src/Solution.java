import java.util.Arrays;
/**
 * 2022 KAKAO TECH INTERNSHIP 문제 3번 코딩 테스트 공부
 *
 * 유튜브 문제 풀이: https://youtu.be/NTaYIADfTRE
 *
 * 자바소스: https://bit.ly/3VKnw4S
 */
class Solution {
    int MAX_ALP = 0, MAX_COP = 0;
    int[][] dp;

    public int solution(int alp, int cop, int[][] problems) {
        MAX_ALP = alp;
        MAX_COP = cop;

        for (int[] p : problems) {
            MAX_ALP = Math.max(MAX_ALP, p[0]);
            MAX_COP = Math.max(MAX_COP, p[1]);
        }

        dp = new int[MAX_ALP + 1][MAX_COP + 1];
        fill2D(dp, Integer.MAX_VALUE);

        for (int a = 0; a <= alp; ++a)
            for (int c = 0; c <= cop; ++c)
                dp[a][c] = 0;

        int[][] ps = new int[problems.length + 2][problems[0].length];
        copy2D(ps, problems);
        // 알고력을 높이기 위해 알고리즘 공부를 합니다. 알고력 1을 높이기 위해서 1의 시간이 필요합니다.
        ps[problems.length][0] = 0;
        ps[problems.length][1] = 0;
        ps[problems.length][2] = 1;
        ps[problems.length][3] = 0;
        ps[problems.length][4] = 1;

        // 코딩력을 높이기 위해 코딩 공부를 합니다. 코딩력 1을 높이기 위해서 1의 시간이 필요합니다.
        ps[problems.length + 1][0] = 0;
        ps[problems.length + 1][1] = 0;
        ps[problems.length + 1][2] = 0;
        ps[problems.length + 1][3] = 1;
        ps[problems.length + 1][4] = 1;

        for (int frA = 0; frA <= MAX_ALP; ++frA) {
            for (int frC = 0; frC <= MAX_COP; ++frC) {
                for (int[] p : ps) {
                    // 현재 알고/코딩력으로 풀수 있는가?
                    if ((frA >= p[0]) && (frC >= p[1])) {
                        int toA = frA + p[2];
                        int toC = frC + p[3];

                        toA = Math.min(toA, MAX_ALP);
                        toC = Math.min(toC, MAX_COP);
                        dp[toA][toC] = Math.min(dp[toA][toC], dp[frA][frC] + p[4]);
                    }
                }
            }
        }

        return dp[MAX_ALP][MAX_COP];
    }

    public void copy2D(int[][] dst, int[][] src) {
        for (int i = 0; i < src.length; ++i) {
            System.arraycopy(src[i], 0, dst[i], 0, src[i].length);
        }
    }

    public void fill2D(int[][] _2D, int v) {
        for (int[] _1D : _2D) {
            Arrays.fill(_1D, v);
        }
    }
}