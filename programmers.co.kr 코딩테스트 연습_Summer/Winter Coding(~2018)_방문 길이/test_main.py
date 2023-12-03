from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(7, solution('ULURRDLLU'))

    def test2_solution(self):
        self.assertEqual(7, solution('LULLLLLLU'))
