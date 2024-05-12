from typing import List
import sys

sys.setrecursionlimit(300000)


class Node:
    def __init__(self, idx: int, sale: int):
        self.number = idx
        self.sale = sale
        self.children: List[Node] = []
        self.me = sale
        self.not_me = 0
        self.leaf = False


def solution(sales: List[int], links: List[List[int]]):
    nodes = [Node(idx + 1, sales[idx]) for idx in range(len(sales))]

    for parent, child in links:
        nodes[parent - 1].children.append(nodes[child - 1])

    dfs(nodes[0])

    return min(nodes[0].me, nodes[0].not_me)


def dfs(node: Node):
    if len(node.children) == 0:
        node.leaf = True
        return

    for child in node.children:
        dfs(child)

    one_child_join = False

    for child in node.children:
        node.me += min(child.me, child.not_me)
        node.not_me += min(child.me, child.not_me)

        if child.me < child.not_me:
            one_child_join = True

    if one_child_join:
        return

    mn = sys.maxsize
    for child in node.children:
        mn = min(mn, child.me - child.not_me)

    node.not_me += mn
