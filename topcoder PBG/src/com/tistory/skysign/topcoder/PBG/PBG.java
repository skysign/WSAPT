package com.tistory.skysign.topcoder.PBG;

public class PBG {
    int mZ1;
    int mZ2;
    int mPos;

    public void LimakfightP(int P) {
        if (P > 1) {
            // winning
            mZ1 += mPos;
            mZ2++;

            --mPos;
            LimakfightP(--P);
        }
    }

    public int findEV(int P, int B, int G) {
        mZ1 = 0;
        mZ2 = 0;
        mPos = 0;

        int NN = P + B + G;
        int N = B + G;
        if (P > 0)
            ++N;

        for(int i=0; i<N; ++i) {
            mPos = NN - B;

            if(B > 0) {
                --B;
            }

            if(P > 0) {
                LimakfightP(P);
                --P;
            }

            if(G > 0) {
                --G;
            }

            mZ1 += mPos;
            mZ2++;
        }

        System.out.printf("%d/%d\n", mZ1, mZ2);

        return mZ1 * (int)Math.pow(mZ2, -1) % ((int)Math.pow(10, 9) + 7);
    }

    public static void main(String[] args) {
	// write your code here
        PBG pbg = new PBG();
        int r;

        r = pbg.findEV(5, 0, 0);
        System.out.println(r);
    }
}
