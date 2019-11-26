package com.tistory.skysign.topcoder.BinaryDistance;

public class BinaryDistance {
    public int findMyLevel(int V, int r) {
        if(V == 1){
            return r;
        }

        return findMyLevel(V/2, r+1);
    }

//    1 : left
//    0 : right
    public int findLR(int V) {
        if(V > 3) {
            return findLR(V/2);
        }

        return (V == 2)? 1: 0;
    }

    public int maxDist(int N, int V) {
//      리턴값
        int r = 0;

        // 트리의 높이 l
        int l = 0;

        // level층 까지의 숫자 합
        int levelSum = 0;

        // V 가 트리의 왼편인지 오른편인지
        int LR = 0;

        boolean isRightShort = false;
        for(int i=0; true; ++i) {
            levelSum += Math.pow(2,i);

            if( (levelSum < N) && (N <= levelSum+Math.pow(2,i+1)) ) {
                if(N <= levelSum + (Math.pow(2,i+1)/2)) {
                    isRightShort = true;
                }
                else {
                    isRightShort = false;
                }

                l = i+1;
                break;
            }
        }

        int ll = findMyLevel(V, 0);
        if(isRightShort) {
            LR = findLR(V);
            r = ll + ((l) - LR);
        }
        else {
            r = ll + (l);
        }

//        System.out.printf("levels : %d\n", l);

        return r;
    }

    public static void main(String[] args) {
        BinaryDistance c = new BinaryDistance();
        int r = 0;
        r = c.maxDist(6, 2);
        System.out.printf("Returns: %s\n", r);

        r = c.maxDist(6, 6);
        System.out.printf("Returns: %s\n", r);

        r = c.maxDist(5, 4);
        System.out.printf("Returns: %s\n", r);

        r = c.maxDist(2, 2);
        System.out.printf("Returns: %s\n", r);

        r = c.maxDist(1000000000, 1);
        System.out.printf("Returns: %s\n", r);

        r = c.maxDist(20, 7);
        System.out.printf("Returns: %s\n", r);

        r = c.maxDist(12, 4);
        System.out.printf("Returns: %s\n", r);
    }
}
