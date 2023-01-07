from unittest import TestCase
from solution import solution


class Test(TestCase):
    def test_solution(self):
        self.assertEqual(3,
                         solution(437674, 3))

    def test_solution2(self):
        self.assertEqual(2,
                         solution(110011, 10))
