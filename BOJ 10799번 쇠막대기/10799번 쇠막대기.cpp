/**
 * BOJ 10799번 쇠막대기
 * 문제링크 : https://www.acmicpc.net/problem/10799
 * 제출링크 : https://www.acmicpc.net/source/19727524
 * 문제풀이 : https://skysign.tistory.com/220
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
    int r = 0;
    char prevC = 0;

    for (char c: str) {
        if ('(' == c) {
            stk.push(c);
        }
        else if (')' == c) {
            if (prevC == '(') {
                stk.pop();
                // 레이저 발사
                r += stk.size();
            }
            else {
                r += 1;
                stk.pop();
            }
        }

        prevC = c;
    }

    cout << r;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}