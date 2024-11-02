import copy
import sys
from collections import deque

# 문제를 BFS로 풀수 없는 반례
# 3 3
# 0 1
# 1 2
# 1 2
# 즉, 마지막 edge가 cycle을 만드는 경우는 BFS로 구현하면, 알 수 없음

def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    vertexes = [n for n in range(N)]

    for m in range(M):
        v1, v2 = map(int, sys.stdin.readline().strip().split())

        root_v1 = find(v1, vertexes)
        root_v2 = find(v2, vertexes)

        if root_v1 == root_v2:
            print(m + 1)
            return
        else:
            union(root_v1, root_v2, vertexes)

    print(0)


def union(v1, v2, vertexes):
    vertexes[max(v1, v2)] = min(v1, v2)


def find(v, vertexes):
    if v != vertexes[v]:
        vertexes[v] = find(vertexes[v], vertexes)
        return vertexes[v]

    return vertexes[v]


if __name__ == '__main__':
    solve()
