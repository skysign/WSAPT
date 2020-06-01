/**
 * BOJ 14002번 가장 긴 증가하는 부분 수열 4
 * 문제링크 : https://www.acmicpc.net/problem/14002
 * 제출링크 : https://www.acmicpc.net/source/20127693
 * 문제풀이 : https://skysign.tistory.com/248
 */

#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
using namespace std;

int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);

#define N_MAX (1000+1)

// 문제풀이 시작
void solve();

int dt[N_MAX] = { 0, };
int dp[N_MAX] = { 0, };
int from[N_MAX] = { 0, };

void solve() {
    int N;
    cin >> N;
    int r = 0;

    for (int i = 1; i <= N; ++i) {
        cin >> dt[i];
    }

    int maxIdx = 0;

    for (int i = 1; i <= N; ++i) {
        int m = 0;
        int fromIdx = 0;
        for (int j = 0; j < i; ++j) {
            if (dt[j] < dt[i]) {
                m = max(m, dp[j] + 1);
                if(m == dp[j] + 1)
                    fromIdx = j;
            }
        }
        dp[i] = m;
        r = max(r, dp[i]);
        if (r == dp[i])
            maxIdx = i;

        from[i] = fromIdx;
    }

    cout << r << '\n';
    stack<int> stk;

    // idxBT 는 index back-tracking의 약자로
    // LIS의 마지막 인덱스 부터, 처음 인덱스까지 찾는대 사용하는 인덱스
    int idxBT = maxIdx;

    while (idxBT != 0) {
        stk.push(dt[idxBT]);
        idxBT = from[idxBT];
    }

    while (!stk.empty()) {
        cout << stk.top() << ' ';
        stk.pop();
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