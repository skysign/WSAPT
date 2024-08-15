import sys
from typing import List


def solve():
    N = int(sys.stdin.readline().strip())
    dp: List[int] = [0 for _ in range(N + 1)]

    dp[0] = 0
    dp[1] = 1
    if N == 1:
        print(dp[N])
        return

    dp[2] = 1 + dp[1]
    if N == 2:
        print(dp[N])
        return

    for i in range(3, N + 1):
        dp[i] = (dp[i-1] + dp[i-2]) % 10007

    print(dp[N])


if __name__ == '__main__':
    solve()
