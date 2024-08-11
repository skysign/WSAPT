import sys
from typing import List


def solve():
    N = int(sys.stdin.readline().strip())
    dt: List[int] = [sys.maxsize for _ in range(N + 1)]

    dt[0] = 0
    dt[1] = 0

    for i in range(1, N+1):
        v = i * 3
        if v <= N:
            dt[v] = min(dt[v], dt[i] + 1)

        v = i * 2
        if v <= N:
            dt[v] = min(dt[v], dt[i] + 1)

        v = i + 1
        if v <= N:
            dt[v] = min(dt[v], dt[i] + 1)

    print(dt[N])


if __name__ == '__main__':
    solve()
