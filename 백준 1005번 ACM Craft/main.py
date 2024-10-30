import sys
from collections import deque


def solve():
    T = int(sys.stdin.readline().strip())

    for _ in range(T):
        N, K = map(int, sys.stdin.readline().strip().split())
        dts = [0] + list(map(int, sys.stdin.readline().strip().split()))
        dp = [0 for _ in range(len(dts))]
        edges_fr_to = [[] for _ in range(len(dts))]
        edges_to_fr = [[] for _ in range(len(dts))]
        incomming = [0 for _ in range(len(dts))]

        for _ in range(K):
            fr, to = map(int, sys.stdin.readline().strip().split())
            edges_to_fr[to].append(fr)
            edges_fr_to[fr].append(to)
            incomming[to] += 1

        W = int(sys.stdin.readline().strip())

        queue = deque()

        for n in range(1, len(dts)):
            if incomming[n] == 0:
                queue.append(n)

        while queue:
            to = queue.popleft()
            frs = edges_to_fr[to]

            if len(frs) == 0:
                dp[to] = dts[to]
            else:
                mx_local = 0
                for fr in frs:
                    mx_local = max(mx_local, dp[fr])
                dp[to] = dts[to] + mx_local

            tos = edges_fr_to[to]
            for to in tos:
                incomming[to] -= 1
                if incomming[to] == 0:
                    queue.append(to)

        print(dp[W])


if __name__ == '__main__':
    solve()
