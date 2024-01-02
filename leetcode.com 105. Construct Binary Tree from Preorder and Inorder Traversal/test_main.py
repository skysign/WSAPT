from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_build_tree(self):
        sol = Solution()
        self.assertEqual(None, sol.buildTree([3,9,20,15,7], [9,3,15,20,7]))
