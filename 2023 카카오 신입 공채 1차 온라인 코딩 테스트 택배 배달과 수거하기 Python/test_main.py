from unittest import TestCase
from main import solution

# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 택배 배달과 수거하기 Python
#
# 유튜브 문제 풀이: https://youtu.be/5FX8K-bih9Q
#
# 파이썬 소스: http://bit.ly/40ycouk
#
# 문제 링크: http://bit.ly/3MaqmxT

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(16,
                         solution(4, 5,
                                  [1, 0, 3, 1, 2],
                                  [0, 3, 0, 4, 0]))
    def test_solution2(self):
        self.assertEqual(30,
                         solution(2, 7,
                                  [1, 0, 2, 0, 1, 0, 2],
                                  [0, 2, 0, 1, 0, 2, 0]))
