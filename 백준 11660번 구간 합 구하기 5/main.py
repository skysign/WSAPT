import sys


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    maps = [[0 for _ in range(N + 1)]]
    dp = [[0 for _ in range(N + 1)] for _ in range(N + 1)]

    for _ in range(N):
        line = list(map(int, sys.stdin.readline().strip().split(' ')))
        maps.append([0] + line)

    for idx_row in range(1, N + 1):
        for idx_col in range(1, N + 1):
            dp[idx_row][idx_col] = dp[idx_row - 1][idx_col] + dp[idx_row][idx_col - 1] - dp[idx_row - 1][idx_col - 1] + maps[idx_row][idx_col]

    for _ in range(M):
        y1, x1, y2, x2 = map(int, sys.stdin.readline().strip().split(' '))
        v = map_sum(dp, y1, x1, y2, x2)
        print(v)


def map_sum(dp, y1, x1, y2, x2):
    rs, rc = y1 - 1, x1 - 1
    return dp[y2][x2] - (dp[y2][rc] + dp[rs][x2]) + dp[rs][rc]


if __name__ == '__main__':
    solve()
