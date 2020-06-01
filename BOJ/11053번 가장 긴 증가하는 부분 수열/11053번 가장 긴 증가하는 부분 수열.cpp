/**
 * BOJ 11053번 가장 긴 증가하는 부분 수열
 * 문제링크 : https://www.acmicpc.net/problem/11053
 * 제출링크 : https://www.acmicpc.net/source/20127279
 * 문제풀이 : https://skysign.tistory.com/247
 */

#include <iostream>
#include <algorithm>
using namespace std;

#define N_MAX (1000+1)

int dt[N_MAX] = { 0, };
int dp[N_MAX] = { 0, };

void solve();

void solve() {
	int N;
	cin >> N;
	int r = 0;

	for (int i = 1; i <= N; ++i) {
		cin >> dt[i];
	}

	for (int i = 1; i <= N; ++i) {
		int m = 0;
		for (int j = 0; j < i; ++j) {
			if (dt[j] < dt[i]) {
				m = max(m, dp[j]+1);
			}
		}
		dp[i] = m;
		r = max(r, dp[i]);
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