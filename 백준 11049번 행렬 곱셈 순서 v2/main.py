import sys


def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    mats = [list(map(int, input().strip().split())) for _ in range(N)]
    dp = [[sys.maxsize for _ in range(N)] for _ in range(N)]

    for i in range(N):
        dp[i][i] = 0

    for bgn in range(N - 1):
        end = bgn + 1
        dp[bgn][end] = mats[bgn][0] * mats[bgn][1] * mats[end][1]

    for l in range(2, N):
        for bgn in range(N - l):
            end = bgn + l
            for mid in range(bgn, end):
                dp[bgn][end] = min(dp[bgn][end], \
                                   dp[bgn][mid] + dp[mid + 1][end] \
                                   + (mats[bgn][0] * mats[mid][1] * mats[end][1]))

    print(dp[0][N - 1])


if __name__ == '__main__':
    solve()
