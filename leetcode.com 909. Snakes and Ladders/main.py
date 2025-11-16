import sys
from typing import List


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        N = len(board)
        destination = 0
        num2board = [[0] * N for _ in range(N)]
        visited = [[sys.maxsize] * N for _ in range(N)]
        visited[len(visited)-1][0] = 0

        number = 1

        for r in range(N):
            for c in range(N):
                num2board[r][c] = number
                number += 1

        for r in range(N):
            if 1 == r % 2:
                num2board[r].reverse()
            destination += len(num2board[r])

        num2board.reverse()

        n2rc = {}
        for r in range(N):
            for c in range(N):
                n2rc[num2board[r][c]] = [r, c]

            def visit(fr, dice):
                sr, sc = n2rc[fr]
                mx = min(destination, fr + dice)
                nr, nc = n2rc[mx]

                if board[nr][nc] == -1:
                    if visited[sr][sc] + 1 < visited[nr][nc]:
                        visited[nr][nc] = visited[sr][sc] + 1
                        for n in range(1, 7):
                            visit(fr + dice, n)
                else:
                    new_dst = board[nr][nc]
                    nr, nc = n2rc[new_dst]
                    if visited[sr][sc] + 1 < visited[nr][nc]:
                        visited[nr][nc] = visited[sr][sc] + 1
                        for n in range(1, 7):
                            visit(new_dst, n)

        for n in range(1, 7):
            visit(1, n)

        r, c = n2rc[destination]
        if sys.maxsize == visited[r][c]:
            return -1
        return visited[r][c]