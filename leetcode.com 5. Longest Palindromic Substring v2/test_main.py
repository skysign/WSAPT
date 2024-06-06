from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_longest_palindrome(self):
        sol = Solution()
        self.assertEqual('bab', sol.longestPalindrome('babad'))

    def test2_longest_palindrome(self):
        sol = Solution()
        self.assertEqual('bb', sol.longestPalindrome('cbbd'))
