from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_oranges_rotting(self):
        sol = Solution()
        self.assertEqual(4, sol.orangesRotting([[2, 1, 1], [1, 1, 0], [0, 1, 1]]))

    def test2_oranges_rotting(self):
        sol = Solution()
        self.assertEqual(-1, sol.orangesRotting([[2, 1, 1], [0, 1, 1], [1, 0, 1]]))

    def test3_oranges_rotting(self):
        sol = Solution()
        self.assertEqual(0, sol.orangesRotting([[0, 2]]))

    def test7_oranges_rotting(self):
        sol = Solution()
        self.assertEqual(-1, sol.orangesRotting([[0, 1]]))
