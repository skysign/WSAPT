from typing import List


class Solution:
    def __init__(self):
        self.drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        self.mx_row = 0
        self.mx_col = 0

    # DFS + Memoization
    # def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
    #     self.mx_row = len(matrix)
    #     self.mx_col = len(matrix[0])
    #     dp: List[List[int]] = [[0 for _ in range(self.mx_col)] for _ in range(self.mx_row)]
    #     mx = 0
    #
    #     for r in range(self.mx_row):
    #         for c in range(self.mx_col):
    #             mx_local = self.dfs(r, c, matrix, dp, 1)
    #             mx = max(mx, mx_local)
    #
    #     return mx
    #
    # def dfs(self, r, c, matrix, dp, depth):
    #     if dp[r][c] > depth:
    #         return dp[r][c]
    #
    #     mx = dp[r][c] = depth
    #
    #     for dr, dc in self.drc:
    #         nr, nc = r + dr, c + dc
    #         if 0 <= nr < self.mx_row and 0 <= nc < self.mx_col:
    #             if matrix[nr][nc] > matrix[r][c]:
    #                 if dp[nr][nc] < dp[r][c] + 1:
    #                     dp[nr][nc] = dp[r][c] + 1
    #                     mx_local = self.dfs(nr, nc, matrix, dp, dp[nr][nc])
    #                     mx = max(mx, mx_local)
    #
    #     return mx
