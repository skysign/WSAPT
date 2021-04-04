#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
#include <queue>
#include <climits>

using namespace std;

void solve();

/**
 * BOJ 1963번 소수 경로
 *
 * 유튜브 문제 풀이
 * https://youtu.be/dG6cyKnyJ5o
 *
 * 문제링크 : https://www.acmicpc.net/problem/1963
 *
 * 자바소스 : https://bit.ly/3gwq2YN
 * CPP소스 : https://bit.ly/33Rkz9x
 */

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	solve();

	return 0;
}

int N;

#define PRIMES_N 10000
int primes[PRIMES_N] = { 0, };
int visited[PRIMES_N] = { 0, };

void SieveOfEratosthenes(int n);
int solve2(int nFr, int nTo);

void solve() {
	SieveOfEratosthenes(PRIMES_N);

	cin >> N;

	for (int i = 0; i < N; ++i) {
		int nFr, nTo;
		cin >> nFr;
		cin >> nTo;

		int r = solve2(nFr, nTo);

		if (r == -1) {
			cout << "Impossible";
		}
		else {
			cout << r;
		}

		cout << '\n';
	}
}

void initVisited();

int solve2(int nFr, int nTo) {
	int* tmp = new int[2]{nFr, 0};
	queue<int*> queue;
	queue.push(tmp);

	initVisited();

	while (queue.size() > 0) {
		int* pItem = queue.front();
		int crntNumber = pItem[0];
		int crntSteps = pItem[1];
		queue.pop();
		delete pItem;

		if (crntNumber == nTo) {
			while (queue.size() > 0) {
				delete queue.front();
				queue.pop();
			}
			return crntSteps;
		}
		else {
			#define LEN_DIGITS 4

			int ns[LEN_DIGITS] = {crntNumber / 1000, (crntNumber % 1000) / 100, (crntNumber % 100) / 10, (crntNumber % 10)};
			int dns[LEN_DIGITS] = {ns[0], ns[1], ns[2], ns[3]};

			// 각 자리수 마다 4번 가능한 숫자들을 변경합니다.
			for (int i = 0; i < LEN_DIGITS; ++i) {
				/**
				 * 각 자리수를 0부터 9까지 하나씩 변경해 가면서
				 * 소수인지 확인합니다.
				 * 
				 * 1000이하 소수들은 모두 소수가 아닌 것으로, 변경해 두었기 때문에,
				 * 각 자리수 1000 100 10 1 마다 루프를 돌때,
				 * j를 0부터 시작할 수 있습니다.
				 */
				for (int j = 0; j < 10; ++j) {
					if (ns[i] != j) {
						dns[i] = j;
						int number = (dns[0] * 1000) + (dns[1] * 100) + (dns[2] * 10) + dns[3];

						if (primes[number] && (crntSteps + 1 < visited[number])) {
							queue.push(new int[2]{ number, (crntSteps + 1) });
							visited[number] = crntSteps + 1;
						}

						dns[i] = ns[i];
					}
				}
			}
		}
	}

	return -1;
}

void SieveOfEratosthenes(int n) {
	primes[0] = 0;
	primes[1] = 0;
	// Both 0 and 1 are not prime numbers
	// Assume from number 2, they are all of prime numbers
	for (int i = 2; i < n; ++i)
		primes[i] = 1;

	for (int i = 2; i * i < n; ++i) {
		if (primes[i]) {
			for (int j = i * i; j < n; j += i)
				primes[j] = 0;
		}
	}

	// 1000이하 소수들은 모두 소수가 아닌 것으로 변경함
	for (int i = 0; i < 1000; ++i) {
		if (primes[i])
			primes[i] = 0;
	}
}

void initVisited() {
	for (int i = 0; i < PRIMES_N; ++i) {
		visited[i] = INT_MAX;
	}
}