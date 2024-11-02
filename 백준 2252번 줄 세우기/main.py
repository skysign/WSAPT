import sys
from collections import deque


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    incomming = [0] + [0 for _ in range(N)]
    edges_fr_to = [[]] + [[] for _ in range(N)]

    for _ in range(M):
        fr, to = map(int, sys.stdin.readline().strip().split())
        edges_fr_to[fr].append(to)
        incomming[to] += 1

    queue = deque()

    for i in range(1, len(incomming)):
        if incomming[i] == 0:
            queue.append(i)

    ans = []

    while queue:
        n = queue.popleft()
        ans.append(n)

        for to in edges_fr_to[n]:
            incomming[to] -= 1

            if incomming[to] == 0:
                queue.append(to)

    print(' '.join(map(str, ans)))


if __name__ == '__main__':
    solve()
