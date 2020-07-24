/**
 * BOJ 2602번 돌다리 건너기
 *
 * 문제링크 : https://www.acmicpc.net/problem/2602
 *
 * 유튜브 문제 풀이
 *
 *
 * 자바소스 :
 */
import java.util.Scanner;

public class Main {
    public void solve() {
        Scanner sc = new Scanner(System.in);

        String strMagic = "0" + sc.next() + "1";
        String[] strStones = new String[2];

        strStones[0] = "0" + sc.next() + "1";
        strStones[1] = "0" + sc.next() + "1";

        char[] magic = strMagic.toCharArray();
        char[][] dt = new char[2][];
        dt[0] = strStones[0].toCharArray();
        dt[1] = strStones[1].toCharArray();

        int cntStones = strStones[0].length();
        int[][][] dp = new int[2][magic.length][cntStones];

        dp[0][0][0] = 1;
        dp[1][0][0] = 1;

        for(int idxMagic = 1; idxMagic < magic.length; ++idxMagic) {
            char cPrev = magic[idxMagic-1];
            char cCrnt = magic[idxMagic];

            int AD = 0;

            for(int idxCrnt = 1; idxCrnt < cntStones; ++idxCrnt) {
                if(dt[AD][idxCrnt] == cCrnt) {
                    int ADBar = switchAngelDevel(AD);

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
                    int ADBar = switchAngelDevel(AD);

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

    public int switchAngelDevel(int a) {
        return (a == 0)? 1: 0;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}

