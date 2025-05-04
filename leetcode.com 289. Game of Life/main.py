from typing import List
import copy

class Solution:
    def gameOfLife(self, board: List[List[int]]) -> None:
        dyx = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]
        board2 = copy.deepcopy(board)

        for sy in range(len(board)):
            for sx in range(len(board[0])):
                cnt_neighbor = 0
                for dy, dx in dyx:
                    y, x = sy + dy, sx + dx
                    if 0 <= y < len(board) and 0 <= x < len(board[0]):
                        cnt_neighbor += board[y][x]

                    live_or_die = 0
                    if board[sy][sx] == 1 and cnt_neighbor < 2:
                        live_or_die = 0
                    elif board[sy][sx] == 1 and 2 <= cnt_neighbor <= 3:
                        live_or_die = 1
                    elif board[sy][sx] == 1 and cnt_neighbor > 3:
                        live_or_die = 0
                    elif board[sy][sx] == 0 and cnt_neighbor == 3:
                        live_or_die = 1
                    else:
                        live_or_die = board[sy][sx]

                    board2[sy][sx] = live_or_die

        for sy in range(len(board)):
            for sx in range(len(board[0])):
                board[sy][sx] = board2[sy][sx]