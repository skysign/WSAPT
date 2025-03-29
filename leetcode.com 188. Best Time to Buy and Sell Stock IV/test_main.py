from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test_max_profit1(self):
        sln = Solution()
        self.assertEqual(sln.maxProfit([2,4,1]), 2)
