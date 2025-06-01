from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if not root:
            return False

        return self.is_sysmmetric(root.left, root.right)


    def is_sysmmetric(self, l: Optional[TreeNode], r: Optional[TreeNode]):
        if l is None and r is None:
            return True

        if l is None and r is not None:
            return False

        if l is not None and r is None:
            return False

        if (l and r) and (l.val != r.val):
            return False

        return self.is_sysmmetric(l.left, r.right) and self.is_sysmmetric(l.right, r.left)

