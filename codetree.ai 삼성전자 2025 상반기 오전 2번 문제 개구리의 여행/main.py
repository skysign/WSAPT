import heapq
import sys


def dijkstra(board, src_r, src_c, dst_r, dst_c):
    N = len(board)
    distances = [[[sys.maxsize] * 6 for _ in range(N)] for _ in range(N)]

    ddrc = [
        [[-1, 0], [-2, 0], [-3, 0], [-4, 0], [-5, 0]],
        [[0, 1], [0, 2], [0, 3], [0, 4], [0, 5]],
        [[1, 0], [2, 0], [3, 0], [4, 0], [5, 0]],
        [[0, -1], [0, -2], [0, -3], [0, -4], [0, -5]]
    ]
    edges = []
    heapq.heappush(edges, (0, -1, src_r, src_c, 0))
    distances[src_r][src_c][1] = 0
    distances[src_r][src_c][2] = 0
    distances[src_r][src_c][3] = 0
    distances[src_r][src_c][4] = 0
    distances[src_r][src_c][5] = 0

    while edges:
        time_prev, jump_crnt, sr, sc, _ = heapq.heappop(edges)
        jump_crnt = -jump_crnt

        if distances[sr][sc][jump_crnt] < time_prev :
            continue

        if (sr, sc) == (dst_r, dst_c):
            return min(distances[dst_r][dst_c])

        for drc in ddrc:
            for dr, dc in drc:
                nr, nc = sr + dr, sc + dc
                if 0 <= nr < N and 0 <= nc < N:
                    if board[nr][nc] == 'S':
                        continue
                    if board[nr][nc] == '#':
                        break
                    time_new = 0
                    jump_new = max(abs(dr), abs(dc))

                    if jump_crnt > jump_new:
                        time_new += 1
                    elif jump_crnt < jump_new:
                        for j in range(jump_crnt + 1, jump_new + 1):
                            time_new += (j ** 2)

                    time_new += 1

                    if distances[nr][nc][jump_new] > time_prev + time_new:
                        edge_new = [time_prev + time_new, -jump_new, nr, nc, time_new]
                        distances[nr][nc][jump_new] = time_prev + time_new
                        heapq.heappush(edges, edge_new)

    answer = min(distances[dst_r][dst_c])
    if answer == sys.maxsize:
        return -1

    return answer


def solve():
    N = int(input())
    board = [['#' for _ in range(N + 1)] for _ in range(N + 1)]

    for r in range(1, N + 1):
        line = input()
        for c in range(1, N + 1):
            board[r][c] = line[c - 1]

    M = int(input())
    for _ in range(M):
        src_r, src_c, dst_r, dst_c = map(int, input().split())
        answer = dijkstra(board, src_r, src_c, dst_r, dst_c)
        print(answer)


if __name__ == '__main__':
    solve()
