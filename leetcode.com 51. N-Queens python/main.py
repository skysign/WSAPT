from typing import List


def can_place_queen(row, col, path: List[tuple]):
    for x1, y1 in path:
        x2, y2 = row, col
        if x1 == x2 or y1 == y2:
            return False
        if abs(x1 - x2) == abs(y1 - y2):
            return False

    return True

def place_queen(row, col, path, board, answer, n):
    path.append((row, col))
    board[row][col] = 'Q'
    if len(path) >= n:
        save(board, answer)

def unplace_queen(row, col, path: List[tuple], board, answer, n):
    path.pop()
    board[row][col] = '.'

def save(map, answer):
    tmp = []
    for row in range(len(map)):
        line = ''

        for col in range(len(map[0])):
            if map[row][col] == 'Q':
                line += 'Q'
            else:
                line += '.'

        tmp.append(line)

    answer.append(tmp)

def clear(path, map):
    for row, col in path:
        map[row][col] = '.'


class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        board = [['.' for _ in range(n)] for _ in range(n)]
        path = []
        answer = []

        self.rec(0, 0, path, board, answer, n)

        return answer

    def rec(self, row_bgn, col_bgn, path, board, answer, n):
        if col_bgn >= len(board[0]):
            row_bgn += 1
            col_bgn = 0

        if row_bgn >= len(board):
            return

        # 이 위치에 queen을 놓을 수 있으면, 퀸을 놓아보고 진행 한다.
        if can_place_queen(row_bgn, col_bgn, path):
            place_queen(row_bgn, col_bgn, path, board, answer, n)
            self.rec(row_bgn +1, 0, path, board, answer, n)
            unplace_queen(row_bgn, col_bgn, path, board, answer, n)

        # 이 위치에 queen을 놓치 않고, 다음 위치로 이동한다.
        self.rec(row_bgn, col_bgn +1, path, board, answer, n)
