from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if root == None:
            return False

        return self.rec(root, 0, targetSum)

    def rec(self, node: Optional[TreeNode], v, targetSum) -> bool:
        rtnl, rtnr = False, False

        if node.left:
            rtnl = self.rec(node.left, v + node.val, targetSum)

        if node.right:
            rtnr = self.rec(node.right, v + node.val, targetSum)

        if node.left == None and node.right == None:
            if v + node.val == targetSum:
                return True

        return rtnl or rtnr