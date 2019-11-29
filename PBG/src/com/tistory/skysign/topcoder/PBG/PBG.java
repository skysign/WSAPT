package com.tistory.skysign.topcoder.PBG;

public class PBG {
    int mrV;
    int mrC;
    int mP;
    int mB;
    int mG;

    public void LimakfightP(int pos, int P) {
        // lose
        mrV += pos;
        mrC++;

        if (P > 1) {
            // winning
            LimakfightP(--pos, --P);
        }
//        else {

//        }
    }

    public int findEV(int P, int B, int G) {
        mrV = 0;
        mrC = 0;

        mP = P;
        mB = B;
        mG = G;

        int N = mP-1 + mB + mG;

        for(int i=0; i<N; ++i) {
            int pos = N-B;

            if(B > 0) {
                --B;
            }

            if(P > 1) {
                LimakfightP(pos, P);
                --P;
            }

            if(G > 0) {
                --G;
            }

            mrV += pos;
            mrC++;
        }

        return mrV * (int)Math.pow(mrC, -1) % ((int)Math.pow(10, 9) + 7);
    }

    public static void main(String[] args) {
	// write your code here
        
    }
}
