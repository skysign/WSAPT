from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution([[40, 10000], [25, 10000]], [7000, 9000]), [1, 5400])

    def test2_solution(self):
        self.assertEqual(solution([[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]], [1300, 1500, 1600, 4900]), [4, 13860])
