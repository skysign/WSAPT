/**
 * BOJ 6588번 골드바흐의 추측
 * 문제링크 : https://www.acmicpc.net/problem/6588
 * 제출링크 : https://www.acmicpc.net/source/19805716
 * 문제풀이 : https://skysign.tistory.com/227
 */

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

void solve();
bool IsPrimeNnumber(int n);

#define MAX_I (1000000)
int dt[MAX_I] = { 0, };

void findPrime();
vector<int> vc;

void solve() {
    for (int i = 0; i < MAX_I; ++i) {
        dt[i] = i;
    }

    findPrime();

    while (true) {
        int n;
        cin >> n;

        if (n == 0)
            break;

        bool bFound = false;

        for (int i = 0; i < vc.size(); ++i) {
            int prime = (n - vc[i]);
            if (0 != dt[prime]) {
                cout << n << " = " << vc[i] << " + " << (n - vc[i]) << '\n';
                bFound = true;
                break;
            }
        }

        if (bFound == false)
            cout << "Goldbach's conjecture is wrong.";
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}

// MAX_I 까지 소수들을 찾는다
void findPrime() {
    for (int i = 2; i < MAX_I; ++i) {
        if (dt[i] != 0) {
            if (IsPrimeNnumber(dt[i])) {
                int delta = dt[i];
                int idx = dt[i] + delta;
                while (idx < MAX_I) {
                    dt[idx] = 0;
                    idx += delta;
                }
                vc.push_back(dt[i]);
            }
        }
    }
}

// prime number
bool IsPrimeNnumber(int n) {
    if (n < 2)
        return false;

    for (int i = 2; i * i <= n; i++)
        if (n % i == 0)
            return false;

    return true;
}