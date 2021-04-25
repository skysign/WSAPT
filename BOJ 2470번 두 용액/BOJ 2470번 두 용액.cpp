#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

// BOJ 2470번 두 용액

void solve();
vector<int> dt;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}

void solve() {
	int N;
	cin >> N;

	for (int i = 0; i < N; ++i) {
		int d;
		cin >> d;
		dt.push_back(d);
	}

	sort(dt.begin(), dt.end());

	int r = 0;

	int idxL = 0;
	int idxR = N -1;
	int rLeft, rRight;
	int sum = INT32_MAX;

	while (idxL < idxR) {
		int sumT = dt[idxL] + dt[idxR];

		if (abs(sum) > abs(sumT)) {
			sum = sumT;
			rLeft = dt[idxL];
			rRight = dt[idxR];
		}

		if (sumT <= 0)
			idxL++;
		else
			idxR--;
	}

	cout << rLeft << " " << rRight;
}