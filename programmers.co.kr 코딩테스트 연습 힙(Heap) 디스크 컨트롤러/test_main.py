from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(9, solution([[0, 3], [1, 9], [2, 6]]))

    def testA_solution(self):
        # https://school.programmers.co.kr/questions/58669
        self.assertEqual(15, solution([[0, 10], [4, 10], [5, 11], [15, 2]]))

    # https://school.programmers.co.kr/questions/49058
    def testC1_solution(self):
        self.assertEqual(2, solution([[0, 3], [10, 1]]))

    def testC2_solution(self):
        self.assertEqual(9, solution([[7, 8], [3, 5], [9, 6]] ))

    def testC3_solution(self):
        self.assertEqual(5, solution([[1, 4], [7, 9], [1000, 3]]))

    def testC4_solution(self):
        self.assertEqual(2, solution([[0, 1], [2, 2], [2, 3]]))

