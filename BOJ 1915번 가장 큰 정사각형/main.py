# 백준 1915번 가장 큰 정사각형
# 문제 링크: https://www.acmicpc.net/problem/1915
# 파이썬 소스: https://bit.ly/3WvyrCz
# 유튜브 문제 풀이: https://youtu.be/ZUHMmUfCqLw?si=AqZQlkRKOVBwzOJz

import sys


def solution(filename=''):
    mystdin = None
    if filename == '':
        mystdin = sys.stdin
    else:
        mystdin = open(filename, 'r')

    ROW, COL = list(map(int, mystdin.readline().split(' ')))
    dt = [[0 for _ in range(COL + 1)]]
    dp = [[0 for _ in range(COL + 1)]]
    for _ in range(ROW):
        l: str = mystdin.readline().rstrip()
        dt.append([0] + list(map(int, list(l))))
        dp.append([0 for _ in range(COL + 1)])

    mx: int = 0
    for r in range(1, ROW + 1):
        for c in range(COL + 1):
            if dt[r][c]:
                mn = min(dp[r - 1][c - 1], dp[r - 1][c], dp[r][c - 1])
                dp[r][c] = mn + 1
                mx = max(dp[r][c], mx)

    answer = str(mx * mx)
    print(answer)
    return answer


if __name__ == '__main__':
    solution()
