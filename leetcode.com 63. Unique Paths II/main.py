from typing import List
from collections import deque


class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        if obstacleGrid[0][0] == 1:
            return 0

        mx_row, mx_col = len(obstacleGrid), len(obstacleGrid[0])
        board = [[0 for _ in range(mx_col)] for _ in range(mx_row)]
        board[0][0] = 1
        queue = deque([[0, 0]])
        drc = [[1, 0], [0, 1]]

        while queue:
            r, c = queue.popleft()

            for dr, dc in drc:
                nr, nc = r + dr, c + dc
                if 0 <= nr < mx_row and 0 <= nc < mx_col and obstacleGrid[nr][nc] == 0:
                    board[nr][nc] += board[r][c]
                    if not queue.__contains__([nr, nc]):
                        queue.append([nr, nc])

        return board[mx_row - 1][mx_col - 1]
