from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        dt = []
        self.inorder(root, dt)
        return dt[k - 1]

    def inorder(self, node: Optional[TreeNode], dt: List[int]):
        if node.left:
            self.inorder(node.left, dt)

        dt.append(node.val)

        if node.right:
            self.inorder(node.right, dt)
