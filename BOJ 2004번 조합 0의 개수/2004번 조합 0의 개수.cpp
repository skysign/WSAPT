/**
 * BOJ 2004번 조합 0의 개수
 * 문제링크 : https://www.acmicpc.net/problem/2004
 * 제출링크 : https://www.acmicpc.net/source/19806657
 * 문제풀이 : https://skysign.tistory.com/231
 */

#include <iostream>
#include <algorithm>	// min() max()
using namespace std;

void solve();
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };

void solve() {
    long long n, m;
    cin >> n >> m;

    long long two = 0, five = 0;

    // n! / (n-m)! * m!
    
    for (long long i = 2; i <= n; i *= 2)
        two += (n / i);

    for (long long i = 2; i <= (n - m); i *= 2)
        two -= ((n - m) / i);

    for (long long i = 2; i <= m; i *= 2)
        two -= (m / i);


    for (long long i = 5; i <= n; i *= 5)
        five += (n / i);

    for (long long i = 5; i <= (n - m); i *= 5)
        five -= ((n - m) / i);

    for (long long i = 5; i <= m; i *= 5)
        five -= (m / i);

    cout << min(two, five);
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();

    return 0;
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