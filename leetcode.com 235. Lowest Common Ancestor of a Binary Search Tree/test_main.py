from unittest import TestCase
from main import Solution, TreeNode

class TestSolution(TestCase):
    def test2_lowest_common_ancestor(self):
        sol = Solution()

        n5 = TreeNode(5)
        n3 = TreeNode(3)
        n9 = TreeNode(9)
        n7 = TreeNode(7)
        n4 = TreeNode(4)
        n4.left = n3
        n4.right = n5
        n0 = TreeNode(0)
        n8 = TreeNode(8)
        n8.left = n7
        n8.right = n9
        n2 = TreeNode(2)
        n2.left = n0
        n2.right = n4
        n6 = TreeNode(6)
        n6.left = n2
        n6.right = n8
        root = n6
        p = n2
        q = n4
        self.assertEqual(2, sol.lowestCommonAncestor(root, p, q).val)

    def test3_lowest_common_ancestor(self):
        sol = Solution()
        p = TreeNode(2)
        q = TreeNode(1)
        p.left = q
        self.assertEqual(2, sol.lowestCommonAncestor(p, p, q).val)
