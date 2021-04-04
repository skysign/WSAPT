/**
 * BOJ 9613번 GCD 합
 * 문제링크 : https://www.acmicpc.net/problem/9613
 * 제출링크 : https://www.acmicpc.net/source/19844531
 * 문제풀이 : https://skysign.tistory.com/232
 */

#include <iostream>
#include <algorithm>	// min() max()
using namespace std;

void solve();

int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);

void solve() {
    int T;
    cin >> T;

    for (int t = 0; t < T; ++t) {
        int N;
        cin >> N;
        int* dt = new int[N];

        for (int n = 0; n < N; ++n) {
            cin >> dt[n];
        }

        long sum = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                sum += GCD(dt[i], dt[j]);
            }
        }

        delete[] dt;

        cout << sum << '\n';
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