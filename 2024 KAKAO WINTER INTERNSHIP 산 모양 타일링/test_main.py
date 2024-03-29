from unittest import TestCase
from main import solution

# 2024 KAKAO WINTER INTERNSHIP 산 모양 타일링 Python
#
# 유튜브 문제 풀이: https://youtu.be/Rac9bK930IA
#
# 파이썬 소스: https://bit.ly/48NJf2p
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/258705

class Test(TestCase):
    def test1(self):
        self.assertEqual(149, solution(4, [1, 1, 0, 1]))

    def test2(self):
        self.assertEqual(11, solution(2, [0, 1]))

    def test3(self):
        self.assertEqual(7704, solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]))

    def test_my2(self):
        self.assertEqual(8, solution(2, [0, 0]))
