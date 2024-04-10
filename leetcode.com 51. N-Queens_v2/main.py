from typing import List


class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        board = [['.' for _ in range(n)] for _ in range(n)]
        answer = []

        def attack(queens: List[List[int]], ny, nx):
            for y, x in queens:
                if y == ny or x == nx:
                    return True
                if abs(y - ny) == abs(x - nx):
                    return True

            return False

        def rec(board, y, queens, answer):
            if y == n:
                answer.append([''.join(row) for row in board])
                return

            for x in range(n):
                if False == attack(queens, y, x):
                    board[y][x] = 'Q'
                    rec(board, y + 1, queens + [[y, x]], answer)
                    board[y][x] = '.'

        rec(board, 0, [], answer)

        return answer
