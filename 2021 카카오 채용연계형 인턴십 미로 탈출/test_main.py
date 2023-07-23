from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            'OOOOXOOO',
            solution(
                8, 2,
                ["D 2","C","U 3","C","D 4","C","U 2","Z","Z"]
            )
        )

    def test1_solution(self):
        self.assertEqual(
            5,
            solution(
                3, 1, 3, [[1, 2, 2], [3, 2, 3]], [2]
            )
        )

    def test2_solution(self):
        self.assertEqual(
            4,
            solution(
                4, 1, 4, [[1, 2, 1], [3, 2, 1], [2, 4, 1]], [2, 3]
            )
        )

    def test3_solution(self):
        self.assertEqual(
            5,
            solution(
                5, 1, 5, [[1, 2, 1], [2, 3, 1], [3, 2, 1], [3, 5, 1], [1, 5, 10]], [2, 3]
            )
        )

    def test3_solution(self):
        self.assertEqual(
            9,
            solution(
                4, 1, 4, [[1, 2, 1], [2, 3, 3], [3, 2, 2], [2, 4, 4], [1, 4, 10]], [2, 3]
            )
        )