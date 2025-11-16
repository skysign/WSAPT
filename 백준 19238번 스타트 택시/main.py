import copy
import sys
from collections import deque


def solve():
    N, M, fuel = map(int, sys.stdin.readline().split())
    board = []
    board.append([1 for _ in range(N + 1)])

    for _ in range(N):
        board.append([1] + list(map(int, sys.stdin.readline().split())))

    taxi_r, taxi_c = map(int, sys.stdin.readline().split())

    passenger = {}
    for _ in range(M):
        fr_r, fr_c, to_r, to_c = map(int, sys.stdin.readline().split())
        passenger[(fr_r, fr_c)] = (to_r, to_c)

    for _ in range(M):
        drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        board2 = [[sys.maxsize] * (N + 1) for _ in range(N + 1)]
        queue = deque([[taxi_r, taxi_c, 0]])
        board2[taxi_r][taxi_c] = 0

        while queue:
            sr, sc, depth = queue.popleft()
            for dr, dc in drc:
                nr, nc = sr + dr, sc + dc
                if 0 <= nr <= N and 0 <= nc <= N and board[nr][nc] != 1:
                    if depth + 1 < board2[nr][nc]:
                        board2[nr][nc] = depth + 1
                        queue.append([nr, nc, depth + 1])

        candidates = []
        for fr_r, fr_c in passenger.keys():
            length = board2[fr_r][fr_c]
            candidates.append([length, fr_r, fr_c])

        candidates.sort()
        length, fr_r, fr_c = candidates[0]
        fuel -= length
        if fuel <= 0:
            print(-1)
            return

        board2 = [[sys.maxsize] * (N + 1) for _ in range(N + 1)]
        queue = deque([[fr_r, fr_c, 0]])
        board2[fr_r][fr_c] = 0

        while queue:
            sr, sc, depth = queue.popleft()
            for dr, dc in drc:
                nr, nc = sr + dr, sc + dc
                if 0 <= nr <= N and 0 <= nc <= N and board[nr][nc] != 1:
                    if depth + 1 < board2[nr][nc]:
                        board2[nr][nc] = depth + 1
                        queue.append([nr, nc, depth + 1])

        to_r, to_c = passenger[(fr_r, fr_c)]
        length = board2[to_r][to_c]
        fuel -= length

        if fuel >= 0:
            fuel += (length * 2)
        else:
            print(-1)
            return

        del passenger[(fr_r, fr_c)]
        taxi_r, taxi_c = to_r, to_c

    print(fuel)

if __name__ == '__main__':
    solve()
