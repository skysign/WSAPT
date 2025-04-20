from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_longest_palindrome(self):
        sln = Solution()
        self.assertEqual('bab', sln.longestPalindrome('babad'))
