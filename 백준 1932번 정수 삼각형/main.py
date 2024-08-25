import sys
from typing import List


def solve():
    n = int(sys.stdin.readline().strip())
    dts: List[List[int]] = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
    dp: List[List[int]] = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

    for row_idx in range(1, n + 1):
        line = list(map(int, sys.stdin.readline().strip().split(' ')))
        for col_idx in range(1, row_idx + 1):
            dts[row_idx][col_idx] = line[col_idx - 1]

    dp[1][1] = dts[1][1]

    for row_idx in range(2, n + 1):
        for col_idx in range(1, row_idx + 1):
            dp[row_idx][col_idx] = dts[row_idx][col_idx] + max(dp[row_idx - 1][col_idx], dp[row_idx - 1][col_idx - 1])

    print(max(dp[n]))


if __name__ == '__main__':
    solve()
