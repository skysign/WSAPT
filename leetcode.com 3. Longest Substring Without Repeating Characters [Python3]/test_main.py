from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test_length_of_longest_substring(self):
        sol = Solution()
        self.assertEqual(3, sol.lengthOfLongestSubstring('pwwkew'))
