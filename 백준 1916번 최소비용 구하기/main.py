import heapq
import sys
from typing import Dict
from collections import defaultdict


def solve():
    N = int(sys.stdin.readline().strip())
    M = int(sys.stdin.readline().strip())
    maps = [sys.maxsize for _ in range(N + 1)]
    edges: Dict = defaultdict(list)

    for _ in range(M):
        fr, to, cost = map(int, sys.stdin.readline().strip().split(' '))
        edges[fr].append([to, cost])

    depature, destination = map(int, sys.stdin.readline().strip().split(' '))
    hq = []
    maps[depature] = 0
    heapq.heappush(hq, [0, depature])

    while hq:
        cost_old, depature = heapq.heappop(hq)

        if maps[depature] < cost_old:
            continue

        for dest, cost_new in edges[depature]:
            if maps[dest] > cost_old + cost_new:
                maps[dest] = cost_old + cost_new
                heapq.heappush(hq, [cost_old + cost_new, dest])

    print(maps[destination])


if __name__ == '__main__':
    solve()
