from unittest import TestCase
from main import solution

# 2022 KAKAO TECH INTERNSHIP 성격 유형 검사하기 python
#
# 유튜브 문제 풀이: https://youtu.be/9sImoRvqxlI
#
# 파이썬소스: http://bit.ly/3BK8gNj

class Test(TestCase):
    def test_solution(self):
        self.assertEqual("TCMA",
                         solution(["AN", "CF", "MJ", "RT", "NA"], [5, 3, 2, 7, 5]))
    def test_solution2(self):
        self.assertEqual("RCJA",
                         solution(["TR", "RT", "TR"], [7, 1, 3]))
