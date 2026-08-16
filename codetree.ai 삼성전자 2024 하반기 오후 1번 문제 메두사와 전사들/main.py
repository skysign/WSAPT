from collections import deque


def medusa_see(N, direction, hr, hc):
    # direction은 상(0), 하(1), 좌(2), 우(3) 중 한개의 값을 가짐
    # 직선 (-1, 0), (1, 0), (0, -1), (0, 1)
    # 메듀사가 바라보는 왼쪽 삼각형
    # -1, -1
    # 메듀사가 바라보는 오른쪽 삼각형
    # -1, 1
    visited = [[False] * N for _ in range(N)]
    board = [[-1] * N for _ in range(N)]
    delta_medusa = [
        [[(-1, 0)], [(-1, 0), (-1, -1)], [(-1, 0), (-1, 1)]],  # 상, 직선, 대각 왼쪽, 대각 오른쪽
        [[(1, 0)], [(1, 0), (1, 1)], [(1, 0), (1, -1)]],  # 하,
        [[(0, -1)], [(0, -1), (-1, -1)], [(0, -1), (1, -1)]],  # 좌,
        [[(0, 1)], [(0, 1), (1, 1)], [(0, 1), (-1, 1)]]  # 우
    ]

    jlr = [0, 1, 2]  # 직선, 대각 왼쪽, 대각 오른쪽

    for idx_jlr in jlr:
        delta_rc = delta_medusa[direction][idx_jlr]

        dq = deque()
        dq.append((hr, hc))

        while dq:
            sr, sc = dq.popleft()
            for dr, dc in delta_rc:
                nr, nc = sr + dr, sc + dc
                if 0 <= nr < N and 0 <= nc < N and visited[nr][nc] == False:
                    visited[nr][nc] = True
                    board[nr][nc] = idx_jlr
                    dq.append((nr, nc))

    return board


def warrior_makes_shade(N, direction, Ms, board):
    delta_warrior = [
        [[(-1, 0)], [(-1, 0), (-1, -1)], [(-1, 0), (-1, 1)]],  # 상, 직선, 대각 왼쪽, 대각 오른쪽
        [[(1, 0)], [(1, 0), (1, 1)], [(1, 0), (1, -1)]],  # 하,
        [[(0, -1)], [(0, -1), (-1, -1)], [(0, -1), (1, -1)]],  # 좌,
        [[(0, 1)], [(0, 1), (1, 1)], [(0, 1), (-1, 1)]]  # 우
    ]

    for mr, mc in Ms:
        if board[mr][mc] == -1:
            continue

        idx_jlr = board[mr][mc]
        dq = deque()
        dq.append((mr, mc))
        delta_rc = delta_warrior[direction][idx_jlr]

        while dq:
            sr, sc = dq.popleft()
            for dr, dc in delta_rc:
                nr, nc = sr + dr, sc + dc
                if 0 <= nr < N and 0 <= nc < N and board[nr][nc] != -1:
                    board[nr][nc] = -1
                    dq.append((nr, nc))

    Ms_not_seen = []
    Ms_seen = []

    for mr, mc in Ms:
        if board[mr][mc] == -1:
            Ms_not_seen.append([mr, mc])
        else:
            Ms_seen.append([mr, mc])

    return board, Ms_not_seen, Ms_seen


def manhattan_distance(ar, ac, br, bc):
    return abs(ar - br) + abs(ac - bc)


def move_warrior(N, board_medusa_see, mr, mc, hr, hc, drc):
    md = manhattan_distance(hr, hc, mr, mc)

    for dr, dc in drc:
        nr, nc = mr + dr, mc + dc
        if 0 <= nr < N and 0 <= nc < N and board_medusa_see[nr][nc] == -1:
            if md > manhattan_distance(hr, hc, nr, nc):
                mr_new, mc_new = nr, nc
                return mr_new, mc_new, 1

    return mr, mc, 0


