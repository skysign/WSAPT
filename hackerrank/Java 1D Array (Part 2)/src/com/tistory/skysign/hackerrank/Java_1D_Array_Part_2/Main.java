package com.tistory.skysign.hackerrank.Java_1D_Array_Part_2;

import java.util.Scanner;

public class Main {
    public static boolean canWin2(int leap, int[] g, int idx) {
        boolean rA = false, rB = false;
        // idx가 g 보다 크거나 같으면, win
        // g[idx -1] 0인 경우 한칸 더 앞으로 전진해서, idx == g.length 가 되었을 때, win으로 리턴해 줍니다.
        if(idx >= g.length)
            return true;
        // 전진해서 온 칸이 1이라면, 더이상 전진 못하기 때문에 false 리턴
        else if(g[idx] != 0)
            return false;
        
        // 앞으로 1칸 전진
        rA = canWin2(leap, g, idx +1);
        // leap아 0일 수도 있으므로, 0보다 클 때만,
        if(leap > 0)
            // leap 만큼 jump 합니다.
            rB = canWin2(leap, g, idx +leap);
        // 전진 2가지 방법이 모두 실패 하면,
        if(false == (rA || rB)) {
            // 뒤로 한칸 이동합니다.
            if((0<=idx-1) && (g[idx-1]==0)) {
                // idx에서는 전진 1칸, 전진 leap 점프
                // 모두 해봤는대, 실패 했으므로, g[idx](0)인대, 이를 1로 바꿔서
                // 이후에 다시 시도하지 않도록 합니다.
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
