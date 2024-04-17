from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution(4, 5, [1, 0, 3, 1, 2], [0, 3, 0, 4, 0]), 16)

    def test2_solution(self):
        self.assertEqual(solution(2, 7, [1, 0, 2, 0, 1, 0, 2], [0, 2, 0, 1, 0, 2, 0]), 30)
