# 백준 1699번 제곱수의 합
#
# 유튜브 문제 풀이: https://youtu.be/8u3rY4yoZLM
#
# 파이썬소스: http://bit.ly/3E0ce5t
#
# 문제: https://www.acmicpc.net/problem/1699

import math
import sys

N = int(input())
dp = [0 for i in range(N+1)]


for n in range(1, N+1):
    cnt = sys.maxsize

    for i in range(1, int(math.sqrt(n)) + 1):
        square = i**2
        idx = n - square
        if idx >= 0:
            cnt = min(cnt, dp[idx] +1)

    dp[n] = cnt

print(dp[N])