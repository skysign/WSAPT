import heapq
from collections import deque
from sys import stdout


class Micro:
    def __init__(self, n, r1, c1, r2, c2):
        self.n = n
        self.r1 = r1
        self.c1 = c1
        self.r2 = r2
        self.c2 = c2
        self.cells = {}
        for r in range(r1, r2 + 1):
            for c in range(c1, c2 + 1):
                nr, nc = r - r1, c - c1
                self.cells[(nr, nc)] = 0

    def __lt__(self, other):
        return self.n < other.n


def init_board(N):
    board = [[[0, 0, 0] for _ in range(N + 2)] for _ in range(N + 2)]

    for i in range(N + 2):
        board[0][i] = [-1, 0, 0]
        board[N + 1][i] = [-1, 0, 0]
        board[i][0] = [-1, 0, 0]
        board[i][N + 1] = [-1, 0, 0]

    return board


def can_place_micro(board, sr, sc, micro):
    N = len(board)

    for r, c in micro.cells:
        nr, nc = sr + r, sc + c
        if 1 <= nr < N - 1 and 1 <= nc < N - 1 and board[nr][nc][0] == 0:
            continue
        else:
            return False

    return True


def place_board(board, sr, sc, micro):
    micro.r1 = sr
    micro.c1 = sc

    for r, c in micro.cells:
        nr, nc = sr + r, sc + c
        board[nr][nc][0] = micro.n
        board[nr][nc][1] = r
        board[nr][nc][2] = c


def tetris_micro(board, micro):
    N = len(board)
    candidates = []

    for r in range(N - 1, 0, -1):
        for c in range(1, N):
            if can_place_micro(board, r, c, micro):
                heapq.heappush(candidates, (c, r))

    c, r = heapq.heappop(candidates)
    place_board(board, r, c, micro)


def count_cells_by_bfs(micro):
    visited = set()
    sr, sc = list(micro.cells.keys())[0]
    queue = deque([[sr, sc]])
    visited.add((sr, sc))
    drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]

    while queue:
        sr, sc = queue.popleft()

        for dr, dc in drc:
            nr, nc = sr + dr, sc + dc
            if (nr, nc) not in visited and (nr, nc) in micro.cells.keys():
                queue.append([nr, nc])
                visited.add((nr, nc))

    return len(visited)


def find_adjacents(board, micro):
    drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]
    local_adjacents = set()

    for r, c in micro.cells.keys():
        sr, sc = r + micro.r1, c + micro.c1
        for dr, dc in drc:
            nr, nc = sr + dr, sc + dc
            if board[nr][nc][0] > 0 and board[nr][nc][0] != micro.n:
                local_adjacents.add(board[nr][nc][0])

    return local_adjacents


def solve():
    N, Q = map(int, input().split())
    micros = {}
    micro_order_heap = []
    board_save = init_board(N)

    for n in range(1, Q + 1):
        board = board_save

        c1, r1, c2, r2 = map(int, input().split())
        r1, c1 = r1 + 1, c1 + 1
        micro = Micro(n, r1, c1, r2, c2)
        micros[n] = micro

        for r, c in micro.cells.keys():
            nr, nc = r + r1, c + c1
            if board[nr][nc][0] != 0:
                micro_overwritten = micros[board[nr][nc][0]]
                del micro_overwritten.cells[(board[nr][nc][1], board[nr][nc][2])]

            board[nr][nc][0] = micro.n
            board[nr][nc][1] = r
            board[nr][nc][2] = c

        # 둘로 나뉘어진 미생물 찾아서 삭제하기
        keys = list(micros.keys())
        for key in keys:
            micro = micros[key]

            # 모든 cell이 다른 cell로 overwrite되어 남아 있는 cell이 없음
            if len(micro.cells.keys()) == 0:
                del micros[micro.n]
                continue

            l = count_cells_by_bfs(micro)

            # 둘로 나뉘어저 있음
            if l != len(micro.cells.keys()):
                del micros[micro.n]
                # board에서 미생물의 모든 cell 삭제하기
                for r, c in micro.cells.keys():
                    nr, nc = r + micro.r1, c + micro.c1
                    board[nr][nc][0] = 0
                    board[nr][nc][1] = 0
                    board[nr][nc][2] = 0

        # cell수가 많은 순서로 미생물 정렬하기
        for key in micros.keys():
            micro = micros[key]
            heapq.heappush(micro_order_heap, (-len(micro.cells.keys()), micro))

        board = init_board(N)

        while micro_order_heap:
            _, micro = heapq.heappop(micro_order_heap)
            tetris_micro(board, micro)

        board_save = board

        # 인접한 미생물 찾기
        answer = 0
        adjacents = set()

        for key in micros.keys():
            micro = micros[key]
            local_adjacents = find_adjacents(board, micro)

            for other_adjacent in local_adjacents:
                if (other_adjacent, micro.n) not in adjacents:
                    adjacents.add((micro.n, other_adjacent))

        for pair in list(adjacents):
            answer += (len(micros[pair[0]].cells) * len(micros[pair[1]].cells))

        print(answer)
        stdout.flush()


if __name__ == '__main__':
    solve()
