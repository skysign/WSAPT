from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        lrtn, lrtn2 = self.path_sum(root.left)
        rrtn, rrtn2 = self.path_sum(root.right)

        rtn = max(root.val,
                  lrtn,
                  rrtn,
                  root.val + lrtn,
                  root.val + rrtn,
                  root.val + lrtn + rrtn)

        rtn = max(rtn, lrtn2, rrtn2)

        return rtn

    def path_sum(self, node: Optional[TreeNode]):
        if not node:
            return [-1001, -1001]

        if not node.left and not node.right:
            return [node.val, node.val]

        lrtn, lrtn2 = self.path_sum(node.left)
        rrtn, rrtn2 = self.path_sum(node.right)

        rtn = max(node.val,
                  node.val + lrtn,
                  node.val + rrtn)

        rtn2 = max(node.val,
                   node.val + lrtn + rrtn,
                   node.val + lrtn,
                   node.val + rrtn,
                   lrtn2,
                   rrtn2)

        return [rtn, rtn2]