from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_min_cost_climbing_stairs(self):
        sol = Solution()
        self.assertEqual(15, sol.minCostClimbingStairs([10,15,20]))

    def test2_min_cost_climbing_stairs(self):
        sol = Solution()
        self.assertEqual(6, sol.minCostClimbingStairs([1,100,1,1,1,100,1,1,100,1]))
