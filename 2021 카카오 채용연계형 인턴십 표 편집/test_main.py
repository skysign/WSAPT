from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            'OOOOXOOO',
            solution(
                8, 2,
                ["D 2","C","U 3","C","D 4","C","U 2","Z","Z"]
            )
        )

    def test1_solution(self):
        self.assertEqual(
            'OOXOXOOO',
            solution(
                8, 2,
                ["D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"]
            )
        )