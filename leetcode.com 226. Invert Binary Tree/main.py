from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        self.swap_children(root)
        return root

    def swap_children(self, node: Optional[TreeNode]):
        if node is None:
            return

        tmp = node.left
        node.left = node.right
        node.right = tmp

        self.swap_children(node.left)
        self.swap_children(node.right)
