from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_word_break(self):
        sol = Solution()
        self.assertEqual(True, sol.wordBreak("leetcode", ["leet","code"]))

    def test2_word_break(self):
        sol = Solution()
        self.assertEqual(True, sol.wordBreak("applepenapple", ["apple","pen"]))

    def test3_word_break(self):
        sol = Solution()
        self.assertEqual(False, sol.wordBreak("catsandog", ["cats","dog","sand","and","cat"]))
