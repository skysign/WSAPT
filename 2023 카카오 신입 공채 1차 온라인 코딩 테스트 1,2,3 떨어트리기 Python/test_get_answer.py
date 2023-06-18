from unittest import TestCase
from main import get_answer

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            [1],
            get_answer(1, 1)
        )

    def test12f_solution(self):
        self.assertEqual(
            [-1],
            get_answer(1, 2)
        )

    def test22_solution(self):
        self.assertEqual(
            [1, 1],
            get_answer(2, 2)
        )

    def test22_solution(self):
        self.assertEqual(
            [2],
            get_answer(2, 1)
        )

    def test22_solution(self):
        self.assertEqual(
            [-1],
            get_answer(2, 3)
        )

    def test31_solution(self):
        self.assertEqual(
            [3],
            get_answer(3, 1)
        )

    def test32_solution(self):
        self.assertEqual(
            [1, 2],
            get_answer(3, 2)
        )

    def test33_solution(self):
        self.assertEqual(
            [1, 1, 1],
            get_answer(3, 3)
        )

    def test34f_solution(self):
        self.assertEqual(
            [-1],
            get_answer(3, 4)
        )

    def test41f_solution(self):
        self.assertEqual(
            [-1],
            get_answer(4, 1)
        )

    def test42_solution(self):
        self.assertEqual(
            [1, 3],
            get_answer(4, 2)
        )

    def test43_solution(self):
        self.assertEqual(
            [1, 1, 2],
            get_answer(4, 3)
        )

    def test44_solution(self):
        self.assertEqual(
            [1, 1, 1, 1],
            get_answer(4, 4)
        )

    def test45f_solution(self):
        self.assertEqual(
            [-1],
            get_answer(4, 5)
        )

    def test55_solution(self):
        self.assertEqual(
            [1, 1, 1, 1, 1],
            get_answer(5, 5)
        )

    def test54_solution(self):
        self.assertEqual(
            [1, 1, 1, 2],
            get_answer(5, 4)
        )
