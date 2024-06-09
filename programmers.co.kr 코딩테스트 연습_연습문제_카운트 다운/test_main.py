from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual([1, 0], solution(21))

    def test2_solution(self):
        self.assertEqual([2, 2], solution(58))
