# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 개인정보 수집 유효기간 Python
#
# 유튜브 문제 풀이: https://youtu.be/lZa2qBb9Sts
#
# 파이썬 소스: http://bit.ly/3FbQzIc
#
# 문제 링크: http://bit.ly/3YD8XR9

from unittest import TestCase
from main import solution

class Test(TestCase):
    def test_solution(self):
        self.assertEqual([1, 3],
                         solution("2022.05.19",
                                  ["A 6", "B 12", "C 3"],
                                  ["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]))

        self.assertEqual([1, 4, 5],
                         solution("2020.01.01",
                                  ["Z 3", "D 5"],
                                  ["2019.01.01 D", "2019.11.15 Z",
                                   "2019.08.02 D", "2019.07.01 D",
                                   "2018.12.28 Z"]))
