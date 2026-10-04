from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(6, solution(3, 6))
    def test2_solution(self):
        self.assertEqual(1, solution(0, 10))
    def test3_solution(self):
        self.assertEqual(7, solution(3, 100))
    def test4_solution(self):
        self.assertEqual(9, solution(5, 16))
