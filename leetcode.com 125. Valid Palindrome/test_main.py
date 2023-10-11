from unittest import TestCase
from main import Solution

# leetcode.com 125. Valid Palindrome
#
# 유튜브 문제 풀이: https://youtu.be/vfebH2GbEdA?si=UqUf0BohwrIPMLwI
#
# 파이썬 소스: https://bit.ly/46hux2j
#
# 문제 링크: https://bit.ly/3rMr6l8

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
