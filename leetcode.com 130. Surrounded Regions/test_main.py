from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_solve(self):
        sol = Solution()
        board = [["X", "X", "X", "X"], ["X", "O", "O", "X"], ["X", "X", "O", "X"], ["X", "O", "X", "X"]]
        sol.solve(board)
        self.assertEqual([["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]], board)

    def test2_solve(self):
        sol = Solution()
        board = [["X"]]
        sol.solve(board)
        self.assertEqual([["X"]], board)

    def test25_solve(self):
        sol = Solution()
        board = [["O","O","O"],["O","O","O"],["O","O","O"]]
        sol.solve(board)
        self.assertEqual([["O","O","O"],["O","O","O"],["O","O","O"]], board)
