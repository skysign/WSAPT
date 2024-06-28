import sys
sys.setrecursionlimit(10 ** 6)
from typing import List, Type, Optional


class Node:
    def __init__(self, n: int):
        self.n: int = n
        self.adjacent = []


def connect_node(mine: Type[Node], node: Type[Node]):
    mine.adjacent.append(node.n)
    node.adjacent.append(mine.n)


def visit(node: Optional[Node], n_parent: int, dp: List[List[int]]):
    for i in node.adjacent:
        if i != n_parent:
            dp[node.n][0] += dp[i][1]
            dp[node.n][1] += min(dp[i][0], dp[i][1])

    dp[node.n][1] += 1


def post_order(node: Optional[Node], n_parent: int, nodes: List[Optional[Node]], dp: List[List[int]]):
    for i in node.adjacent:
        if i != n_parent:
            post_order(nodes[i], node.n, nodes, dp)

    visit(node, n_parent, dp)


def solution(n: int, lighthouse: List[List[int]]):
    nodes: List[Optional[Node]] = [Node(i) for i in range(0, n + 1)]
    # 0번이 내가 꺼진 상태
    # 1번이 내가 켜진 상태
    dp: List[List[int]] = [[0, 0] for _ in range(n + 1)]

    for n1, n2 in lighthouse:
        connect_node(nodes[n1], nodes[n2])

    post_order(nodes[1], -1, nodes, dp)

    answer = min(dp[1][0], dp[1][1])

    return answer
