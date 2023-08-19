from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_is_interleave(self):
        sol = Solution()
        self.assertEqual(True, sol.isInterleave("aabcc", "dbbca", "aadbbcbcac"))

    def test2_is_interleave(self):
        sol = Solution()
        self.assertEqual(False, sol.isInterleave("aabcc", "dbbca", "aadbbbaccc"))

    def test3_is_interleave(self):
        sol = Solution()
        self.assertEqual(True, sol.isInterleave("", "", ""))

    def test76_is_interleave(self):
        sol = Solution()
        self.assertEqual(True, sol.isInterleave("a", "", "a"))

    def test80_is_interleave(self):
        sol = Solution()
        self.assertEqual(True, sol.isInterleave("aa", "ab", "abaa"))
