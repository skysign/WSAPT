from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            'OOOOXOOO',
            solution(
                8, 2,
                ["D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"]
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

    def test3_solution(self):
        self.assertEqual(
            9,
            solution(4, [6, 9, 7, 5], [[-1, -1], [-1, -1], [-1, 0], [2, 1]])
        )

