from collections import defaultdict
from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        rtn = []
        dt = defaultdict(list)
        self.travel(root, dt, 0)

        keys = list(dt.keys())
        keys.sort()
        for i in keys:
            rtn.append(dt[i])

        return rtn

    def travel(self, node, dt, level):
        if node:
            dt[level].append(node.val)

            if node.left:
                self.travel(node.left, dt, level + 1)

            if node.right:
                self.travel(node.right, dt, level + 1)
