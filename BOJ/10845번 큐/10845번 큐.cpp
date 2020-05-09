/**
 * BOJ 10845번 큐
 * 문제링크 : https://www.acmicpc.net/problem/10845
 * 제출링크 : https://www.acmicpc.net/source/19695483
 */

#include <iostream>
#include <queue>
using namespace std;

void solve();

void solve() {
    int N;
    cin >> N;
    queue<int> que;

    for (int i = 0; i < N; ++i) {
        string cmd;
        cin >> cmd;
        int v;

        if ("push" == cmd) {
            cin >> v;
            que.push(v);
        }
        else if ("pop" == cmd) {
            if (que.size() > 0) {
                const int& rv = que.front();
                cout << rv;
                cout << '\n';
                que.pop();
            }
            else {
                cout << -1;
                cout << '\n';
            }
        }
        else if ("size" == cmd) {
            cout << que.size();
            cout << '\n';
        }
        else if ("empty" == cmd) {
            cout << que.empty();
            cout << '\n';
        }
        else if ("front" == cmd) {
            if (que.size() > 0) {
                const int& rv = que.front();
                cout << rv;
                cout << '\n';
            }
            else {
                cout << -1;
                cout << '\n';
            }
        }
        else if ("back" == cmd) {
            if (que.size() > 0) {
                const int& rv = que.back();
                cout << rv;
                cout << '\n';
            }
            else {
                cout << -1;
                cout << '\n';
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