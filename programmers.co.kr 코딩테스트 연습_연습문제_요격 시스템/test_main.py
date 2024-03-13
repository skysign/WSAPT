from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(3, solution([[4,5],[4,8],[10,14],[11,13],[5,12],[3,7],[1,4]]))
