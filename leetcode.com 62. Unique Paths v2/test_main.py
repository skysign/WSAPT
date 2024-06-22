from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_unique_paths(self):
        sol = Solution()
        self.assertEqual(28, sol.uniquePaths(m=3, n=7))

    def test2_unique_paths(self):
        sol = Solution()
        self.assertEqual(3, sol.uniquePaths(m=3, n=2))
