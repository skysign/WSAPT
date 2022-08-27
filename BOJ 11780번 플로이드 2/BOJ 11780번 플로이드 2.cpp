#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
using namespace std;

void solve();

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();

    return 0;
}

int N;
int M;

#define MAX_N (100 * 1000)

vector<vector<int>> dt;

void solve() {
    cin >> N;
    cin >> M;

    for (int i = 0; i < N; ++i) {
        dt.push_back(new vector<int>);
        for (int j = 0; j < N; ++j) {
            dt[i][j] = UINT32_MAX;
        }
    }

    for (int i = 0; i < M; ++i) {
        int fr, to, v;
        cin >> fr >> to >> v;
        dt[fr][to] = min(v, dt[fr][to]);
    }
}