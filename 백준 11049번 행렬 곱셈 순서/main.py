import sys


def solve():
    N = int(sys.stdin.readline().strip())
    matrix = [list(map(int, sys.stdin.readline().strip().split())) for _ in range(N)]
    dp = [[sys.maxsize] * N for _ in range(N)]

    for i in range(N):
        dp[i][i] = 0

    for idx_bgn in range(N - 1):
        idx_end = idx_bgn + 1
        dp[idx_bgn][idx_end] = matrix[idx_bgn][0] * matrix[idx_bgn][1] * matrix[idx_end][1]

    for l in range(2, N):
        for idx_bgn in range(N - l):
            idx_end = idx_bgn + l
            for idx_mid in range(idx_bgn, idx_end):
                dp[idx_bgn][idx_end] = min(dp[idx_bgn][idx_end],
                                           dp[idx_bgn][idx_mid] + dp[idx_mid + 1][idx_end] \
                                           + matrix[idx_bgn][0] * matrix[idx_mid][1] * matrix[idx_end][1])

    print(dp[0][N - 1])


if __name__ == '__main__':
    solve()
