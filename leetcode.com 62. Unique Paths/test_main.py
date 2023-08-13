from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_unique_paths(self):
        solution = Solution()
        self.assertEqual(28, solution.uniquePaths(3, 7))

    def test2_unique_paths(self):
        solution = Solution()
        self.assertEqual(3, solution.uniquePaths(3, 2))
