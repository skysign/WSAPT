from typing import List
from collections import deque


class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        r, c = len(board), len(board[0])
        maps = [['O' for _ in range(c + 2)] for _ in range(r + 2)]

        for r in range(len(board)):
            for c in range(len(board[0])):
                maps[r + 1][c + 1] = board[r][c]

        self.bfs_Oto1(maps)

        for r in range(len(maps)):
            for c in range(len(maps[0])):
                if maps[r][c] == 'O':
                    maps[r][c] = 'X'

        for r in range(len(maps)):
            for c in range(len(maps[0])):
                if maps[r][c] == '1':
                    maps[r][c] = 'O'

        for r in range(len(board)):
            for c in range(len(board[0])):
                board[r][c] = maps[r + 1][c + 1]

    def bfs_Oto1(self, maps: List[List[str]]):
        drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]

        queue = deque([[0, 0]])
        maps[0][0] = '1'

        while queue:
            r, c = queue.popleft()

            for dr, dc in drc:
                nr, nc = r + dr, c + dc
                if 0 <= nr < len(maps) and 0 <= nc < len(maps[0]):
                    if maps[nr][nc] == 'O':
                        maps[nr][nc] = '1'
                        queue.append([nr, nc])


