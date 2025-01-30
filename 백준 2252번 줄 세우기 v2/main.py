import sys
from collections import defaultdict


def solve():
    input = sys.stdin.readline
    N, M = map(int, input().strip().split())
    edges_fr2to = defaultdict(list)
    incoming = [0 for _ in range(N + 1)]

    for _ in range(M):
        fr, to = map(int, input().strip().split())
        edges_fr2to[fr].append(to)
        incoming[to] += 1

    queue = []
    for n in range(1, N + 1):
        if incoming[n] == 0:
            queue.append(n)

    ans = []
    while queue:
        fr = queue.pop(0)
        ans.append(fr)

        while edges_fr2to[fr]:
            to = edges_fr2to[fr].pop(0)
            incoming[to] -= 1
            if incoming[to] == 0:
                queue.append(to)

    print(' '.join(map(str, ans)))


if __name__ == '__main__':
    solve()
