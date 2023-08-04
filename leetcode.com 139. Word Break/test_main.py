from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_word_break(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.wordBreak(
                'leetcode',
                ['leet', 'code']
            )
        )

    def test2_word_break(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.wordBreak(
                'applepenapple',
                ['apple', 'pen']
            )
        )

    def test6_word_break(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.wordBreak(
                'bb',
                ['a', 'b', 'bbb', 'bbbb']
            )
        )

    def test9_word_break(self):
        solution = Solution()
        self.assertEqual(
            True,
            solution.wordBreak(
                'cars',
                ['car', 'ca', 'rs']
            )
        )

    def test2x_word_break(self):
        solution = Solution()
        self.assertEqual(
            False,
            solution.wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
            )
        )
