from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def __init__(self):
        self.answer = -1001

    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        self.path_sum(root)
        return self.answer

    def path_sum(self, node: Optional[TreeNode]):
        if not node:
            return 0

        max_left = self.path_sum(node.left)
        max_right = self.path_sum(node.right)

        path1 = max(node.val, node.val + max_left, node.val + max_right)
        path2 = node.val + max_left + max_right

        self.answer = max(self.answer, path1, path2)

        return path1