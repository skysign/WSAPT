from typing import List


class Solution:
    def floodFill(self, image: List[List[int]], sr: int, sc: int, color: int) -> List[List[int]]:
        if image[sr][sc] == color:
            return image

        visited = [[False for _ in range(len(image[0]))] for _ in range(len(image))]
        queue = [[sr, sc]]
        queue_change_color = [[sr, sc]]
        drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]

        while queue:
            r, c = queue.pop(0)
            visited[r][c] = True

            for dr, dc in drc:
                nr, nc = r + dr, c + dc
                if 0 <= nr < len(image) and 0 <= nc < len(image[0]) \
                    and image[r][c] == image[nr][nc] \
                    and visited[nr][nc] == False:
                    queue_change_color.append([nr, nc])
                    queue.append([nr, nc])

        while queue_change_color:
            r, c = queue_change_color.pop(0)
            image[r][c] = color

        return image