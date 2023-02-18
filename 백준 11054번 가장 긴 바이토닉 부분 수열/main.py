# 백준 11054번 가장 긴 바이토닉 부분 수열
#
# 유튜브 문제 풀이: https://youtu.be/SEdX0CrOfCg
#
# 파이썬소스: http://bit.ly/3Z3fl4C
#
# 문제: https://www.acmicpc.net/problem/11054

N = int(input())
dt = [0] + list(map(int, input().split())) + [0]
LIS = [0 for i in range(N + 2)]
LISR = [0 for i in range(N + 2)]

for i in range(1, N+1):
    for j in range(0, i):
        if dt[j] < dt[i]:
            LIS[i] = max(LIS[i], LIS[j] + 1)

for i in range(N, 0, -1):
    for j in range(N+1, i, -1):
        if dt[i] > dt[j]:
            LISR[i] = max(LISR[i], LISR[j] + 1)

max_bitonic = 0

for i in range(1, N+1):
    max_bitonic = max(max_bitonic, LIS[i] + LISR[i])

print(max_bitonic -1)
