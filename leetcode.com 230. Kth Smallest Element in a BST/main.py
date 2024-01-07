from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def __init__(self, val=0, left=None, right=None):
        self.v = -1

    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        self.travel_inorder(root, 0, k)
        return self.v

    def travel_inorder(self, node: Optional[TreeNode], seq: int, k):
        if self.v != -1:
            return seq

        l_seq = seq

        if node.left:
            l_seq = self.travel_inorder(node.left, l_seq, k)

        l_seq += 1

        if l_seq == k:
            self.v = node.val
            return l_seq

        r_seq = l_seq

        if node.right:
            r_seq = self.travel_inorder(node.right, r_seq, k)

        return r_seq