from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def __init__(self):
        self.count_good = 0

    def goodNodes(self, root: TreeNode) -> int:
        self.travel_inorder(root, -1 * 10 ** 4 -1)
        return self.count_good

    def travel_inorder(self, node: Optional[TreeNode], min:int):
        if not node:
            return

        if min <= node.val:
            self.count_good += 1
            min = node.val

        self.travel_inorder(node.left, min)
        self.travel_inorder(node.right, min)