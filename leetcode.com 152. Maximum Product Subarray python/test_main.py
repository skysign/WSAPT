from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_max_product(self):
        solution = Solution()
        self.assertEqual(6, solution.maxProduct([2, 3, -2, 4]))

    def test2_max_product(self):
        solution = Solution()
        self.assertEqual(0, solution.maxProduct([-2, 0, -1]))

    def test103_max_product(self):
        solution = Solution()
        self.assertEqual(0, solution.maxProduct([-2, 0,-1]))

    def test109_max_product(self):
        solution = Solution()
        self.assertEqual(24, solution.maxProduct([-2, 3, -4]))

    def test115_max_product(self):
        solution = Solution()
        self.assertEqual(2, solution.maxProduct([0, 2]))

    def test115_max_product(self):
        solution = Solution()
        self.assertEqual(24, solution.maxProduct([-2, 3, -4]))

    def test150_max_product(self):
        solution = Solution()
        self.assertEqual(24, solution.maxProduct([2, -5, -2, -4, 3]))

    def test160_max_product(self):
        solution = Solution()
        self.assertEqual(60, solution.maxProduct([1, 0, -1, 2, 3, -5, -2]))

    def test177_max_product(self):
        solution = Solution()
        self.assertEqual(-2, solution.maxProduct([-2]))

    def test184_max_product(self):
        solution = Solution()
        self.assertEqual(0, solution.maxProduct([-2,0,-1]))

    def test178_max_product(self):
        solution = Solution()
        self.assertEqual(1, solution.maxProduct([-3, 0, 1, -2]))

    def test186_max_product(self):
        solution = Solution()
        self.assertEqual(6, solution.maxProduct([-1, -2, -3, 0]))

    def test187_max_product(self):
        solution = Solution()
        self.assertEqual(36, solution.maxProduct([1,-3,2,0,-1,0,-2,-3,1,2,-3,2]))