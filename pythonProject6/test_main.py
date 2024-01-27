from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1(self):
        self.assertEqual(149, solution(4, [1, 1, 0, 1]))

    def test2(self):
        self.assertEqual(11, solution(2, [0, 1]))

    def test3(self):
        self.assertEqual(7704, solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]))
