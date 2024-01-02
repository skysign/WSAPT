from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        self.preorder = None


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        self.preorder = preorder
        val = self.preorder.pop(0)
        idx = inorder.index(val)

        inorder_left = inorder[:idx]
        inorder_right = inorder[idx + 1:]

        return TreeNode(val,
                        self.build_node(inorder_left),
                        self.build_node(inorder_right))

    def build_node(self, inorder: List[int]) -> Optional[TreeNode]:
        if len(inorder) == 0:
            return None

        val = self.preorder.pop(0)
        idx = inorder.index(val)

        inorder_left = inorder[:idx]
        inorder_right = inorder[idx + 1:]
        node = TreeNode(val,
                        self.build_node(inorder_left),
                        self.build_node(inorder_right))

        return node
