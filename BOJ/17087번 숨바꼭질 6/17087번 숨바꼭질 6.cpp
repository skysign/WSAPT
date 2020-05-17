/**
 * BOJ 17087번 숨바꼭질 6
 * 문제링크 : https://www.acmicpc.net/problem/17087
 * 제출링크 : https://www.acmicpc.net/source/19845500
 * 문제풀이 : https://skysign.tistory.com/234
 */

#include <iostream>
#include <algorithm>	// min() max()
using namespace std;

void solve();
int GCD(int a, int b);
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };

void solve() {
    int N, S;
    cin >> N >> S;

    for (int i = 0; i < N; ++i) {
        int v;
        cin >> v;
        dt[i] = abs(S - v);
    }

    for (int i = 1; i < N; ++i) {
        dt[0] = GCD(dt[0], dt[i]);
    }

    cout << dt[0];
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();

    return 0;
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