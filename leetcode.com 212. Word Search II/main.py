from typing import List, Dict
from copy import deepcopy

class Node(Dict):
    def __init__(self):
        self.word_ends: bool = False

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
        self.root: Node = Node()
        self.answer: Dict = {}


    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        self.row = len(board)
        self.col = len(board[0])
        self.build_tries(words)

        self.my_find_word(board)

        return list(self.answer.keys())

    def build_tries(self, words):
        for word in words:
            crnt_node = self.root

            for c in word:
                if not c in crnt_node.keys():
                    crnt_node[c] = Node()

                crnt_node = crnt_node[c]

            crnt_node.word_ends = True

    def my_find_word(self, board: List[List[str]]) -> bool:
        for row in range(self.row):
            for col in range(self.col):
                c = board[row][col]
                if c in self.root.keys():
                    pathes = [c]
                    board[row][col] = None
                    self.dfs(row, col, self.root[c], board, pathes)
                    board[row][col] = c

    def dfs(self, old_row, old_col, crnt_node: Node, board, pathes):
        if crnt_node.word_ends:
            self.answer[''.join(pathes)] = True

        for d_row, d_col in self.drc:
            new_row, new_col = old_row + d_row, old_col + d_col
            if self.is_in(new_row, new_col):
                nc = board[new_row][new_col]

                new_node = crnt_node.get(nc)

                if new_node is not None:
                    pathes.append(nc)
                    board[new_row][new_col] = None
                    # new_node = crnt_node[nc]
                    self.dfs(new_row, new_col, new_node, board, pathes)
                    board[new_row][new_col] = nc
                    pathes.pop(len(pathes) -1)

    def is_in(self, r, c) -> bool:
        if 0 <= r < self.row and 0 <= c < self.col:
            return True

        return False
