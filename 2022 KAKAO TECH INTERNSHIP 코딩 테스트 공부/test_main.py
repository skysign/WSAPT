from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(15, solution(10, 10, [[10, 15, 2, 1, 2], [20, 20, 3, 3, 4]]))

    def test2_solution(self):
        self.assertEqual(13, solution(0, 0, [[0, 0, 2, 1, 2], [4, 5, 3, 1, 2], [4, 11, 4, 0, 2], [10, 4, 0, 4, 2]]))


    # 아래 테스트케이스는 여기서 가지고 왔습니다.
    # https://school.programmers.co.kr/questions/46986
    def testx1_solution(self):
        self.assertEqual(15, solution(10, 10, [[10, 15, 2, 1, 2], [20, 20, 3, 3, 4]]))