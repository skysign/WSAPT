import sys


def solution(filename=''):
    if filename == '':
        mystdin = sys.stdin
    else:
        mystdin = open(filename, 'r')

    dt_cnt, weight_max = list(map(int, mystdin.readline().split(' ')))
    dt = [[0, 0]]
    for _ in range(dt_cnt):
        weight, value = list(map(int, mystdin.readline().split(' ')))
        dt.append([weight, value])

    dp = [[0 for _ in range(weight_max + 1)] for _ in range(len(dt))]

    for i in range(1, len(dt)):
        for j in range(1, weight_max + 1):
            weight, value = dt[i]
            if weight > j:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight] + value)

    print(dp[dt_cnt][weight_max])

    return dp[dt_cnt][weight_max]


if __name__ == '__main__':
    solution()
