from typing import List


class Solution:
    def __init__(self):
        self.drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        self.mx_row = 0
        self.mx_col = 0

    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        self.mx_row = len(matrix)
        self.mx_col = len(matrix[0])
        outdegree: List[List[int]] = [[0 for _ in range(self.mx_col)] for _ in range(self.mx_row)]
        leaves = []

        for r in range(self.mx_row):
            for c in range(self.mx_col):
                for dr, dc in self.drc:
                    nr, nc = r + dr, c + dc
                    if 0 <= nr < self.mx_row and 0 <= nc < self.mx_col:
                        if matrix[nr][nc] > matrix[r][c]:
                            outdegree[r][c] += 1

                if outdegree[r][c] == 0:
                    leaves.append([r, c])

        mx = 0
        while len(leaves) > 0:
            mx += 1
            newLeaves = []

            for r, c in leaves:
                for dr, dc in self.drc:
                    nr, nc = r + dr, c + dc
                    if 0 <= nr < self.mx_row and 0 <= nc < self.mx_col:
                        if matrix[nr][nc] < matrix[r][c]:
                            outdegree[nr][nc] -= 1

                            if outdegree[nr][nc] == 0:
                                newLeaves.append([nr, nc])

            leaves = newLeaves

        return mx