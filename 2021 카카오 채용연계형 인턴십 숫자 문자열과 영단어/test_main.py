from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            1478,
            solution(
                'one4seveneight',
            )
        )

    def test2_solution(self):
        self.assertEqual(
            234567,
            solution(
                '23four5six7',
            )
        )

    def test3_solution(self):
        self.assertEqual(
            234567,
            solution(
                '2three45sixseven',
            )
        )

    def test4_solution(self):
        self.assertEqual(
            123,
            solution(
                '123',
            )
        )
