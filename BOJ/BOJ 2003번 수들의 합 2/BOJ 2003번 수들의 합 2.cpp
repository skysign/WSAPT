#include <iostream>
#include <vector>
using namespace std;
/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 *
 * BOJ 2003번 수들의 합 2
 *
 * 유튜브 문제 풀이: https://youtu.be/ZzBgcF67mgg
 *
 * 문제링크: https://www.acmicpc.net/problem/2003
 *
 * CPP소스: https://bit.ly/3eSN23F
 */
/*
반례
9 5
1 2 3 4 2 5 3 1 1
답:3

아래 반례 4개 출처 : https://www.acmicpc.net/board/view/56921
3 1
1 2 1
답 : 2

5 2
5 2 1000 1 1
답 : 2

1 1
1
답 : 1

6 13
2 3 5 7 11 13
답 : 1
*/

int N, M;
vector<int> dt;

void solve() {
	cin >> N >> M;

	for (int i = 0; i < N; ++i) {
		int d;
		cin >> d;
		dt.push_back(d);
	}

	// dummy to exit while loop
	dt.push_back(0);

	int l = 0, r = 1;
	int sum = dt[l];
	int rlt = 0;

	while (r <= N) {
		if (sum == M) {
			rlt++;

			sum -= dt[l];
			l++;
		}
		else if (sum < M) {
			sum += dt[r];
			r++;
		}
		else {	// sum > M
			sum -= dt[l];
			l++;
		}
	}

	cout << rlt;
}

void solve();

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	solve();

	return 0;
}