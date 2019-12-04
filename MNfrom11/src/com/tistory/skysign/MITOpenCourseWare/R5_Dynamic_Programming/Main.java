package com.tistory.skysign.MITOpenCourseWare.R5_Dynamic_Programming;

public class Main {
//    https://www.youtube.com/watch?v=krZI60lKPek&feature=share
//    동영상에서 4분 후반에 설명하는 첫 DP 문제를 실제 구현하면 아래와 같습니다.

//  [i][j] 로 오는 unique한 길의 수는 좌측/상단의 값을 더한 숫자 입니다.
//  [i-1][j] + [i][j-1] -> [i][j]
    public static int uniqueNumberOfPathes(int i, int j, int[][] a) {
        return a[i-1][j] + a[i][j-1];
    }

    public static void printPathes(int[][] a) {
        for(int i=0; i<a.length; ++i) {
            for(int j=0; j<a[0].length; ++j) {
                System.out.printf("%3d ", a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
//    동영상에서는 좌측 하단에서 우측 상단으로 우측과, 위로 가는 방향만 사용해서
//    1,1 에서 M,N까지 가는 unique한 길의 숫자를 DP를 통해서 계산합니다.
//    구현을 좀 쉽게 하기 위해서, 2개의 조건을 변경합니다.
//    - 동영상의 좌표계를 90도 시계방향으로 회전합니다.
//    - 1,1 부터 시작하지 않고, 0,0 에서 시작하겠습니다.
        int M = 5, N = 5;
        int[][] a = new int[M][N];

//      마찬가지로 M 이 0 이면, unique한 path는 1개 뿐입니다.
        for(int j=1; j<N; ++j)
            a[0][j] = 1;

//      N 이 0 이면, unique한 path는 1개 뿐입니다.
        for(int i=1; i<M; ++i)
            a[i][0] = 1;

        printPathes(a);

//      모든 점으로 이동 가능한 unique한 경우의 수를 계산하면 아래와 같습니다.
        for(int i=1; i<M; ++i) {
            for(int j=1; j<N; ++j) {
                a[i][j] = uniqueNumberOfPathes(i, j, a);
            }
        }

        printPathes(a);
    }
}

