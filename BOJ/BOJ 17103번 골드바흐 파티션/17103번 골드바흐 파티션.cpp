/**
 * BOJ 17103번 골드바흐 파티션
 * 문제링크 : https://www.acmicpc.net/problem/17103
 * 제출링크 : https://www.acmicpc.net/source/19926999
 * 문제풀이 : https://skysign.tistory.com/238
 */

#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
#include <list>
#include <queue>
using namespace std;

void solve();

int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);

#define MAX_I 1000000
int dt[MAX_I] = { 0, };
int dt_primes[MAX_I];

void solve() {
    // N 과 dt[] 에 입력받기
    int N;
    int iMax = 0;
    cin >> N;

    for (int i = 0; i < N; ++i) {
        cin >> dt[i];

        // 입력받은 수의 최대값 구하기, 최대값까지만, 소수를 찾는 대 사용함
        iMax = max(iMax, dt[i]);
    }

    // iMax 이하의 소수 구하기
    for (int i = 2; i <= iMax; ++i) {
        dt_primes[i] = i;
    }

    vector<int> vec;

    for (int i = 2; i <= iMax; ++i) {
        if (dt_primes[i] != 0 && IsPrimeNnumber(i)) {
            vec.push_back(i);
            // remove (prime number) * n
            for (int j = (i + i); j <= iMax; j += i) {
                dt_primes[j] = 0;
            }
        }
    }

    // 구한 소수로, 골드바흐 파티션의 갯수 찾기
    for (int i = 0; i < N; ++i) {
        int r = 0;
        for (int j = 0; j < vec.size(); ++j) {
            int remained = dt[i] - vec[j];

            if ((remained >=2) && 
                (dt_primes[remained] != 0) &&
                (dt_primes[remained] <= dt[i] - dt_primes[remained]))
                    ++r;
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

    return 0;
}

// LCM
int LCM(int a, int b) {
    return a * (b / GCD(a, b));
}

// GCD
int GCD(int a, int b) {
    if (0 == b) {
        return a;
    }

    return GCD(b, a % b);
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