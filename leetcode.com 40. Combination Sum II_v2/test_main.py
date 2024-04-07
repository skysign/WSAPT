from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_combination_sum2(self):
        sol = Solution()
        self.assertEqual(sol.combinationSum2([10, 1, 2, 7, 6, 1, 5], 8),
                         [
                             [1, 1, 6],
                             [1, 2, 5],
                             [1, 7],
                             [2, 6]
                         ])
