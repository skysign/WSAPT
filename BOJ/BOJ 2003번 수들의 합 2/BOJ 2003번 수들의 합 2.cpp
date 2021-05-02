#include <iostream>
#include <vector>
using namespace std;

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