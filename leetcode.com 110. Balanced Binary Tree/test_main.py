from unittest import TestCase
from main import Solution, TreeNode

class TestSolution(TestCase):
    def testx_is_balanced(self):
        node3 = TreeNode(3, None, None)
        node2 = TreeNode(2, None, node3)
        node1 = TreeNode(1, None, node2)
        sol = Solution()
        self.assertEqual(False, sol.isBalanced(node1))

    def test203_is_balanced(self):
        node4r = TreeNode(4, None, None)
        node4l = TreeNode(4, None, None)
        node3r = TreeNode(3, None, node4r)
        node3l = TreeNode(3, node4l, None)
        node2r = TreeNode(2, None, node3r)
        node2l = TreeNode(2, node3l, None)
        node1 = TreeNode(1, node2l, node2r)
        sol = Solution()
        self.assertEqual(False, sol.isBalanced(node1))
