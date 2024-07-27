from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(4, solution(5, 12))

    def test2_solution(self):
        self.assertEqual(3, solution(2, 11))

    def testa_solution(self):
        self.assertEqual(-1, solution(5, 31168))

    # https://school.programmers.co.kr/questions/30926
    # 4, 31
    # (4 + 4) * 4 - (4 / 4)
    def test_a1_solution(self):
        self.assertEqual(5, solution(4, 31))

    # https://school.programmers.co.kr/questions/56476
    def test_b1_solution(self):
        self.assertEqual(4, solution(1, 121))

    def test_b2_solution(self):
        self.assertEqual(4, solution(5, 3025))

    def test_b3_solution(self):
        self.assertEqual(5, solution(5, 3125))

    # def test_b4_solution(self):
    #     self.assertEqual(2, solution(9, 0))

    # https://school.programmers.co.kr/questions/43922
    def test_c1_solution(self):
        self.assertEqual(3, solution(6, 5))