import sys
from typing import List


class Vertex:
    def __init__(self, v):
        self.v = v
        self.adjacent: List[int] = []


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    vertexes: List[Vertex] = [Vertex(v) for v in range(N + 1)]
    visited: List[bool] = [False for v in range(N + 1)]
    answer = 0

    for _ in range(M):
        fr, to = map(int, sys.stdin.readline().strip().split(' '))
        vertexes[fr].adjacent.append(to)
        vertexes[to].adjacent.append(fr)

    for i in range(1, N + 1):
        if visited[i] == False:
            find_connected_component(i, vertexes, visited)
            answer += 1

    print(answer)


def find_connected_component(v, vertexes: List[Vertex], visited: List[bool]):
    queue = [v]

    while queue:
        v = queue.pop(0)
        vertex = vertexes[v]
        visited[v] = True

        for adj in vertex.adjacent:
            if visited[adj] == False and adj not in queue:
                queue.append(adj)


if __name__ == '__main__':
    solve()
