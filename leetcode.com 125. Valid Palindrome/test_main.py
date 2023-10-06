from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_is_palindrome(self):
        sol = Solution()
        self.assertEqual(True, sol.isPalindrome("A man, a plan, a canal: Panama"))

    def test2_is_palindrome(self):
        sol = Solution()
        self.assertEqual(False, sol.isPalindrome("race a car"))

    def test3_is_palindrome(self):
        sol = Solution()
        self.assertEqual(True, sol.isPalindrome(" "))
