from typing import List, Dict
from collections import defaultdict

dict_key = 2


def solution(arr: List[List[int]]):
    global dict_key

    len_row = len(arr)
    len_col = len(arr[0])
    dict = defaultdict(int)

    rec(0, 0, len_row, len_col, arr, dict)

    return [dict[0], dict[1]]


def rec(bgn_row, bgn_col, len_row, len_col, arr: List[List[int]], dict: Dict):
    if is_01(1, bgn_row, bgn_col, len_row, len_col, arr):
        dict[1] += 1
    elif is_01(0, bgn_row, bgn_col, len_row, len_col, arr):
        dict[0] += 1
    else:
        if len_row >= 2:
            rec(bgn_row, bgn_col,
                len_row // 2, len_col // 2, arr, dict)
            rec(bgn_row + len_row // 2, bgn_col,
                len_row // 2, len_col // 2, arr, dict)
            rec(bgn_row, bgn_col + len_col // 2,
                len_row // 2, len_col // 2, arr, dict)
            rec(bgn_row + len_row // 2, bgn_col + len_col // 2,
                len_row // 2, len_col // 2, arr, dict)


def is_01(n, bgn_row, bgn_col, len_row, len_col, arr):
    for row in range(bgn_row, bgn_row + len_row):
        for col in range(bgn_col, bgn_col + len_col):
            if n != arr[row][col]:
                return False

    return True
