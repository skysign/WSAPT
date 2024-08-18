import sys
from typing import List

# python3로는 시간초과로 정답 판정이 안되고
# pypy3로 해서 실행시간을 좀 줄여야, 정답 인정이 됩니다.
def solve():
    N, K = map(int, sys.stdin.readline().strip().split(' '))
    mx = max(N, K)
    dt: List[int] = [sys.maxsize for _ in range(mx + 10)]
    l = len(dt)
    dt[N] = 0

    if N == K:
        print(0)
        return

    time = 0
    queue = []
    add_plus_minus_x2(queue, l, dt, N, time)

    while dt[K] == sys.maxsize:
        time += 1
        queue2 = []

        for n in queue:
            dt[n] = min(dt[n], time)

            if n == K:
                print(time)
                return

            add_plus_minus_x2(queue2, l, dt, n, time)

        queue = queue2

    print(time)


def add_plus_minus_x2(queue: List[int], l, dt, N, time):
    if (N - 1 >= 0) and (N - 1 not in queue) and (dt[N - 1] > time):
        queue.append(N - 1)
    if (N + 1 < l) and (N + 1 not in queue) and (dt[N + 1] > time):
        queue.append(N + 1)
    if (N * 2 < l) and (N * 2 not in queue) and (dt[N * 2] > time):
        queue.append(N * 2)


if __name__ == '__main__':
    solve()
