from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(5, sol.findTargetSumWays([1,1,1,1,1], 3))

    def test2_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(1, sol.findTargetSumWays([1], 1))

    def test134_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(2, sol.findTargetSumWays([1, 0], 1))