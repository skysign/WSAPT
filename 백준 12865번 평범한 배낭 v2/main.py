import sys


def solve():
    N, K = map(int, sys.stdin.readline().strip().split(' '))
    dp = [[0 for _ in range(K + 1)] for _ in range(N + 1)]
    dt = []

    for _ in range(N):
        W, V = map(int, sys.stdin.readline().strip().split(' '))
        dt.append([W, V])

    dt.sort(key=lambda x: [x[0], x[1]])
    dt = [[]] + dt

    for row in range(1, N + 1):
        W, V = dt[row]
        for col in range(K + 1):
            if col < W:
                dp[row][col] = dp[row - 1][col]
            else:
                dp[row][col] = max(dp[row - 1][col], dp[row - 1][col - W] + V)

    print(dp[-1][-1])


if __name__ == '__main__':
    solve()
