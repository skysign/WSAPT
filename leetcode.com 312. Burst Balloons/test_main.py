from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_max_coins(self):
        sol = Solution()
        self.assertEqual(167, sol.maxCoins(nums=[3, 1, 5, 8]))

    def test2_max_coins(self):
        sol = Solution()
        self.assertEqual(10, sol.maxCoins(nums=[1, 5]))
