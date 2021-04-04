/**
 * BOJ 1874번 스택 수열
 * 문제링크 : https://www.acmicpc.net/problem/1874
 * 제출링크 : https://www.acmicpc.net/source/19610344
 */

#include <iostream>
#include <stack>
using namespace std;

void solve();

void solve() {
    int N;
    cin >> N;
    cin.ignore();

    stack<int> stk;
    int m = 0;
    string r;

    for (int i = 0; i < N; ++i) {
        int x;
        cin >> x;
        cin.ignore();

        if (x > m) {
            while (x > m) {
                stk.push(++m);
                r += "+";
            }
            stk.pop();
            r += '-';
        }
        else {
            if (!stk.empty()) {
                if (x == stk.top()) {
                    stk.pop();
                    r += '-';
                }
                else {
                    cout << "NO\n";
                    return;
                }
            }
        }
    }

    for (auto v : r)
        cout << v << '\n';
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}