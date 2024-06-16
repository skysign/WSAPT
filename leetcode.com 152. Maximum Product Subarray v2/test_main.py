from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_max_product(self):
        sol = Solution()
        self.assertEqual(6, sol.maxProduct([2, 3, -2, 4]))

    def test2_max_product(self):
        sol = Solution()
        self.assertEqual(0, sol.maxProduct([-2, 0, -1]))
