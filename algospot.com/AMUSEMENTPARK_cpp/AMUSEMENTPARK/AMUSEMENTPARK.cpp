/**
 * AMUSEMENTPARK 놀이 공원 / algospot.com
 * 문제링크 : https://algospot.com/judge/problem/read/AMUSEMENTPARK
 * 제출링크 : https://algospot.com/judge/submission/detail/653294
 */
#include <iostream>
#include <vector>

#include <algorithm>

using namespace std;
vector<vector<int>> mMap;

bool AreTheyAdjacent(int Oi, int Oj, int Mi, int Mj);
bool AreTheyBlocked(int Oi, int Oj, int Mi, int Mj);
bool AreTheyPrimeNumber(int a, int b);
int mygcd(int a, int b);

int main()
{
    int M, N, MelonPos;
    cin >> M >> N >> MelonPos;

    vector<int> mR = vector<int>(0);
    const int POS_LEN = M*N +1;

    int *posIs = new int[POS_LEN];
    int *posJs = new int[POS_LEN];
    int cntPos = 0;
    mMap = vector<vector<int>>(M, vector<int>(N, 0));

    for (int i=0; i<M; ++i) {
        for (int j = 0; j < N; ++j) {
            int v;
            cin >> v;
            mMap[i][j] = v;
            if (mMap[i][j] > 0) {
                posIs[mMap[i][j]] = i;
                posJs[mMap[i][j]] = j;
                cntPos++;
            }
        }
    }

    for (int mPos = MelonPos, oPos = 1; mPos <= cntPos; ++mPos, ++oPos) {
        if (true == AreTheyAdjacent(posIs[oPos], posJs[oPos], posIs[mPos], posJs[mPos])) {
            mR.push_back(oPos);
        }
        else if (false == AreTheyBlocked(posIs[oPos], posJs[oPos], posIs[mPos], posJs[mPos])) {
            mR.push_back(oPos);
        }
    }

    cout << mR.size() << endl;
    for (auto it = mR.begin(); it != mR.end(); it++) { cout << *it << endl; }

    delete posIs;
    delete posJs;
}

bool AreTheyAdjacent(int Oi, int Oj, int Mi, int Mj) {
    int di[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    int dj[] = {0, 0, -1, 1, -1, 1, 1, -1};

    for (int k = 0; k < 8; ++k) {
        if ((Oi == (Mi + di[k])) && (Oj == (Mj + dj[k])))
            return true;
    }

    return false;
}

bool AreTheyBlocked(int Oi, int Oj, int Mi, int Mj) {
    // i 가 같음
    if (Oi == Mi) {
        int fixedI = Oi;
        int begJ = min(Oj, Mj) + 1;
        int endJ = max(Oj, Mj);
        for (int j = begJ; j < endJ; ++j) {
            if (mMap[fixedI][j] != 0) {
                return true;
            }
        }
    }
    //        if(oPos.mI == mPos.mI) {
    //            int fixedI = oPos.mI;
    //            int begJ = Math.min(oPos.mJ, mPos.mJ) +1;
    //            int endJ = Math.max(oPos.mJ, mPos.mJ);
    //            for(int j = begJ; j < endJ; ++j) {
    //                if(mMap[fixedI][j] != 0) {
    //                    return true;
    //                }
    //            }
    //        }

            // j 가 같음
    if (Oj == Mj) {
        int fixedJ = Oj;
        int begI = min(Oi, Mi) + 1;
        int endI = max(Oi, Mi);
        for (int i = begI; i < endI; ++i) {
            if (mMap[i][fixedJ] != 0) {
                return true;
            }
        }
    }
    //        if(oPos.mJ == mPos.mJ) {
    //            int fixedJ = oPos.mJ;
    //            int begI = Math.min(oPos.mI, mPos.mI) +1;
    //            int endI = Math.max(oPos.mI, mPos.mI);
    //            for(int i = begI; i < endI; ++i) {
    //                if(mMap[i][fixedJ] != 0) {
    //                    return true;
    //                }
    //            }
    //        }

    int di = abs(Oi - Mi);
    int dj = abs(Oj - Mj);
    //        int di = Math.abs(oPos.mI - mPos.mI);
    //        int dj = Math.abs(oPos.mJ - mPos.mJ);

            // i와 j 가 둘다 소수이면
    if (AreTheyPrimeNumber(di, dj))
        return false;

    // Orange와 Melon이 대각선 방향에 있음
    // Orange에서 Melon방향으로 다가갈 때,
    // 중간이 막혀 있으면 blocked(true)

    int gcd = mygcd(di, dj);

    di = (Mi - Oi) / gcd;
    dj = (Mj - Oj) / gcd;

    for (int step = 1, i = Oi, j = Oj; step < gcd; ++step) {
        i += di;
        j += dj;
        if (mMap[i][j] != 0)
            return true;
    }

    //        di = (mPos.mI - oPos.mI) / gcd;
    //        dj = (mPos.mJ - oPos.mJ) / gcd;
    //
    //        for(int step=1, i = oPos.mI, j = oPos.mJ; step<gcd; ++step) {
    //            i += di;
    //            j += dj;
    //            if(mMap[i][j] != 0)
    //                return true;
    //        }

    return false;
}

bool AreTheyPrimeNumber(int a, int b) {
    int min_v = min(a, b);
    for (int i = 2; i <= min_v; ++i) {
        if ((a % i == 0) && (b % i == 0)) {
            return false;
        }
    }

    return true;
}

int mygcd(int a, int b) {
    if (b == 0)
        return a;

    return mygcd(b, a % b);
}