package com.tistory.skysign.WSTT.ANewHope;

import java.util.Arrays;

public class ANewHope {

    public int count(int[] fw, int[] lw, int D) {
        int N = fw.length;
        int weeks = 1;

        if (Arrays.equals(fw, lw)) return 1;

//      이 풀이에서는 루프를 두번 사용해서,
//      같은 숫자가 first week에 있는 위치와, last week에 있는 위치를 찾았습니다.
//	    이보다 빠른 방법은, 숫자와 어레이 인덱스를 서로 변경해서
//      루프를 한번만 사용해서, last week - first week 한뒤, 가장 작은 값을 찾는 방법도 있습니다.
//	    루프를 한번만 사용하는 방법은 여기참고 → https://github.com/kmdigit/TopCoder/blob/master/ANewHope/ANewHope.java

        for(int i=0; i<N; ++i)
            for(int j=0; j<i; ++j)
                if(fw[i] == lw[j]) {
                    int v = (i-j) / (N-D);
//	                (i-j)는 first week에서 last week로 이동해야 하는 거리
//	                (N-D)는 한번에 이동할 수 있는 거리

//                  이 부분은, 이렇게 작성해도 됩니다.
//	                / 또는 % 를 되도록이면, 사용하지 않으려고, 코드를 약간 변경하였습니다.
//	                if((i-j) % (N-D) > 0)
//	                	++v;
                    if(v*(N-D) < (i-j))
                        ++v;
                    weeks = Math.max(weeks, v);
//                  가장 많은 주가 필요한 숫자 하나만 찾습니다.
//					System.out.printf("fw[i %d]%d lw[j %d]%d weeks(%d) v(%d)\n", i, fw[i], j, lw[j], weeks, v);
                }

        return weeks +1;
    }

    public static void main(String[] args) {
        ANewHope aNewHope = new ANewHope();
        int[] fw;
        int[] lw;
        int D;
        int r;

        fw = new int[]{1,2,3,4};
        lw = new int[]{4,3,2,1};
        D = 3;
        r = aNewHope.count(fw, lw, D);
        System.out.printf("r %d\n", r);

        fw = new int[]{8,5,4,1,7,6,3,2};
        lw = new int[]{2,4,6,8,1,3,5,7};
        D = 3;
        r = aNewHope.count(fw, lw, D);
        System.out.printf("r %d\n", r);

        fw = new int[]{1,2,3,4};
        lw = new int[]{1,2,3,4};
        D = 2;
        r = aNewHope.count(fw, lw, D);
        System.out.printf("r %d\n", r);
    }
}
