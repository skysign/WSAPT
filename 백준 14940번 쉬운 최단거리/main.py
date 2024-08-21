import sys
from typing import List


def solve():
    row, col = map(int, sys.stdin.readline().strip().split(' '))
    board = [[0 for _ in range(col)] for _ in range(row)]

    row_start = 0
    col_start = 0

    for row_idx in range(row):
        line = list(map(int, sys.stdin.readline().strip().split(' ')))

        for col_idx in range(col):
            board[row_idx][col_idx] = line[col_idx]

            if 2 == line[col_idx]:
                row_start = row_idx
                col_start = col_idx

    board2 = bfs(board, row_start, col_start, row, col)

    for row_idx in range(row):
        for col_idx in range(col):
            if sys.maxsize == board2[row_idx][col_idx] and board[row_idx][col_idx] == 0:
                board2[row_idx][col_idx] = 0
            if sys.maxsize == board2[row_idx][col_idx] and board[row_idx][col_idx] == 1:
                board2[row_idx][col_idx] = -1

    for line in board2:
        print(' '.join(map(str, line)).strip())


def bfs(board: List[List[int]], row_start: int, col_start: int, row_max: int, col_max: int):
    board2 = [[sys.maxsize for _ in range(col_max)] for _ in range(row_max)]
    board2[row_start][col_start] = 0

    queue = [[row_start, col_start, 0]]
    drc = [[1, 0], [0, 1], [-1, 0], [0, -1]]

    while queue:
        rs, rc, v = queue.pop(0)

        for dr, dc in drc:
            nr, nc = rs + dr, rc + dc

            if 0 <= nr < row_max and 0 <= nc < col_max:
                if board[nr][nc] == 1:
                    if board2[nr][nc] > v + 1:
                        queue.append([nr, nc, v + 1])
                        board2[nr][nc] = v + 1

    return board2


if __name__ == '__main__':
    solve()
