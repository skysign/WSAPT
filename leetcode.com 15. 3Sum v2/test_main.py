from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test_three_sum(self):
        sln = Solution()
        self.assertEqual(sln.threeSum([-1,0,1,2,-1,-4]), [[-1,0,1], [-1,-1,2]])

    def test_three_sum2(self):
        sln = Solution()
        self.assertEqual(sln.threeSum([0, 1, 1]), [])

    def test_three_sum3(self):
        sln = Solution()
        self.assertEqual(sln.threeSum([0, 0, 0]), [[0, 0, 0]])

    def test_three_sum4(self):
        sln = Solution()
        self.assertEqual(sln.threeSum([1,-1,-1,0]), [[-1, 0, 1]])
