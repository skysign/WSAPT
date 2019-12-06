package com.tistory.skysign.LeetCode.Longest_Palindromic_Substring;

class Solution {
    public String longestPalindrome(String s) {
        int l = 0;
        char[] S = s.toCharArray();
        int SB =0, SE =0;
        final int N = s.length();

        if(s.length() < 2) {
            return s;
        }


        for(int i=0; i<N; ++i) {
            int sb = i;
            int se = sb +1;
            int cl = 0;

            while( (sb >= 0) && (se < N) && (S[sb] == S[se])) {
                cl++;
                sb--;
                se++;
            }

            cl<<=1;
            if(cl > l){
                l = cl;
                SB = sb+1;
                SE = se-1;
            }
        }

        for(int i=1; i<N; ++i) {
            int sb = i-1;
            int se = i+1;
            int cl = 0;

            while( (sb >= 0) && (se < N) && (S[sb] == S[se])) {
                cl++;
                sb--;
                se++;
            }

            cl<<=1;
            cl++;

            if(cl > l){
                l = cl;
                SB = sb+1;
                SE = se-1;
            }
        }


        return s.substring(SB, SE+1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s;
        String r = "";

        s = "dddddddd";
        r = solution.longestPalindrome(s);
        System.out.println(r);

        s = "babad";
        r = solution.longestPalindrome(s);
        System.out.println(r);

        s = "baab";
        r = solution.longestPalindrome(s);
        System.out.println(r);
    }
}
