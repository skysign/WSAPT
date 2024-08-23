import sys
from typing import List, Deque
from collections import deque


def solve():
    N = int(sys.stdin.readline().strip())
    nodes: List[List[int]] = [[] for n in range(N + 1)]
    parents: List[int] = [0 for _ in range(N + 1)]
    parents[1] = 1

    for _ in range(N - 1):
        fr, to = list(map(int, sys.stdin.readline().strip().split(' ')))
        nodes[fr].append(to)
        nodes[to].append(fr)

    bfs(1, nodes, parents)

    for idx in range(2, N + 1):
        print(parents[idx])


def bfs(p, nodes, parents):
    queue: Deque = deque([p])

    while queue:
        p = queue.popleft()

        for child in nodes[p]:
            if parents[child] == 0:
                parents[child] = p
                queue.append(child)


if __name__ == '__main__':
    solve()
