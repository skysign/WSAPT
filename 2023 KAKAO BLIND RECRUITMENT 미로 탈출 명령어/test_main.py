from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual("dllrl", solution(3, 4, 2, 3, 3, 1, 5))

    def test2_solution(self):
        self.assertEqual("dr", solution(2, 2, 1, 1, 2, 2, 2))

    def test3_solution(self):
        self.assertEqual("impossible", solution(3, 3, 1, 2, 3, 3, 4))
