import heapq
import sys
from collections import defaultdict


def solve():
    N, M, X = map(int, sys.stdin.readline().strip().split(' '))
    edges = defaultdict(list)

    for _ in range(M):
        fr, to, time = map(int, sys.stdin.readline().strip().split(' '))
        edges[fr].append([to, time])

    answer = 0
    for student in range(1, N + 1):
        tmp = get_time(student, X, edges, N)
        tmp += get_time(X, student, edges, N)

        answer = max(answer, tmp)

    print(answer)


def get_time(depature, destination, edges, N):
    maps = [sys.maxsize for _ in range(N + 1)]
    maps[depature] = 0
    hq = []
    heapq.heappush(hq, [0, depature])

    while hq:
        time_fr, fr = heapq.heappop(hq)

        if maps[fr] < time_fr:
            continue

        for to, time_to in edges[fr]:
            if maps[to] > time_fr + time_to:
                heapq.heappush(hq, [time_fr + time_to, to])
                maps[to] = time_fr + time_to

    return maps[destination]


if __name__ == '__main__':
    solve()
