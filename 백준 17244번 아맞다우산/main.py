import sys
from collections import defaultdict


def solve():
    N, M = map(int, sys.stdin.readline().split())
    board = []
    distance_sum = 0
    for _ in range(M):
        board.append(list(sys.stdin.readline().strip()))

    things = defaultdict(int)
    begin_r, begin_c, end_r, end_c = 0, 0, 0, 0

    for r in range(M):
        for c in range(N):
            if board[r][c] == 'S':
                begin_r, begin_c = r, c
            elif board[r][c] == 'E':
                end_r, end_c = r, c
            elif board[r][c] == 'X':
                things[(r, c)] = 0

    while list(things.keys()):
        next_r, next_c, distance = next_thing(N, M, begin_r, begin_c, things, board, [[sys.maxsize] * N for _ in range(M)])
        distance_sum += distance
        begin_r, begin_c = next_r, next_c

    things[(end_r, end_c)] = 0
    _, _, distance = next_thing(N, M, begin_r, begin_c, things, board, [[sys.maxsize] * N for _ in range(M)])
    distance_sum += distance

    print(distance_sum)


def next_thing(N, M, sr, sc, things, board, visited):
    drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    queue = [[sr, sc, 0]]
    visited[sr][sc] = 0

    while queue:
        sr, sc, distance = queue.pop(0)
        for dr, dc in drc:
            nr, nc = sr + dr, sc + dc
            if 0 <= nr < M and 0 <= nc < N and board[nr][nc] != '#' and distance + 1 < visited[nr][nc]:
                visited[nr][nc] = distance + 1
                queue.append([nr, nc, distance + 1])

                if (nr, nc) in things.keys():
                    things[(nr, nc)] = distance + 1

    tmp = []
    for (r, c) in things.keys():
        tmp.append([things[(r, c)], r, c])

    tmp.sort()
    d, r, c = tmp[0]
    del things[(r, c)]
    return r, c, d


if __name__ == '__main__':
    solve()
