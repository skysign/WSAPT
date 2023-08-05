from unittest import TestCase
# from main import solution
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            40,
            solution(
                3,
                [12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1]
            )
        )

    def test2_solution(self):
        self.assertEqual(
            27,
            solution(1, [6, 9, 7, 5], [[-1, -1], [-1, -1], [-1, 0], [2, 1]])
        )

    def test3_solution(self):
        self.assertEqual(
            14,
            solution(2, [6, 9, 7, 5], [[-1, -1], [-1, -1], [-1, 0], [2, 1]])
        )

    def test4_solution(self):
        self.assertEqual(
            9,
            solution(4, [6, 9, 7, 5], [[-1, -1], [-1, -1], [-1, 0], [2, 1]])
        )

