from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(3, solution([9, -1, -5]))

    def test2_solution(self):
        self.assertEqual(6, solution([-16, 27, 65, -2, 58, -92, -71, -68, -61, -33]))
