from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_coin_change(self):
        sol = Solution()
        self.assertEqual(3, sol.coinChange([1,2,5], 11))

    def test1_coin_change(self):
        sol = Solution()
        self.assertEqual(-1, sol.coinChange([2], 3))

    def test1_coin_change(self):
        sol = Solution()
        self.assertEqual(0, sol.coinChange([1], 0))

