import sys
from collections import deque


def solve():
    T = int(sys.stdin.readline().strip())
    for _ in range(T):
        N = int(sys.stdin.readline().strip())
        dts = [0] + list(map(int, sys.stdin.readline().strip().split()))
        edges = [[]] + [[] for _ in range(N)]
        incoming = [0] + [0 for _ in range(N)]

        answer = N

        for fr in range(1, N + 1):
            to = dts[fr]
            edges[fr].append(to)
            incoming[to] += 1

        queue = deque()

        for idx in range(1, N + 1):
            if incoming[idx] == 0:
                queue.append(idx)

        while queue:
            fr = queue.popleft()
            answer -= 1

            for to in edges[fr]:
                incoming[to] -= 1
                if incoming[to] == 0:
                    queue.append(to)

        print(N - answer)

if __name__ == '__main__':
    solve()
