import sys
from typing import List


def solve():
    N = int(sys.stdin.readline().strip())
    dp: List[List[int]] = []

    for _ in range(N):
        dp.append(list(map(int, sys.stdin.readline().strip().split(' '))))

    for idx in range(1, N):
        dp[idx][0] += min(dp[idx - 1][1], dp[idx - 1][2])
        dp[idx][1] += min(dp[idx - 1][0], dp[idx - 1][2])
        dp[idx][2] += min(dp[idx - 1][0], dp[idx - 1][1])

    print(min(dp[N - 1]))


if __name__ == '__main__':
    solve()
