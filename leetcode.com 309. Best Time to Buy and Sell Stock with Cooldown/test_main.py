from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_max_profit(self):
        sol = Solution()
        self.assertEqual(3, sol.maxProfit([1,2,3,0,2]))

    def test2_max_profit(self):
        sol = Solution()
        self.assertEqual(0, sol.maxProfit([1]))

    def test123_max_profit(self):
        sol = Solution()
        self.assertEqual(3, sol.maxProfit([1, 2, 4]))