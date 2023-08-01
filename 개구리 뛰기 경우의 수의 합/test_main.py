from unittest import TestCase
from main import solution_original, solution_sum

class Test(TestCase):
    # 답: 5
    # 8
    # 1 2 5 7 9 10 12 15
    # 4
    def test1_solution_original(self):
        self.assertEqual(5, solution_original(8, '1 2 5 7 9 10 12 15', 4))

    # 답: -1
    # 8
    # 1 2 5 7 9 10 12 15
    # 2
    def test2_solution_original(self):
        self.assertEqual(-1, solution_original(8, '1 2 5 7 9 10 12 15', 2))

    # 답: 8
    # 17
    # 3 4 8 10 14 18 20 22 23 25 30 33 34 36 38 39 42
    # 7
    def test3_solution_original(self):
        self.assertEqual(8, solution_original(17, '3 4 8 10 14 18 20 22 23 25 30 33 34 36 38 39 42', 7))


    # 답 3
    # 3
    # 1 2 3
    # 2
    def test1_solution_sum(self):
        self.assertEqual(3, solution_sum(3, '1 2 3', 2))