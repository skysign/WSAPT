from typing import List


class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        commands = []

        for r in range(len(matrix)):
            for c in range(len(matrix[0])):
                if matrix[r][c] == 0:
                    commands.append([r, c])

        while len(commands) > 0:
            r, c = commands.pop(0)

            for col in range(len(matrix[0])):
                matrix[r][col] = 0

            for row in range(len(matrix)):
                matrix[row][c] = 0