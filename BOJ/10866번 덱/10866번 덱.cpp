/**
 * BOJ 10866번 덱
 * 문제링크 : https://www.acmicpc.net/problem/10866
 * 제출링크 : https://www.acmicpc.net/source/19712568
 * 문제풀이 : https://skysign.tistory.com/218
 */

#include <iostream>
#include <deque>
using namespace std;

void solve();

void solve() {
    int N;
    cin >> N;
    int v;
    deque<int> dqe;

    for (int i = 0; i < N; ++i) {
        string cmd;
        cin >> cmd;

        if ("push_front" == cmd) {
            cin >> v;
            dqe.push_front(v);
        }
        else if ("push_back" == cmd) {
            cin >> v;
            dqe.push_back(v);
        }
        else if ("pop_front" == cmd) {
            if (dqe.size() > 0) {
                v = dqe.front();
                dqe.pop_front();
                cout << v << '\n';
            }
            else
                cout << -1 << '\n';
        }
        else if ("pop_back" == cmd) {
            if (dqe.size() > 0) {
                v = dqe.back();
                dqe.pop_back();
                cout << v << '\n';
            }
            else
                cout << -1 << '\n';
        }
        else if ("size" == cmd) {
            cout << dqe.size() << '\n';
        }
        else if ("empty" == cmd) {
            cout << (dqe.empty() == 1) << '\n';
        }
        else if ("front" == cmd) {
            if (dqe.size() > 0)
                cout << dqe.front() << '\n';
            else
                cout << -1 << '\n';
        }
        else if ("back" == cmd) {
            if (dqe.size() > 0) {
                cout << dqe.back() << '\n';
            }
            else
                cout << -1 << '\n';
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