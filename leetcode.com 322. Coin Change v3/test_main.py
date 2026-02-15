from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_coin_change(self):
        sln = Solution()
        self.assertEqual(3, sln.coinChange([1, 2, 5], 11))
