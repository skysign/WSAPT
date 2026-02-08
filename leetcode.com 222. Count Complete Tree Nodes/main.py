from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if root == None:
            return 0

        rtn = 1

        if root.left:
            rtn += self.countNodes(root.left)

        if root.right:
            rtn += self.countNodes(root.right)

        return rtn
