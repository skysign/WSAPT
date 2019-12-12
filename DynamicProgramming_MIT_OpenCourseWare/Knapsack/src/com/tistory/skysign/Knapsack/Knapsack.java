package com.tistory.skysign.Knapsack;

import java.util.Scanner;

public class Knapsack {
    // Knapsack 문제 링크
    // https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem/0
    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();

	    for(int i=0; i<T; ++i) {
            int N = sc.nextInt();
            int W = sc.nextInt();
            int wt[] = new int[N];
            int v[] = new int[N];

            for(int iwt=0; iwt<N; ++iwt) {
                wt[iwt] = sc.nextInt();
            }

            for(int iv=0; iv<N; ++iv) {
                v[iv] = sc.nextInt();
            }

            int r = knapsack(N, W, wt, v);
            System.out.println(r);
            r = knapsack2(N, W, wt, v);
            System.out.println(r);
        }
    }

    // knapsack 을 recussion으로 풀기
    // 한번 계산했던 것을 다시 계산 하는 문제가 있습니다.
    public static int knapsack(int N, int W, int[] wt, int[] v) {
        int r = 0;

        if(N <= 0)
            return 0;

        if(W <= 0)
            return 0;

        // wt[N-1]을 넣을 수 있으면?
        if(W >= wt[N-1]) {
            int r1=0, r2=0;
            // (DP문제를 푸는 방법이죠)
            // 넣을 수 있지만, 이짐을 넣지 않고, value의 총합
            // 이 짐을 넣고, value의 총합
            r1 = knapsack(N-1, W, wt, v);
            r2 = knapsack(N-1, W-wt[N-1], wt, v) + v[N-1];
            // 둘중에 큰 값을 리턴
            r = Math.max(r1, r2);
        }
        else {
            // 현재 무게 W가 wt[N-1] 이 짐을 넣을 수 없으면,
            // 이 짐을 넣지 않고, value의 총합
            r = knapsack(N-1, W, wt, v);
        }

        return r;
    }

    public static int knapsack2(int N, int W, int[] wt, int[] v) {
        int K[][] = new int[N+1][W+1];

        for(int i=0; i<=N; ++i) {
            for(int w=0; w<=W; ++w) {
                if(i==0 || w==0)
                    K[i][w] = 0;
                else if(w >= wt[i-1])
                    K[i][w] = Math.max(v[i-1] + K[i-1][w-wt[i-1]], K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
        }

        d0(K);

        return K[N][W];
    }

    public static void d0(int[][] dp) {
        System.out.print("    ");

        for(int j=0; j<dp[0].length; ++j){
            System.out.printf("%3d ", j);
        }
        System.out.println();

        for(int i=0; i<dp.length; ++i){
            System.out.printf("%3d|", i);
            for(int j=0; j<dp[0].length; ++j){
                System.out.printf("%3d ", dp[i][j]);
            }
            System.out.println();
        }
    }
}
