from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        MAX_ROW = len(grid)
        MAX_COL = len(grid[0])
        answer = 0

        dyx = [[1, 0], [0, 1], [-1, 0], [0, -1]]

        for row in range(MAX_ROW):
            for col in range(MAX_COL):
                if grid[row][col] == '1':
                    queue = [[row, col]]
                    grid[row][col] = 0
                    answer += 1

                    while queue:
                        y, x = queue.pop(0)

                        for dy, dx in dyx:
                            ny, nx = y + dy, x + dx
                            if 0 <= ny < MAX_ROW and 0 <= nx < MAX_COL and grid[ny][nx] == '1':
                                queue.append([ny, nx])
                                grid[ny][nx] = 0

        return answer
