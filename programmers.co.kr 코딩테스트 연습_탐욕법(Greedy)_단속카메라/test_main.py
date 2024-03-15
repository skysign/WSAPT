from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(2, solution([[-20,-15], [-14,-5], [-18,-13], [-5,-3]]))
