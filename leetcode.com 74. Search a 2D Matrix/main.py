from typing import List

def row_binary_search(matrix, idx_bgn, idx_end, target):
    if idx_bgn > idx_end:
        return (False, -1)

    idx_last = len(matrix[0]) -1
    idx_mid = int((idx_bgn + idx_end) /2)

    if matrix[idx_mid][0] <= target <= matrix[idx_mid][idx_last]:
        return (True, idx_mid)
    elif target < matrix[idx_mid][0]:
        idx_end = idx_mid -1
    elif target > matrix[idx_mid][idx_last]:
        idx_bgn = idx_mid +1

    return row_binary_search(matrix, idx_bgn, idx_end, target)

def col_binary_search(matrix, idx_bgn, idx_end, target):
    if idx_bgn > idx_end:
        return False

    idx_mid = int((idx_bgn + idx_end) /2)

    if matrix[idx_mid] == target:
        return True
    elif target < matrix[idx_mid]:
        idx_end = idx_mid -1
    elif target > matrix[idx_mid]:
        idx_bgn = idx_mid +1

    return col_binary_search(matrix, idx_bgn, idx_end, target)

class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        rlt = row_binary_search(matrix, 0, len(matrix) - 1, target)
        b, idx_row = rlt

        if not b:
            return False

        return col_binary_search(matrix[idx_row], 0, len(matrix[0]) - 1, target)