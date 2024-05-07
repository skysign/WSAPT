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

    # for yy in range(mx_y):
    #     for xx in range(mx_x):
    #         map[yy][xx] = sys.maxsize
    map = copy.deepcopy(map_ori)
    map[y][x] = 0

    max_length = abs(y - dst_y) + abs(x - dst_x)

    queue = [[y, x, []]]
    while queue:
        y, x, pathes_ori = queue.pop(0)

        if len(pathes_ori) >= max_length:
            continue

        for arrow in darrows:
            dy, dx = dyxs[arrow]
            ny, nx = y + dy, x + dx

            # pathes = copy.deepcopy(pathes_ori)
            pathes = pathes_ori + []

            while 0 <= ny < mx_y and 0 <= nx < mx_x:
                l = len(pathes) + 1
                if map[ny][nx] > l and board[ny][nx] == 0:
                    map[ny][nx] = l
                    if map[dst_y][dst_x] != sys.maxsize:
                        if l < map[dst_y][dst_x] - 1:
                            queue.append([ny, nx, pathes + [arrow]])
                    else:
                        queue.append([ny, nx, pathes + [arrow]])
                    pathes.append(arrow)

                if board[ny][nx] > 0 or ((0 == ny or ny == mx_y - 1) and (dy != 0)) or ((0 == nx or nx == mx_x - 1) and dx != 0):
                    # local_pathes = copy.deepcopy(pathes)
                    local_pathes = pathes + []
                    while len(local_pathes) > 0 and local_pathes[len(local_pathes) - 1] == arrow:
                        local_pathes.pop()
                    local_pathes.append(double_arrows[arrow])

                    if map[ny][nx] > len(local_pathes):
                        map[ny][nx] = len(local_pathes)

                        if map[dst_y][dst_x] != sys.maxsize:
                            if len(local_pathes) < map[dst_y][dst_x] - 1:
                                queue.append([ny, nx, local_pathes])
                        else:
                            queue.append([ny, nx, local_pathes])
                    break

                ny, nx = ny + dy, nx + dx

    # [Enter] key 1+
    return 1 + map[dst_y][dst_x]
