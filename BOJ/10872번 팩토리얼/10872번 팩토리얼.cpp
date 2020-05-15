/**
 * BOJ 10872번 팩토리얼
 * 문제링크 : https://www.acmicpc.net/problem/10872
 * 제출링크 : https://www.acmicpc.net/source/19805980
 * 문제풀이 : https://skysign.tistory.com/228
 */

#include <iostream>
#include <algorithm>	// min() max()
using namespace std;

void solve();
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };

void solve() {
    int N;
    cin >> N;

    int r = 1;

    for (int i = 1; i <= N; ++i) {
        r *= i;
    }

    cout << r;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
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