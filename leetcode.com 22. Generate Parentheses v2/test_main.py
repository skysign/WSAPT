from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_generate_parenthesis(self):
        sln = Solution()
        self.assertEqual(
            ["((()))", "(()())", "(())()", "()(())", "()()()"],
            sln.generateParenthesis(3)
        )

    def test_n_2_generate_parenthesis(self):
        sln = Solution()
        self.assertEqual(
            ['()()', '(())'],
            sln.generateParenthesis(2)
        )

    def test2_generate_parenthesis(self):
        sln = Solution()
        self.assertEqual(
            ["()"],
            sln.generateParenthesis(1)
        )
