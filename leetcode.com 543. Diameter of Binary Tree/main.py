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

    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        self.maxDepth(root)
        return self.answer

    def maxDepth(self, node):
        left = self.maxDepth(node.left) if node.left else 0
        right = self.maxDepth(node.right) if node.right else 0

        self.answer = max(self.answer, left + right)

        return 1 + max(left, right)