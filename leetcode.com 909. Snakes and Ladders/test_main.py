from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_snakes_and_ladders(self):
        sln = Solution()
        self.assertEqual(4, sln.snakesAndLadders(
            board=[[-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, 35, -1, -1, 13, -1], [-1, -1, -1, -1, -1, -1], [-1, 15, -1, -1, -1, -1]]
        ))

    def test2_snakes_and_ladders(self):
        sln = Solution()
        self.assertEqual(1, sln.snakesAndLadders(
            board = [[-1,-1],[-1,3]]
        ))
