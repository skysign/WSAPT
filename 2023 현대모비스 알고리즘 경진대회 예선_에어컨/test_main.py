from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(40, solution(28, 18, 26, 10, 8, [0, 0, 1, 1, 1, 1, 1]))

    def test2_solution(self):
        self.assertEqual(25, solution(-10, -5, 5, 5, 1, [0, 0, 0, 0, 0, 1, 0]))

    def test3_solution(self):
        self.assertEqual(20, solution(11, 8, 10, 10, 1, [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]))

    def test4_solution(self):
        self.assertEqual(60, solution(11, 8, 10, 10, 100, [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]))
