/**
 * BOJ 2602번 돌다리 건너기
 *
 * 문제링크 : https://www.acmicpc.net/problem/2602
 *
 * 유튜브 문제 풀이
 * https://youtu.be/6mQQh0cELZc
 *
 * 자바소스 : https://bit.ly/3hzVWCP
 */
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    char[] magic;
    int[][][] dp;
    char[][] dt;
    int cntStones;

    public void pre() {
        Scanner sc = new Scanner(System.in);

        String strMagic = "0" + sc.next() + "1";
        String[] strStones = new String[2];

        strStones[0] = "0" + sc.next() + "1";
        strStones[1] = "0" + sc.next() + "1";

        magic = strMagic.toCharArray();
        dt = new char[2][];
        dt[0] = strStones[0].toCharArray();
        dt[1] = strStones[1].toCharArray();

        cntStones = strStones[0].length();
        dp = new int[2][magic.length][cntStones];
    }

    public void solve() {
        pre();

        dp[0][0][0] = 1;
        dp[1][0][0] = 1;

        for(int idxMagic = 1; idxMagic < magic.length; ++idxMagic) {
            char cPrev = magic[idxMagic-1];
            char cCrnt = magic[idxMagic];

            int AD = 0;

            for(int idxCrnt = 1; idxCrnt < cntStones; ++idxCrnt) {
                if(dt[AD][idxCrnt] == cCrnt) {
                    int ADBar = switchAngelDevil(AD);

                    for(int idxPrev = 0; idxPrev < idxCrnt; ++idxPrev) {
                        if(dt[ADBar][idxPrev] == cPrev) {
                            dp[AD][idxMagic][idxCrnt] += dp[ADBar][idxMagic-1][idxPrev];
                        }
                    }
                }
            }

            AD = 1;

            for(int idxCrnt = 1; idxCrnt < cntStones; ++idxCrnt) {
                if(dt[AD][idxCrnt] == cCrnt) {
                    int ADBar = switchAngelDevil(AD);

                    for(int idxPrev = 0; idxPrev < idxCrnt; ++idxPrev) {
                        if(dt[ADBar][idxPrev] == cPrev) {
                            dp[AD][idxMagic][idxCrnt] += dp[ADBar][idxMagic-1][idxPrev];
                        }
                    }
                }
            }
        }

        System.out.println(dp[0][magic.length-1][cntStones-1] + dp[1][magic.length-1][cntStones-1]);
    }

    public void solve2() {
        pre();
        int idxLastMagic = magic.length-1;
        int idxLastStones = cntStones-1;

        fill3D(dp, -1);

        dp[0][0][0] = 1;
        dp[1][0][0] = 1;

        int AD = 0;
        dp[AD][idxLastMagic][idxLastStones] = 0;
        sumOfPrevStones(AD, idxLastMagic, idxLastStones);

        AD = 1;
        dp[AD][idxLastMagic][idxLastStones] = 0;
        sumOfPrevStones(AD, idxLastMagic, idxLastStones);

        System.out.println(dp[0][idxLastMagic][idxLastStones] + dp[1][idxLastMagic][idxLastStones]);
    }

    public void sumOfPrevStones(int AD, int idxLaterMagic, int idxLaterStones) {
        if(!(0<idxLaterMagic))
            return;

        int ADBar = switchAngelDevil(AD);

        int idxMagic = idxLaterMagic-1;
        char cStone = magic[idxMagic];
        for(int idxStones=idxLaterStones-1; 0 <= idxStones; --idxStones) {
            if(dt[ADBar][idxStones] == cStone) {
                if(-1 == dp[ADBar][idxMagic][idxStones]) {
                    dp[ADBar][idxMagic][idxStones] = 0;
                    sumOfPrevStones(ADBar, idxMagic, idxStones);
                }

                dp[AD][idxLaterMagic][idxLaterStones] += dp[ADBar][idxMagic][idxStones];
            }
        }
    }


    public int switchAngelDevil(int a) {
        return (a == 0)? 1: 0;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
        //main.solve2();
    }

    // Initialize 3D arrays with value v
    public void fill3D(int[][][] _3D, int v) {
        for(int[][] _2D: _3D) {
            for(int[] _1D: _2D) {
                Arrays.fill(_1D, v);
            }
        }
    }
}