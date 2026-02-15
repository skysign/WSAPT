from typing import Optional
from collections import deque

# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []


class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if not node:
            return None

        root = node
        root_clone = Node(root.val)
        dict_clone = {}
        dict_clone[root_clone.val] = root_clone
        queue = deque([node for node in root.neighbors])

        while queue:
            node = queue.popleft()

            if dict_clone.keys().__contains__(node.val):
                continue

            dict_clone[node.val] = Node(node.val)

            for neighbor in node.neighbors:
                if dict_clone.keys().__contains__(neighbor.val):
                    dict_clone[node.val].neighbors += [dict_clone[neighbor.val]]
                    dict_clone[neighbor.val].neighbors += [dict_clone[node.val]]

            queue += node.neighbors

        return root_clone