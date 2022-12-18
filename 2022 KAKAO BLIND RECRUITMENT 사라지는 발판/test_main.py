from unittest import TestCase

from main import solution


class Test(TestCase):
    def test_solution(self):
        self.assertEqual(5,
                         solution([[1, 1, 1], [1, 1, 1], [1, 1, 1]], [1, 0], [1, 2]))

    def test_solution2(self):
        self.assertEqual(4,
                         solution([[1, 1, 1], [1, 0, 1], [1, 1, 1]], [1, 0], [1, 2]))

    def test_solution3(self):
        self.assertEqual(4,
                         solution([[1, 1, 1, 1, 1]], [0, 0], [0, 4]))

    def test_solution4(self):
        self.assertEqual(0,
                         solution([[1]], [0, 0], [0, 0]))
