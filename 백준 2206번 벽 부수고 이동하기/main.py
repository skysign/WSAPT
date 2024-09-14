import copy
import sys
from collections import deque


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    maps = []
    visited0 = []

    for _ in range(N):
        line = sys.stdin.readline().strip()
        maps.append([int(c) for c in line])
        visited0.append([sys.maxsize for _ in range(M)])

    visited = []
    visited.append(visited0)
    visited1 = copy.deepcopy(visited0)
    visited.append(visited1)

    def bfs():
        dyxs = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        queue = deque()
        queue.append([0, 0, 1])
        visited[1][0][0] = 1
        visited[0][0][0] = 1

        while queue:
            sy, sx, busugo = queue.popleft()

            for dy, dx in dyxs:
                ny, nx = sy + dy, sx + dx
                if 0 <= ny < N and 0 <= nx < M:
                    if maps[ny][nx] == 0:
                        if visited[busugo][ny][nx] > visited[busugo][sy][sx] + 1:
                            queue.append([ny, nx, busugo])
                            visited[busugo][ny][nx] = visited[busugo][sy][sx] + 1
                    elif maps[ny][nx] == 1 and busugo > 0:
                        if visited[busugo - 1][ny][nx] > visited[busugo][sy][sx] + 1:
                            queue.append([ny, nx, busugo - 1])
                            visited[busugo - 1][ny][nx] = visited[busugo][sy][sx] + 1

    bfs()

    if visited[0][N - 1][M - 1] == sys.maxsize and visited[1][N - 1][M - 1] == sys.maxsize:
        print(-1)
    else:
        print(min(visited[0][N - 1][M - 1], visited[1][N - 1][M - 1]))


if __name__ == '__main__':
    solve()
