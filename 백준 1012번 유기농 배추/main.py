import sys
from typing import List


def solve():
    T = int(sys.stdin.readline().strip())
    drcs = [[1, 0], [0, 1], [-1, 0], [0, -1]]

    for _ in range(T):
        answer: int = 0
        M, N, cabbage_cnt = map(int, sys.stdin.readline().strip().split(' '))
        board: List[List[int]] = [[0 for _ in range(N)] for _ in range(M)]

        for _ in range(cabbage_cnt):
            row, col = map(int, sys.stdin.readline().strip().split(' '))
            board[row][col] = 1

        for col in range(N):
            for row in range(M):
                if board[row][col] == 1:
                    answer += 1
                    queue: List[List[int]] = [[row, col]]
                    board[row][col] = 0

                    while queue:
                        r, c = queue.pop(0)

                        for dr, dc in drcs:
                            nr, nc = r + dr, c + dc

                            if ((0 <= nr < M) and (0 <= nc < N)) and (board[nr][nc] == 1):
                                queue.append([nr, nc])
                                board[nr][nc] = 0

        print(answer)


if __name__ == '__main__':
    solve()
