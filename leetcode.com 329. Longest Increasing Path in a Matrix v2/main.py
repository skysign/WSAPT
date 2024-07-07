from typing import List


class Solution:
    def __init__(self):
        self.drc = [
            [-1, 0],
            [0, 1],
            [1, 0],
            [0, -1]
        ]

    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        self.mx_row = len(matrix)
        self.mx_col = len(matrix[0])
        bgns: List[List[int]] = []

        for r in range(self.mx_row):
            for c in range(self.mx_col):
                b = True

                for dr, dc in self.drc:
                    nr, nc = r + dr, c + dc
                    if 0 <= nr < self.mx_row and 0 <= nc < self.mx_col:
                        if matrix[nr][nc] > matrix[r][c]:
                            b = False
                            break

                if b:
                    bgns.append([r, c])

        mx = 0
        dp: List[List[int]] = [[0 for _ in range(self.mx_col)] for _ in range(self.mx_row)]

        for r, c in bgns:
            dp[r][c] = 1
            mx_local = self.dfs(r, c, matrix, dp)
            mx = max(mx, mx_local)

        return mx

    def dfs(self, r, c, matrix, dp):
        mx = dp[r][c]

        for dr, dc in self.drc:
            nr, nc = r + dr, c + dc
            if 0 <= nr < self.mx_row and 0 <= nc < self.mx_col:
                if matrix[nr][nc] < matrix[r][c]:
                    if dp[nr][nc] < dp[r][c] + 1:
                        dp[nr][nc] = dp[r][c] + 1
                        mx_local = self.dfs(nr, nc, matrix, dp)
                        mx = max(mx, mx_local)

        return mx
