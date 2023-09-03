from typing import List

dj = [-1, 0, 1, 0]
di = [0, 1, 0, -1]
dji = [(dj[0], di[0]), (dj[1], di[1]), (dj[2], di[2]), (dj[3], di[3])]

MAX_ROW = 0
MAX_COL = 0

class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        global MAX_ROW, MAX_COL

        visited = [[False for _ in range(len(board[0]))] for _ in range(len(board))]

        MAX_ROW = len(board)
        MAX_COL = len(board[0])

        for idx_row in range(len(board)):
            for idx_col in range(len(board[0])):
                if self.rec(word, 0, idx_row, idx_col, board, visited):
                    return True

        return False

    def rec(self, word, depth, sy, sx, board, visited):
        global dji, MAX_ROW, MAX_COL

        if word[depth] != board[sy][sx]:
            return False

        if len(word) == (depth +1):
            return True

        visited[sy][sx] = True

        for dy, dx in dji:
            dsty = sy + dy
            dstx = sx + dx

            if 0 <= dsty < MAX_ROW:
                if 0 <= dstx < MAX_COL:
                    if not visited[dsty][dstx]:
                        if self.rec(word, depth +1, dsty, dstx, board, visited):
                            return True

        visited[sy][sx] = False

        return False