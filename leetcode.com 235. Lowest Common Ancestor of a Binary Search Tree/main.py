from typing import List
from copy import deepcopy

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def __init__(self):
        self.found = False
        self.pathes = None
        self.lcs = None
        self.lcsNode = None

    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        self.found = False
        self.pathes = None
        self.travel_in_order(root, p, [])
        p_pathes = self.pathes

        self.found = False
        self.pathes = None
        self.travel_in_order(root, q, [])
        q_pathes = self.pathes

        long_pathes = p_pathes if len(p_pathes) >= len(q_pathes) else q_pathes
        short_pathes = p_pathes if len(p_pathes) < len(q_pathes) else q_pathes

        for v in short_pathes:
            if v in long_pathes:
                self.lcs = v
                break

        self.found = False
        self.travel_in_order_by_v(root, self.lcs)

        return self.lcsNode

    def travel_in_order(self, root: 'TreeNode', target: 'TreeNode', pathes: List):
        if self.found:
            return

        pathes.insert(0, root.val)

        if root == target:
            self.found = True
            self.pathes = pathes
            return

        if root.left:
            self.travel_in_order(root.left, target, deepcopy(pathes))

        if root.right:
            self.travel_in_order(root.right, target, deepcopy(pathes))

    def travel_in_order_by_v(self, node: 'TreeNode', v: int):
        if self.found:
            return

        if node.val == v:
            self.found = True
            self.lcsNode = node
            return

        if node.left:
            self.travel_in_order_by_v(node.left, v)

        if node.right:
            self.travel_in_order_by_v(node.right, v)