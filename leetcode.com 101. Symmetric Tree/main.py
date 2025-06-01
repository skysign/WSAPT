from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        dt = []
        self.in_order(root, dt)
        length = len(dt)
        idx_mid = length // 2
        if length != idx_mid * 2 + 1:
            return False

        idx_left, idx_right = idx_mid, idx_mid

        while 0 <= idx_left:
            if dt[idx_left] == dt[idx_right]:
                idx_left -= 1
                idx_right += 1
                continue
            else:
                return False

        return True

    def in_order(self, node: Optional[TreeNode], dt: List[int]):
        if not node:
            return

        if node.left:
            node.left.val += (node.val + 1)
            self.in_order(node.left, dt)

        dt.append(node.val)

        if node.right:
            node.right.val += (node.val + 1)
            self.in_order(node.right, dt)
