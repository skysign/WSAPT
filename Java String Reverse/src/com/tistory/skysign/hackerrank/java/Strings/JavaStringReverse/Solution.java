package com.tistory.skysign.hackerrank.java.Strings.JavaStringReverse;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        int l = A.length();
        // 0
        // l/2
        // l/2 + (l%2)
        // l/2 + (l%2) + (l/2)

        char[] sA = A.substring(0, l/2).toCharArray();
        char[] sB= A.substring(l/2+l%2, l/2+l%2+(l/2)).toCharArray();

        boolean bSame = true;

        for(int i=0; i<l/2; ++i) {
            if (sA[i] != sB[l/2-1-i]) {
                bSame = false;
                break;
            }
        }

        if(bSame)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}
