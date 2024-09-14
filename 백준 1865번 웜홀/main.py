import sys
from collections import defaultdict, deque


def solve():
    # if float('inf') + 1 == float('inf') -1:
    #     print('inf')

    T = int(sys.stdin.readline().strip())

    for _ in range(T):
        N, M, W = map(int, sys.stdin.readline().strip().split(' '))
        edges = defaultdict(list)

        for _ in range(M):
            fr, to, time = map(int, sys.stdin.readline().strip().split(' '))
            edges[fr].append([to, time])

        for _ in range(W):
            fr, to, time = map(int, sys.stdin.readline().strip().split(' '))
            edges[fr].append([to, -time])

        yes_no = bf(N, edges)

        if yes_no:
            print('YES')
        else:
            print('NO')


def bf(departue, N, edges):
    maps = [sys.maxsize for _ in range(N + 1)]
    maps[departue] = 0

    for i in range(1, N + 1):
        nodes = list(edges.keys())
        for fr in nodes:
            for to, cost_to in edges[fr]:
                if (maps[fr] != sys.maxsize) and (maps[to] > maps[fr] + cost_to):
                    maps[to] = maps[fr] + cost_to
                    if i == N:
                        return True

    return False


if __name__ == '__main__':
    solve()
