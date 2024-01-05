from typing import Optional, List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left: Optional[TreeNode] = left
        self.right: Optional[TreeNode] = right

class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        min_val = -1 * (10 ** 31)
        max_val = (10 ** 31) -1
        return self.travel_inorder(root, min_val, max_val)

    def travel_inorder(self, node: Optional[TreeNode], min_val: int, max_val:int) -> bool:
        if not node:
            return True

        if not (min_val < node.val < max_val):
            return False

        return self.travel_inorder(node.left, min_val, node.val) \
                and self.travel_inorder(node.right, node.val, max_val)