package com.tistory.skysign.MITOpenCourseWare.R5_Dynamic_Programming;

import java.util.ArrayList;

// 동전의 조합(1, 3, 5 ...)이 주어졌을 때,
// 특정 잔돈(change)을 만들 때, 가장 적은 동전의 갯수로
// 원하는 잔돈을 만드는 방법에 대해서 알아 보겠습니다.

// 가정
// 각 동전의 개수는 무한함
// 동전의 종류가 오름차순(Ascending order)로 정렬되어 있음
// 동전의 조합에 첫번째 코인은 무조건 1임

// 첫 동전이 1인 것은 매우 중요합니다. 왜냐하면, 1이 있으면, 어떤 잔돈이든 만들 수 있지만,
// 첫 동전이 1이
public class MC_min_numberOfCoins {
    int mChange;

    // 디버깅및 로그 프린트를 위한 함수 입니다.
    public void d0(int numOfCoins, ArrayList<Integer> cp) {
        if (numOfCoins != cp.size()) {
            System.out.printf("bug -_-;\n");
        }
        else {
            System.out.printf("Num of Coins : %d \n", numOfCoins);
        }
        int sum = 0;
        for(int c : cp) {
            System.out.printf("%3d ", c);
            sum += c;
        }
        System.out.printf(" sum:%d %s\n", sum, (mChange == sum)? "Found": "failed");
    }

    public int makeChange(int[] S, int M, int change, int numOfCoins, ArrayList<Integer> cp) {
        if (change < 0) {
            d0(numOfCoins, cp);
            cp.clear();
            cp = null;
            return Integer.MAX_VALUE;
        }

        if (0 == change) {
            d0(numOfCoins, cp);
            cp.clear();
            cp = null;
            return numOfCoins;
        }

        int r = Integer.MAX_VALUE;

        ++numOfCoins;
        for(int i=0; i<M; ++i) {
            ArrayList<Integer> cp2 = new ArrayList<>(cp);
            cp2.add(S[i]);

            r = Math.min(r,
                    makeChange(S, M, change - S[i], numOfCoins, cp2));
        }

        cp.clear();
        cp = null;

        return r;
    }

    // S 동전의 종류 배열
    // M 동전 종류의 갯수
    // change 원하는 잔돈
    public int MC(int[] S, int M, int change) {
        mChange = change;
        return makeChange(S, M, change, 0, new ArrayList<Integer>());
//        int r = Integer.MAX_VALUE;
//
//        for(int i=0; i<M; ++i) {
//            // cp 변수는 실제로 문제를 푸는대 필요한 변수는 아닙니다.
//            // 리커전으로 호출 될 때, 동전이 어떻게 추가 되는지 보기 위한 변수일 뿐입니다.
//            // 참고로, 변수이름 cp 는 coin permutation 의 약자로 코인 순열을 뜻합니다.
//            ArrayList<Integer> cp = new ArrayList<>();
//            cp.add(S[i]);
//            r = Math.min(r,
//                    makeChange(S, M, change - S[i], numOfCoins, cp));
//        }
    }

    public static void main(String[] args) {
//        int[] S = {1, 5, 10};
//        int M = S.length;
//        int change = 3;

//        int[] S = {1, 5, 10};
//        int M = S.length;
//        int change = 7;

        int[] S = {1, 5, 10};
        int M = S.length;
        int change = 13;

        int r;

        MC_min_numberOfCoins mc = new MC_min_numberOfCoins();
        r = mc.MC(S, M, change);
        System.out.println();
        System.out.println(r);
    }
}
