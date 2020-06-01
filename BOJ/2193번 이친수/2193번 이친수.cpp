/**
 * BOJ 2193번 이친수
 * 문제링크 : https://www.acmicpc.net/problem/2193
 * 제출링크 : https://www.acmicpc.net/source/20121295
 * 유튜브 문제풀이 : https://youtu.be/Lc2mtrf8qJY
 * 문제풀이 : https://skysign.tistory.com/246
 */

#include <iostream>
using namespace std;

void solve();

#define LL long long
#define N_MAX (90+1)

void solve() {
    int N;
    cin >> N;

    LL dt[N_MAX][2] = {{0,0}, // 자릿수 0 실제로는 존재하지 않는 수
                       {0,1}, // 자릿수 1, 1개만 이친수
                       {1,0}, // 자릿수 2, 10 1개 이친수, 11 이친수 아님,
                       {1,1}, // 자릿수 3, 100 0으로 끝나는 이친수 1개, 101, 1로 끝나는 이친수 1개
                       };

    for (int n = 4; n <= N; ++n) {
        dt[n][0] += dt[n - 1][0] + dt[n - 1][1];
        dt[n][1] += dt[n - 1][0];
    }

    cout << (dt[N][0] + dt[N][1]);
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}