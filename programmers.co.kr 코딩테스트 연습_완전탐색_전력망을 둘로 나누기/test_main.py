from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(3, solution(9, [[1, 3], [2, 3], [3, 4], [4, 5], [4, 6], [4, 7], [7, 8], [7, 9]]))

    def test2_solution(self):
        self.assertEqual(0, solution(4, [[1, 2], [2, 3], [3, 4]]))

    def test3_solution(self):
        self.assertEqual(1, solution(7, [[1, 2], [2, 7], [3, 7], [3, 4], [4, 5], [6, 7]]))
