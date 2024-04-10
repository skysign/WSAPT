from typing import List


class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        MAX_ROW, MAX_COL = len(board), len(board[0])

        def rec(board, y, x, word, depth, depth_max):
            if depth == depth_max:
                return True

            for dy, dx in [[-1, 0], [0, 1], [1, 0], [0, -1]]:
                ny, nx = y + dy, x + dx
                if 0 <= ny < MAX_ROW and 0 <= nx < MAX_COL:
                    if board[ny][nx] == word[depth] and False == visited[ny][nx]:
                        visited[ny][nx] = True
                        if rec(board, ny, nx, word, depth + 1, depth_max):
                            return True
                        visited[ny][nx] = False

            return False

        visited = [[False for _ in range(MAX_COL)] for _ in range(MAX_ROW)]

        for y in range(MAX_ROW):
            for x in range(MAX_COL):
                if board[y][x] == word[0]:
                    visited[y][x] = True
                    if rec(board, y, x, word, 1, len(word)):
                        return True
                    visited[y][x] = False

        return False
