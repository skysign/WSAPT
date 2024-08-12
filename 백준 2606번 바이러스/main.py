import sys
from typing import List


class Node:
    def __init__(self, n):
        self.n = n
        self.infection: bool = False
        self.adjacent: List[Node] = []


def solve():
    computer_cnt = int(sys.stdin.readline().strip()) + 1
    nodes: List[Node] = [Node(n) for n in range(computer_cnt + 1)]
    edges_cnt = int(sys.stdin.readline().strip())
    infection_cnt = 0

    for _ in range(edges_cnt):
        fr, to = map(int, sys.stdin.readline().strip().split(' '))
        nodes[fr].adjacent.append(nodes[to])
        nodes[to].adjacent.append(nodes[fr])

    nodes[1].infection = True
    infection_cnt += 1
    queue: List[Node] = [1]

    while queue:
        idx = queue.pop(0)
        node = nodes[idx]

        for node_adj in node.adjacent:
            if node_adj.infection == False:
                node_adj.infection = True
                infection_cnt += 1

                if node_adj.n not in queue:
                    queue.append(node_adj.n)

    print(infection_cnt -1)


if __name__ == '__main__':
    solve()
