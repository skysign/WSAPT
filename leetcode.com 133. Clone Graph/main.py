from typing import Optional

# Definition for a Node.
class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []


class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        results = []
        nodes_clone = {}
        visited = []

        queue = []
        if node is not None:
            queue.append(node)

        while queue:
            node = queue.pop(0)

            if node.val not in nodes_clone.keys():
                nodes_clone[node.val] = Node(node.val)

            node_clone = nodes_clone[node.val]
            results.append(node_clone)
            visited.append(node.val)

            neighbors_clone = []

            for neighbor in node.neighbors:
                if neighbor.val not in visited:
                    queue.append(neighbor)

                if neighbor.val not in nodes_clone.keys():
                    nodes_clone[neighbor.val] = Node(neighbor.val)

                neighbors_clone.append(nodes_clone[neighbor.val])

            node_clone.neighbors = neighbors_clone

        return results[0] if len(results) else None