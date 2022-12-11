from unittest import TestCase
from solution import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution([3, 2, 7, 2], [4, 6, 5, 1]), 2);
    def test2_solution(self):
        self.assertEqual(solution([1, 2, 1, 2], [1, 10, 1, 2]), 7);

    def test3_solution(self):
        self.assertEqual(solution([1, 1], [1, 5]), -1);