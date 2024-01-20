from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1(self):
        self.assertEqual(5, solution(4, [3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4]))

    def test2(self):
        self.assertEqual(2, solution(3, [1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12]))

    def test3(self):
        self.assertEqual(4, solution(2, [5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7]))

    def test4(self):
        self.assertEqual(1, solution(10, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]))
