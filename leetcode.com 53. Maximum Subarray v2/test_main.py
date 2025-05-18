from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_max_sub_array(self):
        sln = Solution()
        self.assertEqual(6, sln.maxSubArray(
            nums=[-2, 1, -3, 4, -1, 2, 1, -5, 4]
        ))

    def test2_max_sub_array(self):
        sln = Solution()
        self.assertEqual(1, sln.maxSubArray(
            nums=[1]
        ))

    def test3_max_sub_array(self):
        sln = Solution()
        self.assertEqual(23, sln.maxSubArray(
            nums=[5, 4, -1, 7, 8]
        ))
