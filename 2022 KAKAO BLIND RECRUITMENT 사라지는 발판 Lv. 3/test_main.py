from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution([[1, 1, 1], [1, 1, 1], [1, 1, 1]], [1, 0], [1, 2]), 5)

    def test2_solution(self):
        self.assertEqual(solution([[1, 1, 1], [1, 0, 1], [1, 1, 1]], [1, 0], [1, 2]), 4)

    def test3_solution(self):
        self.assertEqual(solution([[1, 1, 1, 1, 1]], [0, 0], [0, 4]), 4)

    def test4_solution(self):
        self.assertEqual(solution([[1]], [0, 0], [0, 0]), 0)
