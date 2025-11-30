import sys
import itertools


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    board = []

    for _ in range(N):
        board.append(sys.stdin.readline().strip().split())

    virus_pos = []
    for r in range(N):
        for c in range(N):
            if board[r][c] == '2':
                virus_pos.append([r, c])

    min_second = sys.maxsize
    nCr = list(itertools.combinations(virus_pos, M))

    for pos in nCr:
        seconds = bfs(N, board, pos)
        min_second = min(min_second, seconds)

    print(min_second)


def bfs(N, board, pos):
    drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    visited = [[sys.maxsize] * N for _ in range(N)]
    rtn = 0

    for sr, sc in pos:
        queue = [[sr, sc, 0]]
        visited[sr][sc] = 0

        while queue:
            sr, sc, second = queue.pop(0)
            for dr, dc in drc:
                nr, nc = sr + dr, sc + dc
                if 0 <= nr < N and 0 <= nc < N and board[nr][nc] != '1' and second + 1 < visited[nr][nc]:
                    visited[nr][nc] = second + 1
                    queue.append([nr, nc, second + 1])

    for r in range(N):
        for c in range(N):
            if board[r][c] == '0' or board[r][c] == '2':
                if visited[r][c] == sys.maxsize:
                    return -1
                else:
                    rtn = max(rtn, visited[r][c])

    return rtn

if __name__ == '__main__':
    solve()
