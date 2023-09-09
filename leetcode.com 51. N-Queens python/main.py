from typing import List


class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        board = [['.' for _ in range(n)] for _ in range(n)]
        queens = []
        answer = []

        self.rec(0, 0, queens, board, answer, n)

        return answer

    def rec(self, row_bgn, col_bgn, queens, board, answer, n):
        if col_bgn >= len(board[0]):
            row_bgn += 1
            col_bgn = 0

        if row_bgn >= len(board):
            return

        # 이 위치에 queen을 놓을 수 있으면, 퀸을 놓아보고 진행 한다.
        if self.can_place_queen(row_bgn, col_bgn, queens):
            queens.append((row_bgn, col_bgn))
            board[row_bgn][col_bgn] = 'Q'
            if len(queens) >= n:
                self.save(board, answer)

            self.rec(row_bgn + 1, 0, queens, board, answer, n)

            queens.pop()
            board[row_bgn][col_bgn] = '.'

        # 이 위치에 queen을 놓치 않고, 다음 위치로 이동한다.
        self.rec(row_bgn, col_bgn + 1, queens, board, answer, n)

    def can_place_queen(self, row, col, queens: List[List[int]]):
        for x1, y1 in queens:
            x2, y2 = row, col
            if x1 == x2 or y1 == y2:
                return False
            if abs(x1 - x2) == abs(y1 - y2):
                return False

        return True

    def save(self, board, answer):
        tmp = []
        for row in range(len(board)):
            line = ''
            for col in range(len(board[0])):
                if board[row][col] == 'Q':
                    line += 'Q'
                else:
                    line += '.'

            tmp.append(line)

        answer.append(tmp)