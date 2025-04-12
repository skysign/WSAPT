from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_is_palindrome(self):
        sln = Solution()
        self.assertEqual(True, sln.isPalindrome(121))

    def test2_is_palindrome(self):
        sln = Solution()
        self.assertEqual(False, sln.isPalindrome(-121))

    def test3_is_palindrome(self):
        sln = Solution()
        self.assertEqual(False, sln.isPalindrome(10))
