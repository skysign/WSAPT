from unittest import TestCase

from main import solution


class Test(TestCase):
    def test_solution(self):
        self.assertEqual(5,
                         solution([[1, 1, 1], [1, 1, 1], [1, 1, 1]], [1, 0], [1, 2]))