def solve():
    N, M = map(int, input().split())
    # 메듀사 위치, 공원 위치
    hr, hc, er, ec = map(int, input().split())
    Ms = list(map(int, input().split()))
    board = []

    for _ in range(N):
        row = list(map(int, input().split()))
        board.append(row)

    tmp = []
    for i in range(0, len(Ms), 2):
        mir, mic = Ms[i], Ms[i + 1]
        tmp.append([mir, mic])
        del mir
        del mic

    Ms = tmp
    dist = [[-1] * N for _ in range(N)]
    drc = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # 상하좌우
    drc2 = [(0, -1), (0, 1), (-1, 0), (1, 0)]  # 좌우상하

    dq = deque()
    dq.append((er, ec))
    dist[er][ec] = 0

    # 공원에서부터 메듀사의 처음 위치까지 BFS로 방문
    while dq:
        sr, sc = dq.popleft()
        for dr, dc in drc:
            nr, nc = sr + dr, sc + dc
            if 0 <= nr < N and 0 <= nc < N and board[nr][nc] == 0 and dist[nr][nc] == -1:
                dist[nr][nc] = dist[sr][sc] + 1
                dq.append((nr, nc))

    # 공원에서 부터 메듀사의 처음 위치까지 도달할 수 있는 경로 없으면
    if dist[hr][hc] == -1:
        print(-1)
        return

    while not (hr == er and hc == ec):
        # 메듀사 한칸 이동
        for dr, dc in drc:
            nr, nc = hr + dr, hc + dc
            if 0 <= nr < N and 0 <= nc < N and dist[nr][nc] != -1 and dist[hr][hc] > dist[nr][nc]:
                hr, hc = nr, nc
                break

        # 공원에 도착했으면
        if hr == er and hc == ec:
            print(0)
            return

        # disappearing_warrior = 0
        # 메두사가 이동한 칸에 전사가 있을 경우 전사는 메두사에게 공격을 받고 사라집니다.
        if [hr, hc] in Ms:
            Ms.remove([hr, hc])
            # disappearing_warrior += 1

        warrior_step = 0
        warrior_stone = 0
        warrior_attack = 0

        board_medusa_see_best = None
        Ms_not_seen_best, Ms_seen_best = [], []

        # 메두사는 상, 하, 좌, 우 하나의 방향을 선택해 바라봅니다.
        for direction in range(4):
            # 메두사는 바라보는 방향으로 90도의 시야각을 가지며, 시야각 범위 안에 있는 전사들을 볼 수 있습니다.
            board_medusa_see = medusa_see(N, direction, hr, hc)
            # 메두사의 시야각 안에 들어와있지만 다른 전사에 가려진 전사의 경우 메두사에게 보이지 않습니다.
                # Ms_seen 시야각 안에 들어와 있는 전사
                # Ms_not_seen 다른 전사에 가려진 전사, 또는 시야각 밖에 있는 전사, 즉 이동 가능한 전사
            board_medusa_see, Ms_not_seen, Ms_seen = warrior_makes_shade(N, direction, Ms, board_medusa_see)

            if warrior_stone < len(Ms_seen):
                warrior_stone = len(Ms_seen)
                board_medusa_see_best = board_medusa_see
                Ms_not_seen_best, Ms_seen_best = Ms_not_seen, Ms_seen

        tmp = []
        for mr, mc in Ms_not_seen_best:
            mr_new, mc_new, cnt_step = move_warrior(N, board_medusa_see_best, mr, mc, hr, hc, drc)
            warrior_step += cnt_step
            mr_new, mc_new, cnt_step = move_warrior(N, board_medusa_see_best, mr_new, mc_new, hr, hc, drc2)
            warrior_step += cnt_step
            tmp.append([mr_new, mc_new])


        while [hr, hc] in tmp:
            warrior_attack += 1
            tmp.remove([hr, hc])

        Ms = tmp + Ms_seen_best

        print(f'{warrior_step} {warrior_stone} {warrior_attack}')


if __name__ == '__main__':
    solve()
