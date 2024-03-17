from typing import List


class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        MAX_ROW = len(heights)
        MAX_COL = len(heights[0])
        pacific: List[List] = [[False for _ in range(MAX_COL)] for _ in range(MAX_ROW)]
        atlantic: List[List] = [[False for _ in range(MAX_COL)] for _ in range(MAX_ROW)]

        def dfs(visited: List[List], prev_height, r, c):
            if 0 <= r < MAX_ROW \
                    and 0 <= c < MAX_COL \
                    and visited[r][c] == False \
                    and prev_height <= heights[r][c]:
                visited[r][c] = True

                dfs(visited, heights[r][c], r - 1, c)
                dfs(visited, heights[r][c], r, c + 1)
                dfs(visited, heights[r][c], r + 1, c)
                dfs(visited, heights[r][c], r, c - 1)

        for r in range(0, MAX_ROW):
            dfs(pacific, 0, r, 0)
            dfs(atlantic, 0, r, MAX_COL - 1)

        for c in range(0, MAX_COL):
            dfs(pacific, 0, 0, c)
            dfs(atlantic, 0, MAX_ROW - 1, c)

        answer: List[List] = []

        for r in range(0, MAX_ROW):
            for c in range(0, MAX_COL):
                if pacific[r][c] and atlantic[r][c]:
                    answer.append([r, c])

        return answer