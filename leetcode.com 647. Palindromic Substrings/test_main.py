from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_count_substrings(self):
        sol = Solution()
        self.assertEqual(3, sol.countSubstrings('abc'))

    def test2_count_substrings(self):
        sol = Solution()
        self.assertEqual(6, sol.countSubstrings('aaa'))
