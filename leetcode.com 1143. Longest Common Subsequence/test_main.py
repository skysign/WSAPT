from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(3, sol.longestCommonSubsequence(text1 = "abcde", text2 = "ace"))

    def test2_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(3, sol.longestCommonSubsequence(text1 = "abc", text2 = "abc"))

    def test3_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(0, sol.longestCommonSubsequence(text1 = "abc", text2 = "def"))

    def test16_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(1, sol.longestCommonSubsequence(text1 = "bsbininm", text2 = "jmjkbkjkv"))
