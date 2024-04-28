import copy
from typing import List

dyx = [[-1, 0], [0, 1], [1, 0], [0, -1]]


def solution(board: List[List[int]], aloc: List[int], bloc: List[int]) -> int:
    answer = a_move(board, aloc[0], aloc[1], 0, bloc[0], bloc[1], 0)
    return answer[1]


def a_move(board: List[List[int]], ay, ax, adepth, by, bx, bdepth):
    if board[ay][ax] == 0:
        return [False, adepth + bdepth]

    loses = [adepth + bdepth]
    wins = []

    for dy, dx in dyx:
        ny, nx = dy + ay, dx + ax
        if is_movable(board, ny, nx):
            new_board = copy.deepcopy(board)
            new_board[ay][ax] = 0
            [win, depth] = b_move(new_board, ny, nx, adepth + 1, by, bx, bdepth)
            if win:
                loses.append(depth)
            else:
                wins.append(depth)

    if wins:
        wins.sort()
        return [True, wins[0]]

    loses.sort(reverse=True)
    return [False, loses[0]]


def b_move(board: List[List[int]], ay, ax, adepth, by, bx, bdepth):
    if board[by][bx] == 0:
        return [False, adepth + bdepth]

    loses = [adepth + bdepth]
    wins = []

    for dy, dx in dyx:
        ny, nx = dy + by, dx + bx
        if is_movable(board, ny, nx):
            new_board = copy.deepcopy(board)
            new_board[by][bx] = 0
            [win, depth] = a_move(new_board, ay, ax, adepth, ny, nx, bdepth + 1)
            if win:
                loses.append(depth)
            else:
                wins.append(depth)

    if wins:
        wins.sort()
        return [True, wins[0]]

    loses.sort(reverse=True)
    return [False, loses[0]]


def is_movable(board, y, x):
    if 0 <= y < len(board) and 0 <= x < len(board[0]) and board[y][x] == 1:
        return True

    return False
