from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(4, solution('<><??>>'))

    def test2_solution(self):
        self.assertEqual(6, solution('??????'))

    def test3_solution(self):
        self.assertEqual(2, solution('<<?'))

    def test4_solution(self):
        self.assertEqual(0, solution('>?<'))
