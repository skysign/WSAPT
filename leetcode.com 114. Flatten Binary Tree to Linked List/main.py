import heapq
from typing import Optional, List, Dict


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        if not root:
            return

        self.travel_pre_order(root)


    def travel_pre_order(self, node: TreeNode):
        if node.left:
            node_right_leaf = node.left
            while node_right_leaf.right != None:
                node_right_leaf = node_right_leaf.right

            tmp = node.right
            node.right = node.left
            node_right_leaf.right = tmp
            node.left = None

        if node.left:
            self.travel_pre_order(node.left)

        if node.right:
            self.travel_pre_order(node.right)
