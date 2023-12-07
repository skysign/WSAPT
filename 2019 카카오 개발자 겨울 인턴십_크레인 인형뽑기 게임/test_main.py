from unittest import TestCase
from main import solution

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(4, solution([[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]], [1,5,3,5,1,2,1,4]))
