from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


# 테케로 꼭 확인해 봐야 되는 거, 203번 테스트 케이스
# 1, 2, 2, 3, n, n, 3, 4, n, n, 4

class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        if root == None:
            return True
        l, b = self.travel_in_order(root, 0)

        return b

    def travel_in_order(self, node: Optional[TreeNode], depth):
        dl, dr = depth, depth
        bl, br = True, True

        if node.left:
            dl, bl = self.travel_in_order(node.left, depth +1)

        if node.right:
            dr, br = self.travel_in_order(node.right, depth +1)

        if bl == False or br == False:
            return max(dl, dr), False

        return max(dl, dr), (max(dl, dr) - min(dl, dr) <= 1)