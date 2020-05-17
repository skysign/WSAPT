/**
 * BOJ 1212번 8진수 2진수
 * 문제링크 : https://www.acmicpc.net/problem/1212
 * 제출링크 : https://www.acmicpc.net/source/19852354
 * 문제풀이 : https://skysign.tistory.com/236
 */

#include <iostream>
#include <algorithm>	// min() max()
#include <stack>
#include <list>
#include <queue>
using namespace std;

void solve();

int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };

void ON2BN(int v);

void solve() {
    string s;
    cin >> s;

    if (s == "0") {
        cout << s;
        return;
    }

    for (int i = 0; i < s.length(); ++i) {
        int v = s[i] - '0';

        if (i > 0) {
            if (v < 4)
                cout << '0';
            if (v < 2)
                cout << '0';
            if (v == 0)
                cout << '0';
        }

        ON2BN(v);
    }
}

void ON2BN(int v) {
    if (v != 0) {
        ON2BN(v / 2);
        cout << (v % 2);
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