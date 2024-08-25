import sys


def solve():
    T = int(sys.stdin.readline().strip())

    for _ in range(T):
        n = int(sys.stdin.readline().strip())
        dts = []
        line = list(map(int, sys.stdin.readline().strip().split(' ')))
        line = [0, 0] + line
        dts.append(line)
        line = list(map(int, sys.stdin.readline().strip().split(' ')))
        line = [0, 0] + line
        dts.append(line)
        dp = [[0 for _ in range(len(dts[0]))] for _ in range(2)]

        for col_idx in range(2, n + 2):
            dp[0][col_idx] = dts[0][col_idx] + max(dp[1][col_idx - 1], dp[1][col_idx - 2])
            dp[1][col_idx] = dts[1][col_idx] + max(dp[0][col_idx - 1], dp[0][col_idx - 2])

        print(max(dp[0][n + 1], dp[1][n + 1]))


if __name__ == '__main__':
    solve()
