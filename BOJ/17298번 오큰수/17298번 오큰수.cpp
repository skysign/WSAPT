/**
 * BOJ 17298번 오큰수
 * 문제링크 : https://www.acmicpc.net/problem/17298
 * 제출링크 : https://www.acmicpc.net/source/19767377
 * 문제풀이 : https://skysign.tistory.com/221
 */

#include <iostream>
#include <stack>
using namespace std;

void solve();

#define MAX 1000000

int dt[MAX];    // input data
int rlt[MAX];   // dt[i]의 오큰수는 rlt[i]에 저장함

void solve() {
    int N;
    cin >> N;
    
    stack<int> stkNotFound; // 오큰수를 아직 찾지 못한 수들
    stack<int> stkNotFoundIdx; // 오큰수를 아직 차지 못한 수들의 dt배열에 어디에 있는지 index

    for (int i = 0; i < N; ++i) {
        cin >> dt[i];
    }

    for (int i = 0; i < N; ++i) {
        int idx = i;

        while ((stkNotFound.size() > 0) && (stkNotFound.top() < dt[idx])) {
            rlt[stkNotFoundIdx.top()] = dt[idx];
            stkNotFound.pop();
            stkNotFoundIdx.pop();
        }

        stkNotFound.push(dt[idx]);
        stkNotFoundIdx.push(idx);
    }

    // dt[0...N]까지 확인해 봤는대도, 오큰수를 못 찾은 수들의 dt배열에 있는 위치
    while (stkNotFoundIdx.size() > 0) {
        rlt[stkNotFoundIdx.top()] = -1;
        stkNotFoundIdx.pop();
    }

    // 마지막수의 오큰수는 항상 -1
    rlt[N - 1] = -1;

    for(int i=0; i<N; ++i) {
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