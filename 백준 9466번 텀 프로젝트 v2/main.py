import sys
from collections import deque


def solve():
    input = sys.stdin.readline
    T = int(input().strip())

    for _ in range(T):
        N = int(input().strip())
        dts = [0] + list(map(int, input().strip().split()))
        incoming = [0 for _ in range(len(dts))]
        for i in range(len(dts)):
            fr, to = i, dts[i]
            incoming[to] += 1

        queue = deque()

        for i in range(len(dts)):
            if incoming[i] == 0:
                queue.append(i)

        while queue:
            fr = queue.popleft()
            to = dts[fr]
            incoming[to] -= 1
            if incoming[to] == 0:
                queue.append(to)

        answer = 0
        for i in range(len(dts)):
            if incoming[i] == 0:
                answer += 1

        print(answer)

if __name__ == '__main__':
    solve()