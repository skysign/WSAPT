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
