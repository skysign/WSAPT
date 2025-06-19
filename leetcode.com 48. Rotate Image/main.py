from typing import List


class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        tmp = [[0 for _ in range(len(matrix[0]))] for _ in range(len(matrix))]
        n = len(matrix)

        for r in range(len(matrix)):
            for c in range(len(matrix)):
                v = matrix[r][c]
                tmp[c][n - r - 1] = v

        for r in range(len(matrix)):
            for c in range(len(matrix)):
                matrix[r][c] = tmp[r][c]
