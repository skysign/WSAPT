from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_change(self):
        sol = Solution()
        self.assertEqual(4, sol.change(amount=5, coins=[1, 2, 5]))

    def test2_change(self):
        sol = Solution()
        self.assertEqual(0, sol.change(amount=3, coins=[2]))

    def test3_change(self):
        sol = Solution()
        self.assertEqual(1, sol.change(amount=10, coins=[10]))

    def test25_change(self):
        sol = Solution()
        self.assertEqual(1, sol.change(amount=0, coins=[7]))
