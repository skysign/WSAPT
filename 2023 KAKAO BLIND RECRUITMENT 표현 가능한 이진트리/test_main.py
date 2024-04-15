from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution([7, 42, 5]), [1, 1, 0])

    def test2_solution(self):
        self.assertEqual(solution([63, 111, 95]), [1, 1, 0])

    #출처 https://school.programmers.co.kr/questions/57330
    def testx_solution(self):
        self.assertEqual(solution([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]), [0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1])