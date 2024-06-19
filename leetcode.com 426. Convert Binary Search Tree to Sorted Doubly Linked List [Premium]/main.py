# Definition for a Node.
from typing import Optional, List


class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def __init__(self):
        self.root: Optional[Node] = None
        self.prev: Optional[Node] = None
        self.list: List[Optional[Node]] = []

    def treeToDoublyList(self, root: Optional[Node]) -> Optional[Node]:
        if not root:
            return None

        self.inorder(root)

        for node in self.list:
            self.visit(node)

        return self.root

    def append(self, node: Optional[Node]):
        self.list.append(node)
        print(node.val)

    def visit(self, node: Optional[Node]):
        if not self.root:
            self.root = node
            self.prev = node
            self.prev.left = node
            self.prev.right = node
        else:
            if self.root == self.prev:
                self.prev.right = node
                self.prev.left = node
                node.left = self.prev
                node.right = self.prev
                self.prev = node
            else:
                self.prev.right = node
                node.left = self.prev
                node.right = self.root
                self.root.left = node
                self.prev = node


    def inorder(self, node: Optional[Node]):
        if node.left:
            self.inorder(node.left)

        self.append(node)

        if node.right:
            self.inorder(node.right)