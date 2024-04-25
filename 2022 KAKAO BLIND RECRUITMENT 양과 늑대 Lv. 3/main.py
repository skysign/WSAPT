import copy
from typing import List


class Node:
    def __init__(self, idx: int, n: int):
        self.idx = idx
        self.sheep_or_wolf = n
        self.children: List[Node] = []


def solution(info: List[int], edges: List[List[int]]):
    nodes: List[Node] = [Node(idx, info[idx]) for idx in range(len(info))]

    for idx_parent, idx_child in edges:
        nodes[idx_parent].children.append(nodes[idx_child])

    for node in nodes:
        node.children.sort(key = lambda n: n.idx)

    answer = dfs(nodes[0].children, 1, 0)
    return answer


def dfs(reachable: List[Node], cnt_sheep, cnt_wolf):
    max_sheep = cnt_sheep

    if cnt_sheep <= cnt_wolf:
        return -1

    for idx in range(len(reachable)):
        copy_reachable = copy.deepcopy(reachable)
        node = copy_reachable.pop(idx)

        if node.sheep_or_wolf:
            max_sheep = max(max_sheep, dfs(copy_reachable + node.children, cnt_sheep, cnt_wolf + 1))
        else:
            max_sheep = max(max_sheep, dfs(copy_reachable + node.children, cnt_sheep + 1, cnt_wolf))

    return max_sheep