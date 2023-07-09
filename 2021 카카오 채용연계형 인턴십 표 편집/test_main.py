# 2021 카카오 채용연계형 인턴십 표 편집
#
# 유튜브 문제 풀이: https://youtu.be/mlJu7Y4kg0o
#
# 파이썬 소스: https://bit.ly/3NBBIu4
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/81303

from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            'OOOOXOOO',
            solution(
                8, 2,
                ["D 2","C","U 3","C","D 4","C","U 2","Z","Z"]
            )
        )

    def test1_solution(self):
        self.assertEqual(
            'OOXOXOOO',
            solution(
                8, 2,
                ["D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"]
            )
        )