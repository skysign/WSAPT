package com.tistory.skysign.hackerrank.Java_1D_Array_Part_2;

import java.util.Scanner;

public class Main {
    public static boolean canWin2(int leap, int[] g, int idx) {
        boolean rA = false, rB = false;

        if(idx >= g.length)
            return true;
        else if(g[idx] != 0)
            return false;

        rA = canWin2(leap, g, idx +1);
        if(leap > 0)
            rB = canWin2(leap, g, idx +leap);
        if(false == (rA || rB)) {
            if((0<=idx-1) && (g[idx-1]==0)) {
                g[idx] = 1;
                rB = canWin2(leap, g, idx-1);
            }
        }

        return rA || rB;
    }

    public static boolean canWin(int leap, int[] g) {
        return canWin2(leap, g, 0);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}
