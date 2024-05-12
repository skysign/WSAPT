from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution())

    def test1_solution(self):
        self.assertEqual(44, solution([14, 17, 15, 18, 19, 14, 13, 16, 28, 17], [[10, 8], [1, 9], [9, 7], [5, 4], [1, 5], [5, 10], [10, 6], [1, 3], [10, 2]]))

    def test2_solution(self):
        self.assertEqual(6, solution([5, 6, 5, 3, 4], [[2, 3], [1, 4], [2, 5], [1, 2]]))

    def test3_solution(self):
        self.assertEqual(5, solution([5, 6, 5, 1, 4], [[2, 3], [1, 4], [2, 5], [1, 2]]))

    def test4_solution(self):
        self.assertEqual(2, solution([10, 10, 1, 1], [[3, 2], [4, 3], [1, 4]]))

    def test_mya_solution(self):
        self.assertEqual(2, solution([1, 1, 10, 10], [[3, 2], [4, 3], [1, 4]]))

    def test_my1_solution(self):
        self.assertEqual(3, solution([1, 1, 10, 10, 2, 1], [[3, 2], [4, 3], [1, 4], [4, 5], [5, 6]]))

    def test_my2_solution(self):
        self.assertEqual(4, solution([1, 1, 10, 10, 2, 1, 2, 1], [[3, 2], [4, 3], [1, 4], [4, 5], [5, 6], [1, 7], [7, 8]]))

    def test_my3_solution(self):
        self.assertEqual(4, solution([1, 1, 10, 10, 2, 1, 1, 2], [[3, 2], [4, 3], [1, 4], [4, 5], [5, 6], [1, 7], [7, 8]]))

    def test_my4_solution(self):
        self.assertEqual(4, solution([1, 1, 10, 10, 2, 1, 10, 10, 1], [[3, 2], [4, 3], [1, 4], [4, 5], [5, 6], [1, 7], [7, 8], [8, 9]]))

    def test_my5_solution(self):
        self.assertEqual(4, solution([1, 1, 10, 10, 2, 1, 10, 10, 2, 1], [[3, 2], [4, 3], [1, 4], [4, 5], [5, 6], [1, 7], [7, 8], [8, 9], [8, 10]]))
