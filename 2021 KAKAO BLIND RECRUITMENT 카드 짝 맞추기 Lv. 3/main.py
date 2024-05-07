import sys
import copy
from typing import List
from collections import defaultdict

map_ori = []
mx_x = 0
mx_y = 0
darrows = ['↓', '↑', '→', '←']
# →, ←, ↑, ↓
dyxs = {'→': [0, 1], '←': [0, -1], '↑': [-1, 0], '↓': [1, 0]}
# ⇇    ⇈    ⇉    ⇊
double_arrows = {'→': '⇉', '←': '⇇', '↑': '⇈', '↓': '⇊'}


def solution(board: List[List[int]], r: int, c: int):
    global mx_x, mx_y, map_ori

    mx_y = len(board)
    mx_x = len(board[0])
    map_ori = [[sys.maxsize for _ in range(mx_x)] for _ in range(mx_y)]

    dict = defaultdict(list)

    for y in range(mx_y):
        for x in range(mx_x):
            if board[y][x] != 0:
                dict[board[y][x]].append([y, x])

    pairs = []
    for key in list(dict.keys()):
        pairs.append([key, dict[key]])

    answer = rec(board, r, c, pairs, 0)
    return answer


def rec(board, sy, sx, pairs: List[List[List[int]]], cnt_key):
    if len(pairs) == 0:
        return cnt_key

    mn = sys.maxsize

    for idx in range(len(pairs)):
        [card_number, [[y1, x1], [y2, x2]]] = pairs.pop(idx)

        cnt1 = count_keys(board, sy, sx, y1, x1)
        board[y1][x1] = 0
        cnt1 += count_keys(board, y1, x1, y2, x2)
        board[y2][x2] = 0
        local_count1 = rec(board, y2, x2, pairs, cnt_key + cnt1)
        board[y1][x1] = card_number
        board[y2][x2] = card_number

        cnt2 = count_keys(board, sy, sx, y2, x2)
        board[y2][x2] = 0
        cnt2 += count_keys(board, y2, x2, y1, x1)
        board[y1][x1] = 0
        local_count2 = rec(board, y1, x1, pairs, cnt_key + cnt2)
        board[y1][x1] = card_number
        board[y2][x2] = card_number

        pairs.insert(idx, [card_number, [[y1, x1], [y2, x2]]])

        mn = min(mn, local_count1, local_count2)

    return mn


def count_keys(board, y, x, dst_y, dst_x):
    if y == dst_y and x == dst_x:
        return 1

    global mx_x, mx_y, map_ori, darrows, dyxs, double_arrows

    map = copy.deepcopy(map_ori)
    map[y][x] = 0

    max_length = abs(y - dst_y) + abs(x - dst_x)

    queue = [[y, x, 0]]
    while queue:
        y, x, crnt_length = queue.pop(0)

        if crnt_length >= max_length:
            continue

        for arrow in darrows:
            dy, dx = dyxs[arrow]
            ny, nx = y + dy, x + dx

            if not (0 <= ny < mx_y and 0 <= nx < mx_x):
                continue

            if dy != 0:
                while 0 <= ny < mx_y:
                    if (board[ny][nx] > 0 or ((0 == ny or ny == mx_y - 1))):
                        if map[ny][nx] > crnt_length + 1:
                            map[ny][nx] = crnt_length + 1
                            queue.append([ny, nx, crnt_length + 1])
                        break
                    ny = ny + dy

            if dx != 0:
                while 0 <= nx < mx_x:
                    if (board[ny][nx] > 0 or ((0 == nx or nx == mx_x - 1))):
                        if map[ny][nx] > crnt_length + 1:
                            map[ny][nx] = crnt_length + 1
                            queue.append([ny, nx, crnt_length + 1])
                        break
                    nx = nx + dx

            ny, nx = y + dy, x + dx
            if map[ny][nx] > crnt_length + 1:
                map[ny][nx] = crnt_length + 1
                queue.append([ny, nx, crnt_length + 1])

    # [Enter] key 1+
    return 1 + map[dst_y][dst_x]
