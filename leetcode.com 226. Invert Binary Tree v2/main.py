from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root:
            return None

        def invert(node: Optional[TreeNode]):
            if not node:
                return

            tmp = node.left
            node.left = node.right
            node.right = tmp
            invert(node.left)
            invert(node.right)

        invert(root)
        return root