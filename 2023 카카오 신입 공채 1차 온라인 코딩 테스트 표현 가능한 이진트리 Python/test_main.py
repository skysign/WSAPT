from unittest import TestCase
from main import solution

# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 표현 가능한 이진트리 Python
#
# 유튜브 문제 풀이: https://youtu.be/XExcM0Gqbd4
#
# 파이썬 소스: https://bit.ly/3KNLBUe
#
# 문제 링크: https://bit.ly/41yCaOT

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(
            [1, 1, 0],
            solution(
                [7, 42, 5]
            )
        )

    def test2_solution(self):
        self.assertEqual(
            [1, 1, 0],
            solution(
                [63, 111, 95]
            )
        )
