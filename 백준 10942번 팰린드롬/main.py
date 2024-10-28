import sys


def solve():
    N = int(sys.stdin.readline().strip())
    dts = list(map(int, sys.stdin.readline().strip().split()))
    dp = [[False for _ in range(N)] for _ in range(N)]
    M = int(sys.stdin.readline().strip())

    for i in range(N):
        dp[i][i] = True

    for mid in range(N):
        for l in range(1, N):
            if 0 <= mid - l and mid + l < N and dts[mid - l] == dts[mid + l] and dp[mid - l + 1][mid + l - 1]:
                dp[mid - l][mid + l] = True
            else:
                break

    for i in range(N - 1):
        if dts[i] == dts[i + 1]:
            dp[i][i + 1] = True

    for i in range(N - 1):
        ii = i + 1
        for l in range(1, N):
            if 0 <= i - l and ii + l < N and dts[i - l] == dts[ii + l] and dp[i - l + 1][ii + l - 1]:
                dp[i - l][ii + l] = True
            else:
                break

    for _ in range(M):
        S, E = map(int, sys.stdin.readline().strip().split())

        if dp[S - 1][E - 1]:
            print(1)
        else:
            print(0)


if __name__ == '__main__':
    solve()
