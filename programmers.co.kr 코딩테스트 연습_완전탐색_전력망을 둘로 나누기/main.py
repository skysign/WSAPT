from typing import List


def solution(n, wires: List[List[int]]):
    nodes = [[] for n in range(0, n + 1)]

    for e_fr, e_to in wires:
        nodes[e_fr].append(e_to)
        nodes[e_to].append(e_fr)

    answer = n

    for e_fr, e_to in wires:
        nodes[e_fr].remove(e_to)
        nodes[e_to].remove(e_fr)
        visited = [False for _ in range(0, n + 1)]
        cnt = mycount(1, nodes, visited)
        answer = min(answer, abs((n - cnt) - cnt))
        nodes[e_fr].append(e_to)
        nodes[e_to].append(e_fr)

    return answer


def mycount(idx, nodes: List[List[int]], visited: List[bool]):
    if visited[idx]:
        return 0

    visited[idx] = True

    edges: List[int] = nodes[idx]
    cnt = 1

    for i in edges:
        cnt += mycount(i, nodes, visited)

    return cnt
