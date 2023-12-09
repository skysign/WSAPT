from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        return self.travel_in_order(root, subRoot)


    def travel_in_order(self, tree: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        r1 = False
        r2 = False
        rx = False

        if tree.left:
            r1 = self.travel_in_order(tree.left, subRoot)

        if self.match(tree, subRoot):
            rx = True
        else:
            rx = False

        if tree.right:
            r2 = self.travel_in_order(tree.right, subRoot)

        return r1 or rx or r2

    def match(self, tree: Optional[TreeNode], subTree: Optional[TreeNode]):
        if tree == None and subTree == None:
            return True

        if (tree == None and subTree != None)\
                or (tree != None and subTree == None):
            return False

        if (tree and subTree) and (tree.val == subTree.val):
            rx = True
        else:
            return False

        rl = self.match(tree.left, subTree.left)
        rr = self.match(tree.right, subTree.right)

        return rx and rl and rr