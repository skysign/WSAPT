from typing import Optional, List, Dict

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []

        dict = {}
        self.travel_in_order(root, 0, dict)
        return [dict[key] for key in sorted(dict.keys())]


    def travel_in_order(self, node: Optional[TreeNode], level: int, dict: Dict):
        if not level in dict.keys():
            dict[level] = []

        dict[level].append(node.val)

        if node.left:
            self.travel_in_order(node.left, level +1, dict)

        if node.right:
            self.travel_in_order(node.right, level +1, dict)
