from unittest import TestCase
from main import solution

# 2022 KAKAO BLIND RECRUITMENT 파괴되지 않은 건물 python
#
# 유튜브 문제 풀이: https://youtu.be/Gu6uXd1bPMU
#
# 파이썬소스: http://bit.ly/3Gikm2M

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(10,
                         solution([[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5],[5,5,5,5,5]], [[1,0,0,3,4,4],[1,2,0,2,3,2],[2,1,0,3,1,2],[1,0,1,3,3,1]]))

    def test_solution2(self):
        self.assertEqual(6,
                         solution([[1,2,3],[4,5,6],[7,8,9]], [[1,1,1,2,2,4],[1,0,0,1,1,2],[2,2,0,2,0,100]]))