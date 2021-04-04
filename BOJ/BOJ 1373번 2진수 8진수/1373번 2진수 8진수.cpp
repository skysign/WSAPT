/**
 * BOJ 1373번 2진수 8진수
 * 문제링크 : https://www.acmicpc.net/problem/1373
 * 제출링크 : https://www.acmicpc.net/source/19846046
 * 문제풀이 : https://skysign.tistory.com/235
 */

#include <iostream>
#include <algorithm>	// min() max()
#include <cmath>    // pow()
#include <stack>
#include <list>
#include <queue>
#include <string>

using namespace std;

void solve();

int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };

void solve() {
    string s;
    cin >> s;

    int begin = s.length() % 3;

    if (1 == begin) {
        int d0;
        d0 = s[0] - '0';

        int b8 = d0;
        cout << b8;
    }
    else if (2 == begin) {
        int d0, d1;
        d0 = s[0] - '0';
        d1 = s[0 + 1] - '0';

        int b8 = d0 * 2 + d1;
        cout << b8;
    }

    for (int i = begin; i < s.length(); i+=3) {
        int d0, d1, d2;
        d0 = s[i] - '0';
        d1 = s[i+1] - '0';
        d2 = s[i+2] - '0';

        int b8 = d0 * 4 + d1*2 + d2;
        cout << b8;
    }

    cout << '\n';
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