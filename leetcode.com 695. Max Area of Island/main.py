from typing import List

class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        answer = 0

        dyx = [[1, 0], [0, 1], [-1, 0], [0, -1]]
        MAX_ROW = len(grid)
        MAX_COL = len(grid[0])

        for row in range(MAX_ROW):
            for col in range(MAX_COL):
                if grid[row][col] == 1:
                    answer_local = 0
                    queue = [[row, col]]
                    grid[row][col] = 0

                    while queue:
                        y, x = queue.pop(0)
                        answer_local += 1
                        grid[y][x] = 0

                        for dy, dx in dyx:
                            ny = y + dy
                            nx = x + dx

                            if 0 <= ny < MAX_ROW    \
                                and 0 <= nx < MAX_COL   \
                                and grid[ny][nx] == 1:
                                queue.append([ny, nx])
                                grid[ny][nx] = 0

                    answer = max(answer, answer_local)
        return answer