/**
 * BOJ 1929번 소수 구하기
 * 문제링크 : https://www.acmicpc.net/problem/1929
 * 제출링크 : https://www.acmicpc.net/source/19805033
 * 문제풀이 : https://skysign.tistory.com/226
 */

#include <iostream>
using namespace std;

void solve();
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };

void solve() {
    int M, N;
    cin >> M >> N;

    // dt 에 1부터 N까지 숫자를 채우고
    for (int i = 1; i <= N; ++i) {
        dt[i] = i;
    }

    for (int i = 2; i <= N; ++i) {
        if (dt[i] != 0) {
            if (IsPrimeNnumber(dt[i])) {
                int delta = i;
                int idx = i + delta;
                while (idx <= N) {
                    dt[idx] = 0;
                    idx += delta;
                }

                if (M <= i && i <= N) {
                    cout << dt[i] << '\n';
                }
            }
        }
    }
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