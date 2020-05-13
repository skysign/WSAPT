/**
 * BOJ 17299번 오등큰수
 * 문제링크 : https://www.acmicpc.net/problem/17299
 * 제출링크 :
 * 문제풀이 : https://skysign.tistory.com/222
 */

#include <iostream>
#include <vector>
#include <stack>
using namespace std;

void solve();

#define MAX 1000000

int freq[MAX] = { 0, };
int rlt[MAX] = { 0, };

void solve() {
    int N;
    cin >> N;

    vector<int> dt;

    for (int i = 0; i < N; ++i) {
        int v;
        cin >> v;
        dt.push_back(v);
    }

    for (int i = 0; i < N; ++i) {
        freq[dt[i]]++;
    }

    stack<int> stk;
    stack<int> stkIdx;

    stk.push(dt[0]);
    stkIdx.push(0);

    for (int i = 1; i < N; ++i) {
        while (!stk.empty() && freq[stk.top()] < freq[dt[i]]) {
            rlt[stkIdx.top()] = dt[i];
            stk.pop();
            stkIdx.pop();
        }

        stk.push(dt[i]);
        stkIdx.push(i);
    }

    // 마지막 아이템은 오른쪽에 다른 숫자가 있을 수 없음
    rlt[N - 1] = -1;

    while (!stkIdx.empty()) {
        rlt[stkIdx.top()] = -1;
        stkIdx.pop();
    }

    for (int i = 0; i < N; ++i) {
        cout << rlt[i] << ' ';
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}