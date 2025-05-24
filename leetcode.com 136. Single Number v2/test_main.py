from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test2_single_number(self):
        sln = Solution()
        self.assertEqual(4, sln.singleNumber(nums = [4,1,2,1,2]))
