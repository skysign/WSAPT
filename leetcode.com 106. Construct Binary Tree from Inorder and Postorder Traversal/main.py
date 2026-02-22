from typing import List, Optional

# 정답 판정을 받으려면, TreeNode 클래스를, 주석처리한 뒤에 소스코드를 submit 하세요.
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        root_val = postorder.pop()
        idx = inorder.index(root_val)
        inorder_left = inorder[:idx]
        inorder_right = inorder[idx+1:]

        root = TreeNode(root_val)

        def travel(node, postorder, inorder_left, inorder_right):
            if postorder and inorder_right:
                right_val = postorder.pop()
                node.right = TreeNode(right_val)
                right_idx = inorder_right.index(right_val)
                travel(node.right, postorder, inorder_right[:right_idx], inorder_right[right_idx+1:])

            if postorder and inorder_left:
                left_val = postorder.pop()
                node.left = TreeNode(left_val)
                left_idx = inorder_left.index(left_val)
                travel(node.left, postorder, inorder_left[:left_idx], inorder_left[left_idx + 1:])


        travel(root, postorder, inorder_left, inorder_right)

        return root