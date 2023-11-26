import sys
sys.setrecursionlimit(10**6)

from heapq import heappush, heappop
from typing import List

class bnode:
    def __init__(self, n, x):
        self.n = n
        self.x = x
        self.left = None
        self.right = None

def solution(nodexys):
    heap = []
    max_x = -1

    for idx in range(len(nodexys)):
        x, y = nodexys[idx]
        n = idx+1
        heappush(heap, (-y, x, n))
        max_x = max(max_x, x)

    max_x += 1

    _, x, n = heappop(heap)
    root = bnode(n, x)

    queue = [[root, 0, root.x, max_x]]
    while len(queue) > 0:
        node, l, m, r = queue.pop(0)

        if len(heap) > 0:
            item = heappop(heap)
            _, x, n = item

            if l <= x <= m:
                node.left = bnode(n, x)
                queue.append([node.left, l, node.left.x, m])
            else:
                heappush(heap, item)

        if len(heap) > 0:
            item = heappop(heap)
            _, x, n = item

            if m <= x <= r:
                node.right = bnode(n, x)
                queue.append([node.right, m, node.right.x, r])
            else:
                heappush(heap, item)

    def preorder(node, r: List):
        r.append(node.n)

        if node.left:
            r.extend(preorder(node.left, []))
        if node.right:
            r.extend(preorder(node.right, []))

        return r

    def postorder(node, r: List):
        if node.left:
            r.extend(postorder(node.left, []))
        if node.right:
            r.extend(postorder(node.right, []))

        r.append(node.n)
        return r

    r1 = preorder(root, [])
    r2 = postorder(root, [])

    answer = [r1, r2]
    return answer