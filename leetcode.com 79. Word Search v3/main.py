from typing import List


class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        MAX_ROW = len(board)
        MAX_COL = len(board[0])
        drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]

        def dfs(sr, sc, depth, word, visited):
            if depth == len(word):
                return True

            for dr, dc in drc:
                nr, nc = sr + dr, sc + dc
                if 0 <= nr < MAX_ROW and 0 <= nc < MAX_COL \
                    and visited[nr][nc] == False and board[nr][nc] == word[depth]:
                    visited[nr][nc] = True
                    if dfs(nr, nc, depth +1, word, visited):
                        return True
                    visited[nr][nc] = False

            return False

        for r in range(MAX_ROW):
            for c in range(MAX_COL):
                if board[r][c] == word[0]:
                    visited = [[False] * MAX_COL for _ in range(MAX_ROW)]
                    visited[r][c] = True
                    if dfs(r, c, 1, word, visited):
                        return True
                    visited[r][c] = False

        return False