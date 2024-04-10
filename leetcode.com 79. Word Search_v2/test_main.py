from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_exist(self):
        sol = Solution()
        self.assertEqual(True, sol.exist(board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"))

    def test3_exist(self):
        sol = Solution()
        self.assertEqual(False, sol.exist(board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"))
