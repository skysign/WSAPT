# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 1,2,3 떨어트리기 Python
#
# 유튜브 문제 풀이: https://youtu.be/2qgW5HFwt_g
#
# 파이썬 소스: https://bit.ly/3NcyNaY
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/150364
from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            [1, 1, 2, 2, 2, 3, 3],
            solution(
                [[2, 4], [1, 2], [6, 8], [1, 3], [5, 7], [2, 5], [3, 6], [6, 10], [6, 9]],
                [0, 0, 0, 3, 0, 0, 5, 1, 2, 3]
            )
        )

    def test2_solution(self):
        self.assertEqual(
            [1, 1, 3, 2, 3],
            solution(
                [[1, 2], [1, 3]],
                [0, 7, 3]
            )
        )

    def test3_solution(self):
        self.assertEqual(
            [-1],
            solution(
                [[1, 3], [1, 2]],
                [0, 7, 1])
        )


