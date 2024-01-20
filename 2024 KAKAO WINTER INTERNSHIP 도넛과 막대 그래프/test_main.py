from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1(self):
        self.assertEqual([2, 1, 1, 0], solution([[2, 3], [4, 3], [1, 1], [2, 1]]))

    def test2(self):
        self.assertEqual([4, 0, 1, 2], solution([[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]))

    # https://school.programmers.co.kr/questions/65444
    def test_x1(self):
        self.assertEqual([2, 1, 1, 0], solution([[4,3], [2, 3], [1, 1], [2, 1]]))

    # https://school.programmers.co.kr/questions/70658
    def test_x2(self):
        self.assertEqual([2, 0, 1, 1], solution([[2, 1], [2, 5], [3, 4], [4, 5], [5, 6], [6, 7], [7, 3], [3, 8], [8, 9], [9, 10], [10, 11], [11, 3]]))
    def test_x3(self):
        self.assertEqual([2, 0, 3, 0], solution([[2, 1], [1, 3], [2, 4], [4, 5], [2, 6], [6, 7]]))