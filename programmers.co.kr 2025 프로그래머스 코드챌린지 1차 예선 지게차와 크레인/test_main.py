from unittest import TestCase
from main import solution


class Test(TestCase):
    def test_solution(self):
        self.assertEqual(11, solution(["AZWQY", "CAABX", "BBDDA", "ACACA"], ["A", "BB", "A"]))
