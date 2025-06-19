from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __init__(self):
        self.answer = 0

    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        self.dfs(root, 0)
        return self.answer

    def dfs(self, node: Optional[TreeNode], v):
        if node.left is None and node.right is None:
            self.answer += (v * 10 + node.val)
            return

        if node.left:
            self.dfs(node.left, v * 10 + node.val)

        if node.right:
            self.dfs(node.right, v * 10 + node.val)
