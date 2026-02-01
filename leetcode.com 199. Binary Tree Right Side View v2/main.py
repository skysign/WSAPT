import collections
from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        dt = collections.defaultdict(int)
        self.inorder(root, dt, 0)
        keys = sorted(dt.keys())
        rtn = []
        for i in keys:
            rtn.append(dt[i])

        return rtn

    def inorder(self, node: Optional[TreeNode], dt, level: int):
        if node:
            dt[level] = node.val

            if node.left:
                self.inorder(node.left, dt, level + 1)

            if node.right:
                self.inorder(node.right, dt, level + 1)
