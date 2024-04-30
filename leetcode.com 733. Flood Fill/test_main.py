from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_flood_fill(self):
        sol = Solution()
        self.assertEqual(sol.floodFill([[1, 1, 1], [1, 1, 0], [1, 0, 1]], sr=1, sc=1, color=2), [[2, 2, 2], [2, 2, 0], [2, 0, 1]])

    def test2_flood_fill(self):
        sol = Solution()
        self.assertEqual(sol.floodFill([[0, 0, 0], [0, 0, 0]], sr=0, sc=0, color=0), [[0,0,0],[0,0,0]])
