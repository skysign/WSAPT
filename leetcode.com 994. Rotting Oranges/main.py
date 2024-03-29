from typing import List


class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        rotten_oranges: List[List] = []
        MAX_ROW = len(grid)
        MAX_COL = len(grid[0])
        count_fresh_orange = 0

        for row in range(len(grid)):
            for col in range(len(grid[0])):
                if grid[row][col] == 1:
                    count_fresh_orange += 1
                elif grid[row][col] == 2:
                    rotten_oranges.append([row, col])

        dyx = [[-1, 0], [0, 1], [1, 0], [0, -1]]

        minutes = 0
        new_rotten_oranges: List[List] = []

        while rotten_oranges:
            y, x = rotten_oranges.pop(0)

            for [dy, dx] in dyx:
                ny, nx = y + dy, x + dx

                if 0 <= ny < MAX_ROW and 0 <= nx < MAX_COL:
                    if grid[ny][nx] == 1:
                        new_rotten_oranges.append([ny, nx])
                        grid[ny][nx] = 2
                        count_fresh_orange -= 1

            if len(rotten_oranges) == 0 and len(new_rotten_oranges) != 0:
                rotten_oranges = new_rotten_oranges
                new_rotten_oranges = []
                minutes += 1

        if count_fresh_orange == 0:
            return minutes

        return -1