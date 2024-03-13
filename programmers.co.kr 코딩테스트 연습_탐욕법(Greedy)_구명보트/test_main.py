from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(3, solution([70, 50, 80, 50], 100))

    def test2_solution(self):
        self.assertEqual(3, solution([70, 80, 50], 100))
