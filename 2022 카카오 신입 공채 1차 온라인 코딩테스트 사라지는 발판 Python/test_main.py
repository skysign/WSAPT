from unittest import TestCase
from main import solution

# 2022 카카오 신입 공채 1차 온라인 코딩테스트 사라지는 발판 Python
#
# 유튜브 문제 풀이: https://youtu.be/Zxv3ZAqSRBk
#
# 파이썬 소스: http://bit.ly/3GkaLse
#
# 문제: https://school.programmers.co.kr/learn/courses/30/lessons/92345

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(10,
                         solution([[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5]], [[1,0,0,3,4,4],[1,2,0,2,3,2],[2,1,0,3,1,2],[1,0,1,3,3,1]]))

    def test_solution2(self):
        self.assertEqual(6,
                         solution([[1,2,3],[4,5,6],[7,8,9]], [[1,1,1,2,2,4],[1,0,0,1,1,2],[2,2,0,2,0,100]]))