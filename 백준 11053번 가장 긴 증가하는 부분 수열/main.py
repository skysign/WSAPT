import sys
from typing import List


def solve():
    N = int(sys.stdin.readline().strip())
    dts: List[int] = [0] + list(map(int, sys.stdin.readline().strip().split(' ')))
    dp: List[int] = [0 for _ in range(N + 1)]
    answer = 0

    for idx in range(1, N + 1):
        mx = 0
        for i2 in range(idx + 1):
            if dts[i2] < dts[idx]:
                mx = max(mx, dp[i2])

        dp[idx] = mx + 1
        answer = max(answer, dp[idx])

    print(answer)


if __name__ == '__main__':
    solve()
