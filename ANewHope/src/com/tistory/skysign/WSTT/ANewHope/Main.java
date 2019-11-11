package com.tistory.skysign.WSTT.ANewHope;

public class Main {
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