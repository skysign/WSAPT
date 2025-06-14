from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        MAX_ROW = len(grid)
        MAX_COL = len(grid[0])
        answer = 0
        drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]

        for row in range(MAX_ROW):
            for col in range(MAX_COL):
                if grid[row][col] == '1':
                    answer += 1
                    queue = [[row, col]]
                    grid[row][col] = '0'

                    while queue:
                        sr, sc = queue.pop(0)

                        for dr, dc in drc:
                            r, c = sr + dr, sc + dc
                            if 0 <= r < MAX_ROW and 0 <= c < MAX_COL and grid[r][c] == '1':
                                queue.append([r, c])
                                grid[r][c] = '0'
        return answer
