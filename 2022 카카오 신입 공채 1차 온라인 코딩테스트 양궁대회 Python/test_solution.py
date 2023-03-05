# 2022 카카오 신입 공채 1차 온라인 코딩테스트 양궁대회 Python
#
# 유튜브 문제 풀이: https://youtu.be/T-hETKL42SU
#
# 파이썬소스: http://bit.ly/3EVUfgY
#
# 문제링크: http://bit.ly/3IOWofS

from unittest import TestCase
from solution import solution, get_score, more_lower_score


class Test(TestCase):
    def test_solution1(self):
        self.assertEqual([0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0],
                         solution(5, [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]))

    def test_solution2(self):
        self.assertEqual([-1],
                         solution(1, [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]))

    def test_solution3(self):
        self.assertEqual([1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0],
                         solution(9, [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1]))

    def test_solution4(self):
        self.assertEqual([1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2],
                         solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]))

    def test_get_score(self):
        print(get_score(9, [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1], [1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0]))
        print(get_score(10, [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3], [1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2]))
        print(more_lower_score([0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3], [1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2]))
        print(more_lower_score([1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2], [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]))
