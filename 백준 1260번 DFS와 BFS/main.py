import sys
from typing import List


class Vertex:
    def __init__(self, v):
        self.v = v
        self.adjacent: List[int] = []


def solve():
    N, M, V = map(int, sys.stdin.readline().strip().split(' '))
    vertexes: List[Vertex] = [Vertex(n) for n in range(N + 1)]

    for _ in range(M):
        fr, to = map(int, sys.stdin.readline().strip().split(' '))
        vertexes[fr].adjacent.append(to)
        vertexes[to].adjacent.append(fr)

    for vertex in vertexes:
        vertex.adjacent.sort()

    answer: List[int] = []
    answer.append(V)
    dfs(V, vertexes, answer)
    answer = map(str, answer)
    print(' '.join(answer))

    answer: List[int] = []
    bfs(V, vertexes, answer)
    answer = map(str, answer)
    print(' '.join(answer))


def dfs(V, vertexes, answer):
    for v in vertexes[V].adjacent:
        if v not in answer:
            answer.append(v)
            dfs(v, vertexes, answer)


def bfs(V, vertexes, answer):
    queue: List[int] = [V]
    answer.append(V)

    while queue:
        V = queue.pop(0)
        for v in vertexes[V].adjacent:
            if v not in answer:
                answer.append(v)
                queue.append(v)


if __name__ == '__main__':
    solve()
