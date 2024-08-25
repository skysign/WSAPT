import sys


def solve():
    stra = ' ' + sys.stdin.readline().strip()
    strb = ' ' + sys.stdin.readline().strip()

    dp = [[0 for _ in range(len(stra))] for _ in range(len(strb))]

    for row_idx in range(1, len(strb)):
        for col_idx in range(1, len(stra)):
            if strb[row_idx] == stra[col_idx]:
                dp[row_idx][col_idx] = dp[row_idx - 1][col_idx - 1] + 1
            else:
                dp[row_idx][col_idx] = max(dp[row_idx - 1][col_idx], dp[row_idx][col_idx - 1])

    mx = dp[-1][-1]
    print(mx)


if __name__ == '__main__':
    solve()
