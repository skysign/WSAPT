from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __init__(self):
        self.mx = 0

    def maxDepth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0

        def dfs(node: Optional[TreeNode], level: int):
            if node.left:
                dfs(node.left, level + 1)
            if node.right:
                dfs(node.right, level + 1)

            self.mx = max(self.mx, level)

        dfs(root, 1)

        return self.mx