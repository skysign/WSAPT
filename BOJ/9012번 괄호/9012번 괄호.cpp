/**
 * BOJ 9012번 괄호
 * 문제링크 : https://www.acmicpc.net/problem/9012
 * 제출링크 : https://www.acmicpc.net/source/19609114
 */

#include <iostream>
#include <string>
#include <stack>
using namespace std;

void solve();

void solve() {
    int N;
    cin >> N;
    cin.ignore();

    for (int i = 0; i < N; ++i) {
        string s;
        getline(cin, s);
        stack<char> st;
        string r = "YES";

        for (char c : s) {
            if ('(' == c)
                st.push(c);
            if (')' == c) {
                if ((st.size() > 0) && ('(' == st.top())) {
                    st.pop();
                }
                else {
                    r = "NO";
                    break;
                }
            }
        }

        if (st.size() > 0) {
            cout << "NO" << '\n';
            continue;
        }

        cout << r << '\n';
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}