from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0

        return self.depth(root, 1)

    def depth(self, node, level):
        if node.left is None and node.right is None:
            return level

        left = 0
        if node.left:
            left = self.depth(node.left, level +1)

        right = 0
        if node.right:
            right = self.depth(node.right, level +1)

        return max(left, right)