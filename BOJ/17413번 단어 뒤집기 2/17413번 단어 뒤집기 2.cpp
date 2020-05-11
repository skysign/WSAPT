/**
 * BOJ 17413번 단어 뒤집기 2
 * 문제링크 : https://www.acmicpc.net/problem/17413
 * 제출링크 : https://www.acmicpc.net/source/19726366
 * 문제풀이 : https://skysign.tistory.com/219
 */

#include <iostream>
#include <string>
#include <stack>
using namespace std;

void solve();

void solve() {
    string str;
    getline(cin, str);

    stack<char> stk;

    bool bSkipTag = false;

    for (char c: str) {
        if ('<' == c) {
            while (stk.size() > 0) {
                cout << stk.top();
                stk.pop();
            }
            bSkipTag = true;
        }

        if (bSkipTag) {
            cout << c;
        }
        else {
            if (' ' == c) {
                while (stk.size() > 0) {
                    cout << stk.top();
                    stk.pop();
                }
                cout << c;
            }
            else {
                stk.push(c);
            }
        }

        if ('>' == c) {
            bSkipTag = false;
        }
    }

    while (stk.size() > 0) {
        cout << stk.top();
        stk.pop();
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}