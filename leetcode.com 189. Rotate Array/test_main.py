from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_rotate(self):
        sln = Solution()
        nums = [1, 2, 3, 4, 5, 6, 7]
        sln.rotate(nums, 3)
        self.assertEqual([5,6,7,1,2,3,4], nums)
