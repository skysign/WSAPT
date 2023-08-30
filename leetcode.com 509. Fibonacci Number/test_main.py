from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_fib(self):
        sol = Solution()
        self.assertEqual(1, sol.fib(2))

    def test2_fib(self):
        sol = Solution()
        self.assertEqual(2, sol.fib(3))

    def test3_fib(self):
        sol = Solution()
        self.assertEqual(3, sol.fib(4))
