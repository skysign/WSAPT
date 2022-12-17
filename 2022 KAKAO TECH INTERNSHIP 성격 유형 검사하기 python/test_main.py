from unittest import TestCase
from main import solution


class Test(TestCase):
    def test_solution(self):
        self.assertEqual("TCMA",
                         solution(["AN", "CF", "MJ", "RT", "NA"], [5, 3, 2, 7, 5]))
    def test_solution2(self):
        self.assertEqual("RCJA",
                         solution(["TR", "RT", "TR"], [7, 1, 3]))
