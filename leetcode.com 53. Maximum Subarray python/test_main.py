from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_max_sub_array(self):
        sol = Solution()
        self.assertEqual(6, sol.maxSubArray([-2,1,-3,4,-1,2,1,-5,4]))

    def test2_max_sub_array(self):
        sol = Solution()
        self.assertEqual(1, sol.maxSubArray([1]))

    def test3_max_sub_array(self):
        sol = Solution()
        self.assertEqual(23, sol.maxSubArray([5,4,-1,7,8]))