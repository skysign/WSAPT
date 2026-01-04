from typing import Optional, List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class BSTIterator:
    def __init__(self, root: Optional[TreeNode]):
        self.dt = []
        self.idx = 0
        self.inorder(root)

    def inorder(self, node: Optional[TreeNode]):
        if node.left:
            self.inorder(node.left)

        self.dt.append(node.val)

        if node.right:
            self.inorder(node.right)

    def next(self) -> int:
        i = self.idx
        self.idx += 1

        return self.dt[i]

    def hasNext(self) -> bool:
        return self.idx < len(self.dt)

# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()
