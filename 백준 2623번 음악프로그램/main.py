import sys
from collections import deque


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    edges = [[] for _ in range(N + 1)]
    incoming = [0 for _ in range(N + 1)]
    # outgoing = [0 for _ in range(N + 1)]

    for _ in range(M):
        dts = list(map(int, sys.stdin.readline().strip().split()))
        dts.pop(0)

        fr = dts.pop(0)
        for to in dts:
            edges[fr].append(to)
            incoming[to] += 1
            fr = to

    queue = deque()
    for i in range(1, len(incoming)):
        if incoming[i] == 0:
            queue.append(i)

    if len(queue) == 0:
        print(0)
        return

    answer = []

    while queue:
        v = queue.popleft()
        answer.append(v)

        for to in edges[v]:
            incoming[to] -= 1
            if incoming[to] == 0:
                queue.append(to)

    # 사이클이 존재해서, 위상정렬로 모두 방문할 수 없는 경우
    if len(answer) != N:
        print(0)
        return

    for a in answer:
        print(a)

if __name__ == '__main__':
    solve()
