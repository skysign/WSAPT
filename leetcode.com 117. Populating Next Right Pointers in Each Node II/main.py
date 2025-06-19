from typing import Dict

# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if root is None:
            return root

        dict = {}
        self.postorder(root, 0, dict)
        return root

    def postorder(self, node, level: int, dict: Dict):
        node.next = None

        if node.left:
            self.postorder(node.left, level + 1, dict)

        if node.right:
            self.postorder(node.right, level + 1, dict)

        if level in dict.keys():
            dict[level].next = node
            dict[level] = node
        else:
            dict[level] = node

