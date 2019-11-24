package com.tistory.skysign.hackerrank.java.BigNumber.JavaBigDecimal;

import java.math.BigDecimal;

public class Solution {
    public static void main(String[] args) {
        final int n=9;
        String[] s = {"-100", "50", "0", "56.6", "90", "0.12", ".12", "02.34", "000.000"};
//      버블소트를 구현할 때, 보통 아래와 같이 이중루프를 사용합니다.
//      for(int i=0; i<n; ++i)
//          for(int j=i+1; j<n; ++j)
//      이 문제에서는, 위와 같이 하면, 0.12와 .12의 순서가 서로 뒤바뀌게 됩니다.
//      이런 문제를 방지 하기 위해서, 아래와 같이 인접한 인덱스끼리 서로 비교해서 자리를 바꿔야만
//      0.12와 .12가 순서가 바뀌는 문제를 피할 수 있습니다.
        for(int i=1; i<=n-1; ++i)
            for(int j=0; j<n-i; ++j){
                BigDecimal a = new BigDecimal(s[j]);
                BigDecimal b = new BigDecimal(s[j+1]);
                if(a.compareTo(b) < 0) {
                    String t = s[j];
                    s[j] = s[j+1];
                    s[j+1] = t;
                }
            }

        //Output
        for(int i=0;i<n;i++)
        {
            System.out.println(s[i]);
        }
    }
}
