/**
 * 스터디에서 알고리즘 문제 함께 풀어보실 분들
 * 여기로 → https://wsapt.github.io/public/
 * 
 * BOJ 10828번 스택
 * 
 * 문제링크 : https://www.acmicpc.net/problem/10828
 * 
 * CPP소스 : 
 */

#include <iostream>
#include <stack>
using namespace std;

void solve();

void solve() {
    int N;
    cin >> N;

    stack<int> st;

    for (int i = 0; i < N; ++i) {
        string cmd;
        cin >> cmd;

        if ("push" == cmd) {
            int v;
            cin >> v;
            st.push(v);
        }

        if ("pop" == cmd) {
            if (st.size() > 0) {
                int v = st.top();
                cout << v << '\n';
                st.pop();
            }
            else {
                cout << -1 << '\n';
            }
        }

        if ("size" == cmd) {
            cout << st.size() << '\n';
        }

        if ("empty" == cmd) {
            cout << (st.size() == 0) << '\n';
        }

        if ("top" == cmd) {
            if (st.size() > 0) {
                cout << st.top() << '\n';
            }
            else {
                cout << -1 << '\n';
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