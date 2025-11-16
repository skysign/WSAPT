from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_word_break(self):
        sln = Solution()
        self.assertEqual(True, sln.wordBreak(s = "leetcode", wordDict = ["leet","code"]))

    def test2_word_break(self):
        sln = Solution()
        self.assertEqual(True, sln.wordBreak(s = "applepenapple", wordDict = ["apple","pen"]))
