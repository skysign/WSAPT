from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_combination_sum(self):
        sol = Solution()
        self.assertEqual([[2,2,3],[7]], sol.combinationSum([2,3,6,7], 7))
