from typing import List, Optional


class Node:
    def __init__(self, node_num, target):
        self.num = node_num
        self.target = target
        self.children: List[Optional[Node]] = []
        self.idx_child = 0
        self.indexes123: List[int] = []

    def send_num123(self, idx123):
        if len(self.children) == 0:
            self.indexes123.append(idx123)
            return not len(self.indexes123) > self.target

        rtn = self.children[self.idx_child].send_num123(idx123)
        self.idx_child = (self.idx_child + 1) % len(self.children)
        return rtn

    def __lt__(self, other):
        return self.num < other.num


def num123_make_target(leaves):
    for idx in range(len(leaves)):
        if not len(leaves[idx].indexes123) <= leaves[idx].target <= len(leaves[idx].indexes123) * 3:
            return False

    return True


def make_target_from123(length123: int, target: int):
    num123 = 3
    answer = []

    while target > 0:
        if length123 - 1 <= target - num123 <= (length123 - 1) * 3:
            answer.append(num123)
            target -= num123
            length123 -= 1
        else:
            num123 -= 1

    answer.sort()
    return answer


def solution(edges: List[List[int]], target: List[int]):
    nodes: List[Node] = [None for _ in range(101)]

    for parent_num, child_num in edges:
        if nodes[parent_num] is None:
            nodes[parent_num] = Node(parent_num, target[parent_num - 1])
        if nodes[child_num] is None:
            nodes[child_num] = Node(child_num, target[child_num - 1])

        nodes[parent_num].children.append(nodes[child_num])

    leaves: List[Node] = []

    for node_num in range(101):
        if nodes[node_num]:
            if len(nodes[node_num].children) > 0:
                nodes[node_num].children.sort()
            else:
                leaves.append(nodes[node_num])

    idx123 = 0
    while True:
        if False == nodes[1].send_num123(idx123):
            return [-1]

        idx123 += 1

        if num123_make_target(leaves):
            break

    answer = [0] * idx123
    for leaf in leaves:
        ans = make_target_from123(len(leaf.indexes123), target[leaf.num - 1])
        ans.sort()
        for idx in range(len(leaf.indexes123)):
            answer[leaf.indexes123[idx]] = ans[idx]

    return answer
