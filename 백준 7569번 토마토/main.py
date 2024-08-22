import sys
from typing import List, Deque
from collections import deque


def solve():
    M, N, H = map(int, sys.stdin.readline().strip().split(' '))
    maps: List[List[List[int]]] = [[[0 for _ in range(M)] for _ in range(N)] for _ in range(H)]
    queue: Deque = deque()

    for height_idx in range(H):
        for row_idx in range(N):
            line = list(map(int, sys.stdin.readline().strip().split(' ')))
            for col_idx in range(M):
                maps[height_idx][row_idx][col_idx] = line[col_idx]

                if maps[height_idx][row_idx][col_idx] == 1:
                    queue.append([height_idx, row_idx, col_idx, 0])

    answer = bfs(maps, queue, H, N, M)

    for height_idx in range(H):
        for row_idx in range(N):
            for col_idx in range(M):
                if maps[height_idx][row_idx][col_idx] == 0:
                    print(-1)
                    return

    print(answer)


def bfs(maps: List[List[List[int]]], queue: Deque, height_max, row_max, col_max):
    answer: int = 0
    dhrc = [[-1, 0, 0], [1, 0, 0], [0, -1, 0], [0, 1, 0], [0, 0, -1], [0, 0, 1]]

    while queue:
        hs, rs, cs, time = queue.popleft()
        for dh, dr, dc in dhrc:
            nh, nr, nc = hs + dh, rs + dr, cs + dc
            if (0 <= nh < height_max) \
                    and (0 <= nr < row_max) \
                    and (0 <= nc < col_max):
                if maps[nh][nr][nc] == 0:
                    maps[nh][nr][nc] = 1
                    queue.append([nh, nr, nc, time + 1])
                    answer = max(answer, time + 1)

    return answer


if __name__ == '__main__':
    solve()
