from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test_max_profit1(self):
        sln = Solution()
        self.assertEqual(sln.maxProfit([7,1,5,3,6,4]), 5)

    def test_max_profit2(self):
        sln = Solution()
        self.assertEqual(sln.maxProfit([7,6,4,3,1]), 0)
