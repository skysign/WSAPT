from unittest import TestCase
from main import Solution, TreeNode

class TestSolution(TestCase):
    def test2_is_subtree(self):
        sol = Solution()

        n0 = TreeNode(0)
        n2 = TreeNode(2, n0, None)
        n1 = TreeNode(1)
        n4 = TreeNode(4, n1, n2)
        n5 = TreeNode(5)
        root = TreeNode(3, n4, n5)

        sn2 = TreeNode(2)
        sn1 = TreeNode(1)
        sn4 = TreeNode(4, sn1, sn2)
        self.assertEqual(False, sol.isSubtree(root, sn4))
