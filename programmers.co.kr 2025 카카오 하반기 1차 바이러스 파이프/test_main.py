from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(6,
                         solution(10, 1, [[1, 2, 1], [1, 3, 1], [1, 4, 3], [1, 5, 2], [5, 6, 1], [5, 7, 1], [2, 8, 3], [2, 9, 2], [9, 10, 1]], 2)
                         )
