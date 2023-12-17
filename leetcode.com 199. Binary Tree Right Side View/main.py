from typing import Optional, List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        vals = []
        self.travel_inorder(root, 1, vals)

        return vals

    def travel_inorder(self, node: Optional[TreeNode], level: int, vals: List[int]):
        if not node:
            return vals

        if len(vals) < level:
            vals.append(node.val)
        else:
            vals[level -1] = node.val

        self.travel_inorder(node.left, level +1, vals)
        self.travel_inorder(node.right, level +1, vals)