import heapq
import sys
from collections import defaultdict


def dijkstra(board, src_r, src_c, dst_r, dst_c, edges_raw):
    N = len(board)
    distances = [[[sys.maxsize] * 6 for _ in range(N)] for _ in range(N)]

    edges = []
    distances[src_r][src_c][1] = 0
    distances[src_r][src_c][2] = 0
    distances[src_r][src_c][3] = 0
    distances[src_r][src_c][4] = 0
    distances[src_r][src_c][5] = 0

    heapq.heappush(edges, [0, 1, src_r, src_c])

    while edges:
        edge = heapq.heappop(edges)
        time_total, jump_new, nr, nc = edge

        if time_total > distances[nr][nc][jump_new]:
            continue

        if (nr, nc) == (dst_r, dst_c):
            return time_total

        for edge in edges_raw[(nr, nc, jump_new)]:
            ntime, njump_new, nnr, nnc = edge
            ntime_total = ntime + time_total
            if distances[nnr][nnc][njump_new] > ntime_total:
                distances[nnr][nnc][njump_new] = ntime_total
                heapq.heappush(edges, (ntime_total, njump_new, nnr, nnc))

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

    ddrc = [
        [[-1, 0], [-2, 0], [-3, 0], [-4, 0], [-5, 0]],
        [[0, 1], [0, 2], [0, 3], [0, 4], [0, 5]],
        [[1, 0], [2, 0], [3, 0], [4, 0], [5, 0]],
        [[0, -1], [0, -2], [0, -3], [0, -4], [0, -5]]
    ]
    edges_raw = defaultdict(list)
    JUMP_MAX = 5

    for sr in range(1, N + 1):
        for sc in range(1, N + 1):
            if board[sr][sc] == '.':
                for drc in ddrc:
                    for dr, dc in drc:
                        nr, nc = sr + dr, sc + dc
                        if 0 <= nr < N + 1 and 0 <= nc < N + 1:
                            if board[nr][nc] == 'S':
                                continue
                            if board[nr][nc] == '#':
                                break

                            jump_new = max(abs(dr), abs(dc))

                            for jump_prev in range(1, JUMP_MAX + 1):
                                time = 0

                                if jump_prev > jump_new:
                                    time += 1
                                elif jump_prev < jump_new:
                                    for j in range(jump_prev + 1, jump_new + 1):
                                        time += (j ** 2)

                                time += 1
                                edges_raw[(sr, sc, jump_prev)].append((time, jump_new, nr, nc))

    M = int(input())
    for _ in range(M):
        src_r, src_c, dst_r, dst_c = map(int, input().split())
        answer = dijkstra(board, src_r, src_c, dst_r, dst_c, edges_raw)
        print(answer)


if __name__ == '__main__':
    solve()
