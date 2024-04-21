from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution(437674, 3), 3)

    def test2_solution(self):
        self.assertEqual(solution(110011, 10), 2)
