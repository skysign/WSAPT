from typing import List
from copy import deepcopy

class Solution:
    def __init__(self):
        self.drc = [
            (0, -1),
            (-1, 0),
            (0, 1),
            (1, 0)
        ]
        self.row = 0
        self.col = 0


    # Time Limit Exceeded
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        rtn: List[str] = []
        self.row = len(board)
        self.col = len(board[0])

        for word in words:
            if self.my_find_word(board, word):
                rtn.append(word)

        return rtn

    def my_find_word(self, board: List[List[str]], word: str) -> bool:
        c = word[0]
        ori_visited = [[False for _ in range(self.col)] for _ in range(self.row)]

        for row in range(self.row):
            for col in range(self.col):
                if c == board[row][col]:
                    visited = deepcopy(ori_visited)
                    visited[row][col] = True
                    if self.dfs(row, col, word[1:], board, visited):
                        return True

        return False

    def dfs(self, row, col, word, board, visited):
        if '' == word:
            return True

        c = word[0]

        for idx in range(len(self.drc)):
            dr, dc = self.drc[idx]
            nr, nc = row + dr, col + dc
            if self.isIn(nr, nc) and board[nr][nc] == c and visited[nr][nc] == False:
                visited[nr][nc] = True
                if self.dfs(nr, nc, word[1:], board, visited):
                    return True
                visited[nr][nc] = False

        return False

    def isIn(self, r, c) -> bool:
        if 0 <= r < self.row and 0 <= c < self.col:
            return True

        return False