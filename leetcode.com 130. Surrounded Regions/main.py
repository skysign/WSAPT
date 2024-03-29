import copy
from typing import List


class Solution:
    def __init__(self):
        self.dyx: List[List] = [[-1, 0], [0, 1], [1, 0], [0, -1]]

    def solve(self, board: List[List[str]]) -> None:
        self.MAX_ROW = len(board)
        self.MAX_COL = len(board[0])
        self.visited:List[List] = [[False for _ in range(self.MAX_COL)] for _ in range(self.MAX_ROW)]

        for row in range(self.MAX_ROW):
            for col in range(self.MAX_COL):
                if board[row][col] == 'O' and self.visited[row][col] == False:
                    queue_O = self.find_O(board, row, col)
                    if self.is_surrounded(board, copy.deepcopy(queue_O)):
                        while queue_O:
                            y, x = queue_O.pop(0)
                            board[y][x] = 'X'

    def find_O(self, board, y, x):
        queue = [[y,x]]
        self.visited[y][x] = True
        queue_O = []

        while queue:
            y, x = queue.pop(0)
            queue_O.append([y, x])

            for dy, dx in self.dyx:
                ny, nx = y + dy, x + dx

                if 0 <= ny < self.MAX_ROW and 0 <= nx < self.MAX_COL:
                    if board[ny][nx] == 'O' and self.visited[ny][nx] == False:
                        queue.append([ny, nx])
                        self.visited[ny][nx] = True

        return queue_O

    def is_surrounded(self, board, queue):
        while queue:
            y, x = queue.pop(0)

            for dy, dx in self.dyx:
                ny, nx = y + dy, x + dx

                if not (0 <= ny < self.MAX_ROW and 0 <= nx < self.MAX_COL):
                    return False

        return True