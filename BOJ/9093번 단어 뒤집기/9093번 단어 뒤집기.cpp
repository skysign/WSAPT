/**
 * BOJ 9093번 단어 뒤집기
 * 문제링크 : https://www.acmicpc.net/problem/9093
 * 제출링크 : https://www.acmicpc.net/source/19602024
 */

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <vector>
using namespace std;

void solve();

void solve() {
    int N;
    cin >> N;
    cin.ignore();

    for (int i = 0; i < N; ++i) {
        string line;
        getline(cin, line);

        stringstream ss(line);
        string word;
        vector<string> vc;

        while (ss >> word) {
            vc.push_back(word);
        }

        for (string const& w : vc) {
            for (int i = w.length()-1; i >=0 ; --i) {
                cout << w[i];
            }
            cout << " ";
        }

        cout << '\n';
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    solve();
}