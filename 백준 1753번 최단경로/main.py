import heapq
import sys
from collections import defaultdict


def solve():
    V, E = map(int, sys.stdin.readline().strip().split(' '))
    K = int(sys.stdin.readline().strip())
    edges = defaultdict(list)

    maps = [sys.maxsize for _ in range(V + 1)]

    for _ in range(E):
        fr, to, cost = map(int, sys.stdin.readline().strip().split(' '))
        edges[fr].append([to, cost])

    maps[K] = 0
    hq = []
    heapq.heappush(hq, [0, K])

    while hq:
        cost, fr = heapq.heappop(hq)

        if maps[fr] < cost:
            continue

        for to, cost in edges[fr]:
            if maps[fr] + cost < maps[to]:
                maps[to] = maps[fr] + cost
                heapq.heappush(hq, [maps[to], to])

    for v in range(1, V + 1):
        if maps[v] == sys.maxsize:
            print('INF')
        else:
            print(maps[v])


if __name__ == '__main__':
    solve()
