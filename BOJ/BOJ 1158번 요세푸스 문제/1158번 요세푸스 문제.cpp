/**
 * BOJ 1158번 요세푸스 문제
 * 문제링크 : https://www.acmicpc.net/problem/1158
 * 제출링크 : https://www.acmicpc.net/source/19712098
 * 문제풀이 : https://skysign.tistory.com/217
 */

#include <iostream>
#include <list>
#include <string>
using namespace std;

void solve();

void solve() {
    int N, K;
    cin >> N >> K;
    list<int> lst;

    for (int i = 1; i <= N; ++i) {
        lst.push_back(i);
    }

    cout << "<";

    while (lst.size() > 1) {
        for (int i = 0; i < K-1; ++i) {
            lst.push_back(lst.front());
            lst.pop_front();
        }

        cout << lst.front() << ", ";
        lst.pop_front();
    }

    cout << lst.front();
    cout << ">";
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}