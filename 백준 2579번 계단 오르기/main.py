import sys
from typing import List


def solve():
    N = int(sys.stdin.readline().strip())
    stairs: List[int] = [0]

    for _ in range(N):
        stairs.append(int(sys.stdin.readline().strip()))

    dp: List[int] = [0 for _ in range(len(stairs))]
    dp[1] = stairs[1]

    if N == 1:
        print(dp[1])
        return

    dp[2] = stairs[2] + dp[1]

    if N == 2:
        print(dp[2])
        return

    for i in range(3, N + 1):
        dp[i] = stairs[i] + max(dp[i - 2], stairs[i - 1] + dp[i - 3])

    print(dp[-1])


if __name__ == '__main__':
    solve()
