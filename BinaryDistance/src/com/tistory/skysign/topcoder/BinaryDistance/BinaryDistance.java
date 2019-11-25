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

            if(levelSum==N) {
                isRightShort = false;
                l = i+1;
                break;
            }

            if( (levelSum<N) && (N<levelSum+Math.pow(2,i+1)) ) {
                if(N <= levelSum + (Math.pow(2,i+1)/2)) {
                    isRightShort = true;
                }
                else {
                    isRightShort = false;
                }

                l = i+2;
                break;
            }
        }

        int ll = findMyLevel(V, 0);
        if(isRightShort) {
            LR = findLR(V);
            r = ll + ((l-1) - LR);
        }
        else {
            r = ll + (l-1);
        }

        System.out.printf("levels : %d\n", l);

        return r;
    }

    public static void main(String[] args) {
        BinaryDistance c = new BinaryDistance();
        int r = c.maxDist(12, 4);
        System.out.printf("%s\n", r);
    }
}
