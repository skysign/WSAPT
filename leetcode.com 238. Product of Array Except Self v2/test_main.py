from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_product_except_self(self):
        sln = Solution()
        self.assertEqual([24,12,8,6],
                         sln.productExceptSelf([1,2,3,4]))
