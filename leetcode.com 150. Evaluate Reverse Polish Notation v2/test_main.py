from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test3_eval_rpn(self):
        sln = Solution()
        self.assertEqual(22, sln.evalRPN(["10","6","9","3","+","-11","*","/","*","17","+","5","+"]))
