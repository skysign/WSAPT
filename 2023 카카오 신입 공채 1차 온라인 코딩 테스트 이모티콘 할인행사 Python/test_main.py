from unittest import TestCase
from main import solution

# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 이모티콘 할인행사 Python
#
# 문제 링크: http://bit.ly/3mkbhzu

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(
            [1, 5400],
            solution(
                [[40, 10000], [25, 10000]],
                [7000, 9000]
            )
        )

    def test2_solution(self):
        self.assertEqual(
            [4, 13860],
            solution(
                [[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]],
                [1300, 1500, 1600, 4900]
            )
        )
