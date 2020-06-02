#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
using namespace std;

// begin define
// type define
#define LL long long

#define _1_000_000_000 (1000000000)
#define _1_000_000_009 (1000000009)
// end define

// begin utility functions
int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);
// end utility functions

void solve();

/**
 * BOJ 15988번 1, 2, 3 더하기 3
 * 문제링크 : https://www.acmicpc.net/problem/15988
 * 제출링크 : https://www.acmicpc.net/source/20161147
 * 문제풀이 : https://skysign.tistory.com/251
 */
// 문제 푸는 코드 시작
#define N_MAX (1000000+1)
#define MOD _1_000_000_009

int dp[N_MAX];
int dt[N_MAX];

void solve() {
    int N, maxN = 0;
    cin >> N;

    for (int i = 0; i < N; ++i) {
        cin >> dt[i];
        maxN = max(maxN, dt[i]);
    }

    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;

    for (int n = 4; n <= maxN; ++n) {
        dp[n] = (dp[n] + dp[n - 3]) % MOD;
        dp[n] = (dp[n] + dp[n - 2]) % MOD;
        dp[n] = (dp[n] + dp[n - 1]) % MOD;
    }

    for (int i = 0; i<N; ++i) {
        cout << dp[dt[i]] << '\n';
    }
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