from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_solve_nqueens(self):
        sol = Solution()
        self.assertEqual([[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]],
                         sol.solveNQueens(4))

    def test2_solve_nqueens(self):
        sol = Solution()
        self.assertEqual([["Q"]],
                         sol.solveNQueens(1))
