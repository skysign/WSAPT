from typing import List


class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        max_row = len(matrix)
        max_col = len(matrix[0])
        visited = [[False] * max_col for _ in range(max_row)]
        rtn = []

        sr, sc = 0, 0
        visited[sr][sc] = True
        rtn.append(matrix[0][0])

        drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        dd = drc.pop(0)
        drc.append(dd)

        while True:
            dr, dc = dd
            nr, nc = sr + dr, sc + dc
            if 0 <= nr < max_row and 0 <= nc < max_col and visited[nr][nc] == False:
                rtn.append(matrix[nr][nc])
                sr, sc = nr, nc
                visited[sr][sc] = True
            else:
                # check stop condition
                change_direction = False
                for dr, dc in drc:
                    nr, nc = sr + dr, sc + dc
                    if 0 <= nr < max_row and 0 <= nc < max_col and visited[nr][nc] == False:
                        change_direction |= True

                    if change_direction:
                        break

                if change_direction:
                    dd = drc.pop(0)
                    drc.append(dd)
                else:
                    return rtn
