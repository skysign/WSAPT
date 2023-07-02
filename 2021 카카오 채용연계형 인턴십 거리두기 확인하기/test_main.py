# 2021 카카오 채용연계형 인턴십 거리두기 확인하기 Python
#
# 유튜브 문제 풀이: https://youtu.be/CSmCku0lDCQ
#
# 파이썬 소스: https://bit.ly/3PBrxZ9
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/81302#fn1

from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            [1, 0, 1, 1, 1],
            solution(
                [["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"], ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]
            )
        )
