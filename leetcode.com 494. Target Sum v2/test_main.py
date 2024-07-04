from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(5, sol.findTargetSumWays(nums=[1, 1, 1, 1, 1], target=3))

    def test2_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(1, sol.findTargetSumWays(nums=[1], target=1))

    def test36_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(2, sol.findTargetSumWays(nums=[1, 0], target=1))

    def test37_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(256, sol.findTargetSumWays(nums=[0, 0, 0, 0, 0, 0, 0, 0, 1], target=1))

    def test41_find_target_sum_ways(self):
        sol = Solution()
        self.assertEqual(2, sol.findTargetSumWays(nums=[1, 2, 1], target=0))
