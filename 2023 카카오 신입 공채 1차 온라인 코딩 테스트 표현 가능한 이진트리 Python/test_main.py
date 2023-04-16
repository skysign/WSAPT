from unittest import TestCase
from main import solution

class Test(TestCase):
    def test_solution(self):
        self.assertEqual(
            [1, 1, 0],
            solution(
                [7, 42, 5]
            )
        )

    def test2_solution(self):
        self.assertEqual(
            [1, 1, 0],
            solution(
                [63, 111, 95]
            )
        )
