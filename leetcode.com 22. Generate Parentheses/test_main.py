from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_generate_parenthesis(self):
        sol = Solution()
        self.assertEqual(set(["((()))","(()())","(())()","()(())","()()()"]),
                         set(sol.generateParenthesis(3)))
