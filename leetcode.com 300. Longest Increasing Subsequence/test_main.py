from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_length_of_lis(self):
        solution = Solution()
        self.assertEqual(4, solution.lengthOfLIS([0,1,0,3,2,3]))

    def test2_length_of_lis(self):
        solution = Solution()
        self.assertEqual(1, solution.lengthOfLIS([7,7,7,7,7,7,7]))
