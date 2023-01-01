from unittest import TestCase
from solution import solution

# 2022 카카오 신입 공채 1차 온라인 코딩테스트 신고 결과 받기 Python
#
# 유튜브 문제 풀이: https://youtu.be/XNVY9InXtfU
#
# 파이썬소스: http://bit.ly/3GbA1jc
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/92334

class Test(TestCase):
    def test_solution(self):
        self.assertEqual([2,1,1,0],
                         solution(["muzi", "frodo", "apeach", "neo"],
                                  ["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"],
                                  2))
    def test_solution2(self):
        self.assertEqual([0,0],
                         solution(["con", "ryan"],
                                  ["ryan con", "ryan con", "ryan con", "ryan con"],
                                  3))
