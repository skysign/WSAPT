from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_climb_stairs(self):
        sol = Solution()
        self.assertEqual(2, sol.climbStairs(2))

    def test2_climb_stairs(self):
        sol = Solution()
        self.assertEqual(3, sol.climbStairs(3))
