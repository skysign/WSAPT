from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(14, solution('test1.txt'))
