import sys
from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def my(self, node: Optional[TreeNode], mxl, mxr):
        if not (mxl < node.val < mxr):
            return False

        rtnl, rtnr = True, True

        if node.left:
            rtnl = self.my(node.left, mxl, node.val)

        if node.right:
            rtnr = self.my(node.right, node.val, mxr)

        return rtnl and rtnr

    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        mxl, mxr = -sys.maxsize, sys.maxsize
        return self.my(root, mxl, mxr)
