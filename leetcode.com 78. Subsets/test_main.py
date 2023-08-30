from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_subsets(self):
        sol = Solution()
        self.assertEqual([[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]], sol.subsets([1,2,3]))

    def test2_subsets(self):
        sol = Solution()
        self.assertEqual([[],[0]], sol.subsets([0]))
