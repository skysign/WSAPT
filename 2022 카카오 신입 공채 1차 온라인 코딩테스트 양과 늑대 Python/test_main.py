from unittest import TestCase
from main import solution

# 2022 카카오 신입 공채 1차 온라인 코딩테스트 양과 늑대 Python
#
# 유튜브 문제 풀이: https://youtu.be/6Hxf7zjSA-g
#
# 파이썬소스: http://bit.ly/3vK7IDz
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/92343

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(5,
                         solution([0,0,1,1,1,0,1,0,1,0,1,1], [[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]))
    def test_solution2(self):
        self.assertEqual(5,
                         solution([0,1,0,1,1,0,1,0,0,1,0], [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]))
