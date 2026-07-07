import heapq


def dijkstra(board, src_r, src_c, dst_r, dst_c):
    ddrc = [[[-1, 0], [-2, 0], [-3, 0], [-4, 0], [-5, 0]],
            [[1, 0], [2, 0], [3, 0], [4, 0], [5, 0]],
            [[0, 1], [0, 2], [0, 3], [0, 4], [0, 5]],
            [[0, -1], [0, -2], [0, -3], [0, -4], [0, -5]]]

    N = len(board)
    # node [time_node, -jump_crnt, r, c, time_total]
    heap = []
    heapq.heappush(heap, [0, -1, src_r, src_c, 0])
    dijkstra = {}
    dijkstra[(src_r, src_c)] = [0, 1, 0]

    while heap:
        time_crnt, jump_crnt, src_r, src_c, time_total = heapq.heappop(heap)
        jump_crnt = -jump_crnt

        for drc in ddrc:
            for dr, dc in drc:
                nr, nc = src_r + dr, src_c + dc
                if 1 <= nr < N and 1 <= nc < N:
                    if board[nr][nc] == '#':
                        break
                    if board[nr][nc] == '.':
                        jump_new = max(abs(dr), abs(dc))
                        time_new = 0

                        if jump_new != jump_crnt:
                            if jump_new > jump_crnt:
                                for j in range(jump_crnt + 1, jump_new + 1):
                                    time_new += (j * j)
                                time_new += 1
                            else: # 점프력을 감소 + 점프하는 시간
                                time_new += (1 + 1)
                        else: # jump_new == jump_crnt
                            time_new += 1

                        dijkstra_node = [time_new, -jump_new, nr, nc, time_total + time_new]

                        # if dst_r == nr and dst_c == nc:
                        #     return time_total + time_new

                        if (nr, nc) in dijkstra.keys():
                            node_legacy = dijkstra[(nr, nc)]

                            if dijkstra_node[4] < node_legacy[2]:
                                dijkstra[(nr, nc)] = [time_new, jump_new, time_total + time_new]
                                heapq.heappush(heap, dijkstra_node)
                            elif dijkstra_node[4] == node_legacy[2] and dijkstra_node[1] < -node_legacy[1]:
                                dijkstra[(nr, nc)] = [time_new, jump_new, time_total + time_new]
                                heapq.heappush(heap, dijkstra_node)
                        else:
                            dijkstra[(nr, nc)] = [time_new, jump_new, time_total + time_new]
                            heapq.heappush(heap, dijkstra_node)
                else:
                    break

    if (dst_r, dst_c) in dijkstra.keys():
        return dijkstra[(dst_r, dst_c)][2]

    return -1


def solve():
    N = int(input())
    board = [['S' for _ in range(N + 1)] for _ in range(N + 1)]

    for r in range(1, N + 1):
        c = 1
        line = input()
        for b in line:
            board[r][c] = b
            c += 1

    Q = int(input())
    for _ in range(Q):
        r1, c1, r2, c2 = map(int, input().split())
        answer = dijkstra(board, r1, c1, r2, c2)
        print(answer)


if __name__ == '__main__':
    solve()
