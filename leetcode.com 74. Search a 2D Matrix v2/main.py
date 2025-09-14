from typing import List


class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        idx_row1, idx_row2 = 0, len(matrix) - 1
        idx_row = -1

        while idx_row1 < idx_row2:
            idx_mid = (idx_row1 + idx_row2) // 2

            if target == matrix[idx_mid][0]:
                idx_row = idx_mid
                break
            elif target < matrix[idx_mid][0]:
                idx_row2 = idx_mid - 1
            elif target <= matrix[idx_mid][-1]:
                idx_row2 = idx_mid
            else:  # matrix[idx_mid][-1] < target
                idx_row1 = idx_mid + 1

        if idx_row == -1:
            idx_row = idx_row1

        idx_col1, idx_col2 = 0, len(matrix[0]) - 1

        while idx_col1 <= idx_col2:
            idx_mid = (idx_col1 + idx_col2) // 2

            if target == matrix[idx_row][idx_mid]:
                return True
            elif target < matrix[idx_row][idx_mid]:
                idx_col2 = idx_mid - 1
            else: # matrix[idx_row][idx_mid] < target
                idx_col1 = idx_mid + 1

        return False
