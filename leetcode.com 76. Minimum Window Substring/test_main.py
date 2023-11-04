from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_min_window(self):
        sol = Solution()
        self.assertEqual('BANC', sol.minWindow('ADOBECODEBANC', 'ABC'))
