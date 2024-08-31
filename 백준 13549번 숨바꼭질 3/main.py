import sys
from typing import Deque
from collections import deque


def solve():
    N, K = map(int, sys.stdin.readline().strip().split(' '))
    maps = [sys.maxsize for _ in range(100000 + 1)]
    queue: Deque = deque([[N, 0]])

    while queue:
        n, time = queue.popleft()
        if time < maps[n]:
            maps[n] = time
            if n == K:
                print(time)
                return
        else:
            continue

        if n == 0:
            queue.append([n + 1, time + 1])
            continue

        if n * 2 <= 100000:
            queue.appendleft([n * 2, time])

        if n - 1 >= 0:
            queue.append([n - 1, time + 1])

        if n + 1 <= 100000:
            queue.append([n + 1, time + 1])


if __name__ == '__main__':
    solve()
