package com.tistory.skysign.ALGOSPOT.AMUSEMENTPARK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// https://algospot.com/judge/problem/read/AMUSEMENTPARK
// algospot의 AMUSEMENTPARK 문제 풀이 입니다.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int posMelonStart = sc.nextInt();

        int[][] map = new int[M][N];

        for(int i=0; i<M; ++i) {
            for(int j=0; j<N; ++j) {
                map[i][j] = sc.nextInt();
            }
        }
//      여기까지가 입력 받기 끝
        ArrayList<Integer> r = new ArrayList<Integer>();

        int posLast = map[M-1][N-1];

        for(int posMelon=posMelonStart, posOrange = 1; posMelon<=posLast; ++posMelon, ++posOrange) {
            int iO = 0, jO = 0;
            int iM = 0, jM = 0;
            boolean bOrangeFound = false;
            boolean bMelonFound = false;

            for(int i=0; i<M; ++i) {
                for(int j=0; j<N; ++j) {
                    if(posOrange == map[i][j]) {
                        iO = i;
                        jO = j;
                        bOrangeFound = true;
                    }
                    if(posMelon == map[i][j]) {
                        iM = i;
                        jM = j;
                        bMelonFound = true;
                    }
                    if(bOrangeFound && bMelonFound) {
                        break;
                    }
                }
            }

            int di = Math.abs(iM - iO);
            int dj = Math.abs(jM - jO);

//            예외 경우들이 있기는 하지만, 아래 3가지 경우에는 안보인다고 가정함
//            1. iO == iM 같은 column 에 있으면 않보임
//            2. JO == jM 같은 row에 있으면 않보임
//            3. di == dj (iO, jO) -> (iM, jM)을 잊는 직선의 기울기가 1 임으로 중간에 막혀 있다고 가정
            if((iO == iM) || (jO == jM) || (di == dj)){
                // 하지만, orange와 melon이 인접해 있으면 볼 수 있음
                if((iM-1 <= iO) && (iO <= iM+1)) {
                    if((jM-1 <= jO) && (jO <= jM+1)) {
                        r.add(posOrange);
                    }
                }
                else {
                    if (di == dj) {
                        boolean All0 = true;
//                    iO,jO부터 -> iM,jM 으로 한칸씩 이동하면서, 중간이 모두 0으로 채워저 있는지 확인하는 과정
//                    + X 짜로 8가지 방향이 있는 것 같지만, 실제로는 4방향
//                    중간이 0으로 채워저 있느지만 확인하면 되기 때문에,
//                    min(iO,iM)과 max(iO,iM)까지 중간이 모두 0인지 확인하면 됨
//                    기울기가 1이기 때문에, x와 y의 증가량 모두 1씩임
//                    iS는 0이 있는지 확인할 시작점
//                    iE는 0이 있는지 확인할 끝점
                        int iS = Math.min(iO, iM) +1;
                        int iE = Math.max(iO, iM) -1;
                        int jS = Math.min(jO, jM) +1;
                        int jE = Math.max(jO, jM) -1;
                        for( ; iS<=iE; ++iS, ++jS) {
                            if(0 != map[iS][jS]){
                                All0 = false;
                                break;
                            }
                        }

                        if(All0) {
                            r.add(posOrange);
                        }
                    }

                    if (iO == iM) {
                        boolean All0 = true;
                        int jS = Math.min(jO, jM) +1;
                        int jE = Math.max(jO, jM) -1;
                        for( ; jS<=jE; ++jS) {
                            if(0 != map[iO][jS]){
                                All0 = false;
                                break;
                            }
                        }

                        if(All0) {
                            r.add(posOrange);
                        }
                    }

                    if (jO == jM) {
                        boolean All0 = true;
                        int iS = Math.min(iO, iM) +1;
                        int iE = Math.max(iO, iM) -1;
                        for( ; iS<=iE; ++iS) {
                            if(0 != map[iS][jO]){
                                All0 = false;
                                break;
                            }
                        }

                        if(All0) {
                            r.add(posOrange);
                        }
                    }
                }
            }
            else {
                r.add(posOrange);
            }
        }

        System.out.println(r.size());
        Collections.sort(r);
        for(int a: r)
            System.out.println(a);
    }
}
