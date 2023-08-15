from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(3, sol.longestCommonSubsequence('abcde', 'ace'))

    def test2_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(3, sol.longestCommonSubsequence('abc', 'abc'))

    def test3_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(0, sol.longestCommonSubsequence('abc', 'def'))

    def test9_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(1, sol.longestCommonSubsequence('bl', 'yby'))

    def test11_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(2, sol.longestCommonSubsequence('ezupkr', 'ubmrapg'))

    def test18_longest_common_subsequence(self):
        sol = Solution()
        self.assertEqual(5, sol.longestCommonSubsequence('hofubmnylkra', 'pqhgxgdofcvmr'))