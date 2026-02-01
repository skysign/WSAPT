from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        return self.convert(nums, 0, len(nums) - 1)

    def convert(self, nums, idx_left, idx_right):
        if idx_left > idx_right:
            return None

        idx = (idx_left + idx_right) // 2
        node = TreeNode(nums[idx])

        node.left = self.convert(nums, idx_left, idx - 1)
        node.right = self.convert(nums, idx + 1, idx_right)

        return node
