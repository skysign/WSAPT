from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_change(self):
        sol = Solution()
        self.assertEqual(4, sol.change(5, [1, 2, 5]))

    def test2_change(self):
        sol = Solution()
        self.assertEqual(0, sol.change(3, [2]))

    def test3_change(self):
        sol = Solution()
        self.assertEqual(1, sol.change(10, [10]))