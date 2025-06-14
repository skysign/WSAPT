from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_min_sub_array_len(self):
        sln = Solution()
        self.assertEqual(2, sln.minSubArrayLen(target = 7, nums = [2,3,1,2,4,3]))
