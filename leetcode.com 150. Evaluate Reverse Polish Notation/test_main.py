from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_eval_rpn(self):
        sol = Solution()
        self.assertEqual(9, sol.evalRPN(["2","1","+","3","*"]))

    def test2_eval_rpn(self):
        sol = Solution()
        self.assertEqual(6, sol.evalRPN(["4","13","5","/","+"]))
