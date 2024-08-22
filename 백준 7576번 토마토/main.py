import sys
from collections import deque
from typing import List, Deque


def solve():
    M, N = map(int, sys.stdin.readline().strip().split(' '))
    maps: List[List[int]] = [[0 for _ in range(M)] for _ in range(N)]
    queue: Deque = deque()

    for row_idx in range(N):
        line = list(map(int, sys.stdin.readline().strip().split(' ')))
        for col_idx in range(M):
            maps[row_idx][col_idx] = line[col_idx]
            if maps[row_idx][col_idx] == 1:
                queue.append([row_idx, col_idx, 0])

    answer = bfs(queue, maps, N, M)

    for row_idx in range(N):
        for col_idx in range(M):
            if maps[row_idx][col_idx] == 0:
                print(-1)
                return

    print(answer)


def bfs(queue: Deque, maps: List[List[int]], row_max, col_max):
    answer: int = 0
    drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]

    while queue:
        rs, cs, v = queue.popleft()

        for dr, dc in drc:
            nr, nc = rs + dr, cs + dc

            if (0 <= nr < row_max) and (0 <= nc < col_max):
                if maps[nr][nc] == 0:
                    maps[nr][nc] = 1
                    queue.append([nr, nc, v + 1])
                    answer = max(answer, v + 1)

    return answer


if __name__ == '__main__':
    solve()
