package com.tistory.skysign.topcoder.Alpha;

public class Alpha {
    public int maxPref(String s) {
        int i = 0;
        for(i=0; i<s.length(); ++i) {
            if(s.charAt(i) != (i+'a')) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
	// write your code here
        String s = "abctyf";
        Alpha alpha = new Alpha();
        int r = 0;
        r = alpha.maxPref(s);
        System.out.printf("%d\n", r);
    }
}
