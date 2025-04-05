import sys
from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def getMinimumDifference(self, root: Optional[TreeNode]) -> int:
        dt = []

        self.dfs_inorder(root, dt)

        answer = sys.maxsize
        for i in range(len(dt) - 1):
            answer = min(answer, dt[i + 1] - dt[i])

        return answer

    def dfs_inorder(self, node: Optional[TreeNode], dt):
        if node.left:
            self.dfs_inorder(node.left, dt)

        dt.append(node.val)

        if node.right:
            self.dfs_inorder(node.right, dt)
