from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_is_interleave(self):
        sol = Solution()
        self.assertEqual(True, sol.isInterleave(s1="aabcc", s2="dbbca", s3="aadbbcbcac"))

    def test2_is_interleave(self):
        sol = Solution()
        self.assertEqual(False, sol.isInterleave(s1="aabcc", s2="dbbca", s3="aadbbbaccc"))

    def test3_is_interleave(self):
        sol = Solution()
        self.assertEqual(True, sol.isInterleave(s1="", s2="", s3=""))

    def test9_is_interleave(self):
        sol = Solution()
        self.assertEqual(False, sol.isInterleave(s1="a", s2="b", s3="a"))
