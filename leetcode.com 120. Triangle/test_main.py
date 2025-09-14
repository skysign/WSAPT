from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_minimum_total_case_1(self):
        sln = Solution()
        self.assertEqual(11,
                         sln.minimumTotal(
                             [[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]
                         ))
