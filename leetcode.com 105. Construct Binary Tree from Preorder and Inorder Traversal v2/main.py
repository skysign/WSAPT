from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if preorder and inorder:
            val = preorder.pop(0)
            node = TreeNode(val)
            node.left = self.buildTree(preorder, inorder[:inorder.index(val)])
            node.right = self.buildTree(preorder, inorder[inorder.index(val) + 1:])

            return node

        return None
