from typing import List


class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        row = [[False] * 9 for _ in  range(9)]
        col = [[False] * 9 for _ in range(9)]
        box = [[False] * 9 for _ in range(9)]

        for r in range(9):
            for c in range(9):
                if board[r][c] != '.':
                    idx = ord(board[r][c]) - ord('1')

                    if row[r][idx]:
                        return False

                    row[r][idx] = True

                    if col[idx][c]:
                        return False

                    col[idx][c] = True

                    i = (r // 3) * 3 + (c // 3)
                    if box[i][idx]:
                        return False

                    box[i][idx] = True

        return True