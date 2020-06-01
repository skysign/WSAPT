/**
 * BOJ 2225번 합분해
 * 문제링크 : https://www.acmicpc.net/problem/2225
 * 제출링크 : https://www.acmicpc.net/source/20136342
 * 문제풀이 : https://skysign.tistory.com/250
 */

#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
using namespace std;

// begin define
// type define
#define LL long long

#define _1_000_000_000 (1000000000)
// end define

// begin utility functions
int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);
// end utility functions

void solve();

// 문제 푸는 코드 시작
#define N_MAX (200+1)
#define K_MAX (200+1)
#define MOD (_1_000_000_000)

LL dp[N_MAX][K_MAX] = { {1, }, };

void solve() {
    int N, K;
    cin >> N >> K;

    for (int n = 0; n <= N; ++n) {
        for (int k = 1; k <= K; ++k) {
            for (int l = 0; l <= n; ++l) {
                dp[n][k] += dp[n - l][k-1];
                dp[n][k] %= MOD;
            }
        }
    }

    cout << dp[N][K];
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();

    return 0;
}

// LCM
int LCM(int a, int b) {
    return a * (b / GCD(a, b));
}

// GCD
int GCD(int a, int b) {
    if (0 == b) {
        return a;
    }

    return GCD(b, a % b);
}

// prime number
bool IsPrimeNnumber(int n) {
    if (n < 2)
        return false;

    for (int i = 2; i * i <= n; i++)
        if (n % i == 0)
            return false;

    return true;
}