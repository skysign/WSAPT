#include <iostream>
#include <algorithm>	// min() max()
#include <vector>
#include <stack>
using namespace std;

// begin define
// type define
#define LL long long

#define _1_000_000_000 (1000000000)
// end define

// begin utility functions
int LCM(int a, int b);
int GCD(int a, int b);
bool IsPrimeNnumber(int n);
// end utility functions

void solve();

/**
 * BOJ 
 * 문제링크 : 
 * 제출링크 : 
 * 문제풀이 : 
 * 유튜브 문제풀이 : 
 * CPP소스 : 
 * 자바소스 : 
 */
#define N_MAX (1000+1)

void solve() {
	int N;
	cin >> N;
	
	for(int i=0; i<N; ++i) {

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