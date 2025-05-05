from typing import List


class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        answer = 0

        for y in range(0, len(matrix)):
            for x in range(0, len(matrix[0])):
                matrix[y][x] = int(matrix[y][x])
                answer = max(answer, matrix[y][x])

                if y-1 >= 0 and x -1 >= 0:
                    if matrix[y][x] >= 1 \
                        and matrix[y - 1][x - 1] >= 1 \
                        and matrix[y - 1][x] >= 1 \
                        and matrix[y][x - 1] >= 1:
                            tmp = min(matrix[y - 1][x - 1], matrix[y - 1][x], matrix[y][x - 1])
                            matrix[y][x] = tmp + 1
                            answer = max(answer, matrix[y][x])

        return answer * answer
