from unittest import TestCase
from main import solution

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(16,
                         solution(4, 5,
                                  [1, 0, 3, 1, 2],
                                  [0, 3, 0, 4, 0]))
    def test_solution2(self):
        self.assertEqual(30,
                         solution(2, 7,
                                  [1, 0, 2, 0, 1, 0, 2],
                                  [0, 2, 0, 1, 0, 2, 0]))
