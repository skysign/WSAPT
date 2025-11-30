import copy
import sys


def solve():
    N = int(sys.stdin.readline())
    board = []

    for _ in range(N):
        board.append(list(sys.stdin.readline().strip()))

    normal_color = [['R'], ['G'], ['B']]
    rg_color = [['R', 'G'], ['B']]

    board_rg = copy.deepcopy(board)

    rlt_normal = 0
    rlt_rg = 0

    for r in range(N):
        for c in range(N):
            if board[r][c] != '0':
                color = []
                for col in normal_color:
                    if board[r][c] in col:
                        color = col
                        break

                bfs(N, r, c, board, color)
                rlt_normal += 1

    for r in range(N):
        for c in range(N):
            if board_rg[r][c] != '0':
                color = []
                for col in rg_color:
                    if board_rg[r][c] in col:
                        color = col
                        break

                bfs(N, r, c, board_rg, color)
                rlt_rg += 1

    print(f'{rlt_normal} {rlt_rg}')


def bfs(N, sr, sc, board, color):
    drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    board[sr][sc] = '0'
    queue = [[sr, sc]]

    while queue:
        sr, sc = queue.pop(0)

        for dr, dc in drc:
            nr, nc = sr + dr, sc + dc
            if 0 <= nr < N and 0 <= nc < N and board[nr][nc] in color:
                board[nr][nc] = '0'
                queue.append([nr, nc])


if __name__ == '__main__':
    solve()
