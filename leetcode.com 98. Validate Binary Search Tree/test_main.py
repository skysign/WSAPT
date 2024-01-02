from unittest import TestCase
from main import Solution, TreeNode

class TestSolution(TestCase):
    def test_is_valid_bst(self):
        sol = Solution()

        rrl = TreeNode(3, None, None)
        rrr = TreeNode(7, None, None)
        rr = TreeNode(6, rrl, rrr)
        rl = TreeNode(4, None, None)
        r = TreeNode(5, rl, rr)

        self.assertEqual(False,
                         sol.isValidBST(r))
