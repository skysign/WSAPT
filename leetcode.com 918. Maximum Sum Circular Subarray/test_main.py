from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_max_subarray_sum_circular(self):
        sln = Solution()
        self.assertEqual(3, sln.maxSubarraySumCircular(
            nums=[1, -2, 3, -2]
        ))

    def test2_max_subarray_sum_circular(self):
        sln = Solution()
        self.assertEqual(10, sln.maxSubarraySumCircular(
            nums=[5, -3, 5]
        ))

    def test3_max_subarray_sum_circular(self):
        sln = Solution()
        self.assertEqual(-2, sln.maxSubarraySumCircular(
            nums=[-3, -2, -3]
        ))

    def test39_max_subarray_sum_circular(self):
        sln = Solution()
        self.assertEqual(4, sln.maxSubarraySumCircular(
            [3,-1,2,-1]
        ))
