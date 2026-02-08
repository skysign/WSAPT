from typing import Optional, List
from collections import defaultdict, deque

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        def bfs(node: Optional[TreeNode]):
            if node is None:
                return []

            rtn = []
            queue = deque()
            queue.append([node, 0])

            while queue:
                sibling = []

                for _ in range(len(queue)):
                    node, level = queue.popleft()

                    if level % 2 == 0:
                        sibling = sibling + [node.val]
                    else:
                        sibling = [node.val] + sibling

                    if node.left:
                        queue.append([node.left, level + 1])
                    if node.right:
                        queue.append([node.right, level + 1])

                rtn.append(sibling)

            return rtn

        return bfs(root)
