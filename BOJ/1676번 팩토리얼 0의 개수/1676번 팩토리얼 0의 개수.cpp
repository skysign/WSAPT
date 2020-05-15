/**
 * BOJ 1676번 팩토리얼 0의 개수
 * 문제링크 : https://www.acmicpc.net/problem/1676
 * 제출링크 : https://www.acmicpc.net/source/19806343
 * 문제풀이 : https://skysign.tistory.com/229
 */

#include <iostream>
#include <algorithm>	// min() max()
using namespace std;

void solve();
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };

bool DoesItContain5(int n);

void solve() {
    int N;
    cin >> N;
    int r = 0;

    // 5의 갯수를 세어보자
    // 방법 #1
    //for (int i = 5; i <= N; i += 5) {
    //    int v = i;
    //    while (DoesItContain5(v)) {
    //        ++r;
    //        v /= 5;
    //    }
    //}

    // 방법 #2
    for (int i = 5; i <= N; i *= 5) {
        r += (N / i);
    }

    cout << r;
}

bool DoesItContain5(int n) {
    return 0 == (n % 5);
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