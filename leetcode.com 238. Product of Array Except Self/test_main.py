from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_product_except_self(self):
        sol = Solution()
        self.assertEqual([24,12,8,6], sol.productExceptSelf([1,2,3,4]))
