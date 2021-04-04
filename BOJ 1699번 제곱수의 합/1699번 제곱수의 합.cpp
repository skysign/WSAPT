/**
 * BOJ 1699번 제곱수의 합
 * 문제링크 : https://www.acmicpc.net/problem/1699
 * 제출링크 : https://www.acmicpc.net/source/20128697
 * 문제풀이 : https://skysign.tistory.com/249
 */

#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
using namespace std;

int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);

void solve();

#define N_MAX (100000+1)
int dp[N_MAX] = {0, };

void solve() {
    int N;
    cin >> N;

    for (int i = 1; i <= N; ++i) {
        // 어떤 dp[i]가 가질 수 있는 최대 값은, N_MAX 입니다.
        // 1^2 으로 N_MAX 번 더해서, N 이라는 숫자를 만든 것입니다.
        dp[i] = N_MAX;
        for (int j = 1; j*j <= i; ++j) {
            if ((i - (j * j)) >= 0) {
                dp[i] = min(dp[i], dp[i - (j * j)] + 1);
            }
        }
    }

    cout << dp[N];
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