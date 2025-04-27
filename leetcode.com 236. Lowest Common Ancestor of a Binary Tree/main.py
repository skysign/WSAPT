# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        targets = [p, q]

        if not root:
            return None

        if root in targets:
            return root

        found_left = self.lca(root.left, targets)
        found_right = self.lca(root.right, targets)

        if found_left and found_right:
            return root

        if found_left:
            return found_left

        return found_right

    def lca(self, node, targets):
        if not node:
            return None

        if node in targets:
            return node

        found_left = self.lca(node.left, targets)
        found_right = self.lca(node.right, targets)

        if found_left and found_right:
            return node

        if found_left:
            return found_left

        return found_right