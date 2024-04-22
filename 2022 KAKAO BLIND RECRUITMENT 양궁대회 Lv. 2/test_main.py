from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution(5, [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]), [0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0])

    def test2_solution(self):
        self.assertEqual(solution(1, [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]), [-1])

    def test3_solution(self):
        self.assertEqual(solution(9, [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1]), [1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0])

    def test4_solution(self):
        self.assertEqual(solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]), [1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2])
