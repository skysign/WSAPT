from unittest import TestCase
from main import Solution, TreeNode

class TestSolution(TestCase):
    def test1_max_path_sum(self):
        sol = Solution()
        root = TreeNode(1,
                        TreeNode(2, None, None),
                        TreeNode(3, None, None))
        self.assertEqual(6, sol.maxPathSum(root))

    def test2_max_path_sum(self):
        sol = Solution()
        root = TreeNode(-10,
                        TreeNode(9, None, None),
                        TreeNode(20,
                                 TreeNode(15, None, None),
                                 TreeNode(7, None, None),
                        )
                )
        self.assertEqual(42, sol.maxPathSum(root))

    def test66_max_path_sum(self):
        sol = Solution()
        root = TreeNode(-3, None, None)
        self.assertEqual(-3, sol.maxPathSum(root))

    def test68_max_path_sum(self):
        sol = Solution()
        root = TreeNode(-1,
                        TreeNode(-2,
                                 TreeNode(-6, None, None),
                                 None),
                        TreeNode(10,
                                 TreeNode(-3, None, None),
                                 TreeNode(-6, None, None),
                        )
                )
        self.assertEqual(10, sol.maxPathSum(root))

    def test72_max_path_sum(self):
        sol = Solution()
        root = TreeNode(1,
                        TreeNode(-2,
                                 TreeNode(1,
                                          TreeNode(-1, None, None),
                                          None
                                 ),
                                 TreeNode(3, None, None)
                        ),
                        TreeNode(-3,
                                 TreeNode(-2, None, None),
                                 None
                        )
                )
        self.assertEqual(3, sol.maxPathSum(root))