from unittest import TestCase
from main import solution

# 2021 카카오 채용연계형 인턴십 숫자 문자열과 영단어
#
# 유튜브 문제 풀이: https://youtu.be/gN6aK7l1chg
#
# 파이썬 소스: https://bit.ly/3CMOuky
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/81301

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            1478,
            solution(
                'one4seveneight',
            )
        )

    def test2_solution(self):
        self.assertEqual(
            234567,
            solution(
                '23four5six7',
            )
        )

    def test3_solution(self):
        self.assertEqual(
            234567,
            solution(
                '2three45sixseven',
            )
        )

    def test4_solution(self):
        self.assertEqual(
            123,
            solution(
                '123',
            )
        )
