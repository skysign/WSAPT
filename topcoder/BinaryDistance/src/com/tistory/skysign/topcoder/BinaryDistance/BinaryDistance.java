package com.tistory.skysign.topcoder.BinaryDistance;

public class BinaryDistance {
//    첫번째 풀기 시도한 것
//    풀기는 했지만, 코드가 너무 길고, 풀이가 복잡함
//    public int findMyLevel(int V, int r) {
//        if(V == 1){
//            return r;
//        }
//
//        return findMyLevel(V/2, r+1);
//    }
//
////    1 : left
////    0 : right
//    public int findLR(int V) {
//        if(V > 3) {
//            return findLR(V/2);
//        }
//
//        return (V == 2)? 1: 0;
//    }
//
//    public int maxDist(int N, int V) {
////      리턴값
//        int r = 0;
//
//        // 트리의 높이 l
//        int l = 0;
//
//        // level층 까지의 숫자 합
//        int levelSum = 0;
//
//        // V 가 트리의 왼편인지 오른편인지
//        int LR = 0;
//
//        boolean isRightShort = false;
//        for(int i=0; true; ++i) {
//            levelSum += Math.pow(2,i);
//
//            if( (levelSum < N) && (N <= levelSum+Math.pow(2,i+1)) ) {
//                if(N <= levelSum + (Math.pow(2,i+1)/2)) {
//                    isRightShort = true;
//                }
//                else {
//                    isRightShort = false;
//                }
//
//                l = i+1;
//                break;
//            }
//        }
//
//        int ll = findMyLevel(V, 0);
//        if(isRightShort) {
//            LR = findLR(V);
//            r = ll + ((l) - LR);
//        }
//        else {
//            r = ll + (l);
//        }
////        System.out.printf("levels : %d\n", l);
//        return r;
//    }

    // SRM 768 EDITORIALS를 보고 난뒤,
    // 보다 빠른 방법을 풀어봄
    // is_left()와 depth()를 하나로 합처서, depth_LR()로 계산함
    // left : LR[0](true)
    // right : LR[0](false)
    public int depth_LR(int a, boolean[] LR) {
        int v = a;
        int d = 1;
        while(v>3) {
            d++;
            v >>= 1;
        }

        if(v == 2) {
            LR[0] = true;
        }
        else {
            LR[0] = false;
        }

        return d;
    }

    public int maxDist(int N, int V) {
        int dN, dV;
        boolean[] lrN = {true};
        boolean[] lrV = {true};

        int r;

        if(V == 1) {
            return depth_LR(N,lrN);
        }

        dN = depth_LR(N, lrN);
        dV = depth_LR(V, lrV);

        r = dN + dV;

        if(lrN[0]) {
            if(lrV[0])
                r--;
        }

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
