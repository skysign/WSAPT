from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_longest_consecutive(self):
        sol = Solution()
        self.assertEqual(4, sol.longestConsecutive([100,4,200,1,3,2]))
