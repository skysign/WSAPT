import copy


def create_board(N, v):
    board = []
    for _ in range(N):
        board.append([v for _ in range(N)])

    return board


def create_board_path(N):
    board = []
    for _ in range(N):
        board.append([[-1, -1, -1] for _ in range(N)])

    return board


def solve():
    N, M, K = map(int, input().split())
    board_sanhocho = []
    answer = []

    for _ in range(N):
        board_sanhocho.append(list(map(int, input().split())))

    turtles = []
    for _ in range(M):
        turtles.append(list(map(int, input().split())))
        answer.append(0)

    board_volcano = create_board(N, -1)
    volcanoes = []
    for k in range(K):
        row = list(map(int, input().split()))
        volcanoes.append(row + [0])
        r, c, _ = row
        board_volcano[r][c] = k

    for turn in range(1, 101):
        # 1단계: 바다거북 이동
        turtles_new = []  # 이번턴에 이동한 거북이

        for idx_turtles in range(len(turtles)):
            # board_sanhocho를 copy함으로, 산호초의 위치는 board에 저장됨
            board = copy.deepcopy(board_sanhocho)

            for r_turtles, c_turtles in turtles_new:
                board[r_turtles][c_turtles] = 2  # 이동한 거북이 위치 표시

            sr_turtles, sc_turtles = turtles.pop(0)
            if answer[idx_turtles] == -1:
                turtles_new.append([sr_turtles, sc_turtles])
            elif sr_turtles == N - 1 and sc_turtles == N - 1:
                turtles_new.append([sr_turtles, sc_turtles])
            else:
                for r_turtles, c_turtles in turtles:
                    board[r_turtles][c_turtles] = 2  # 아직 이동하지 않은 거북이 위치 표시

                board_visited = create_board(N, 200)
                board_visited[sr_turtles][sc_turtles] = 0
                board_path = create_board_path(N)
                board_path[sr_turtles][sc_turtles] = [0, -1, -1]
                board_path2 = create_board(N, -1)
                board_path2[sr_turtles][sc_turtles] = 0

                queue = [[0, sr_turtles, sc_turtles]]
                can_go_safezone = False

                while queue:
                    depth, sr, sc = queue.pop(0)
                    if sr == N - 1 and sc == N - 1:
                        can_go_safezone = True
                        continue

                    # 우(→), 하(↓), 좌(←), 상(↑)
                    drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]
                    for dr, dc in drc:
                        nr, nc, = sr + dr, sc + dc
                        if 0 <= nr < N and 0 <= nc < N and board[nr][nc] == 0 and board_visited[nr][nc] > depth + 1:
                            queue.append([depth + 1, nr, nc])
                            board_path[nr][nc] = [depth + 1, sr, sc]
                            board_visited[nr][nc] = depth + 1

                r_next, c_next = sr_turtles, sc_turtles

                if can_go_safezone:
                    depth, r, c = board_path[N - 1][N - 1]
                    board_path2[N - 1][N - 1] = depth
                    while r != -1 and c != -1:
                        board_path2[r][c] = depth - 1
                        depth, r, c = board_path[r][c]
                        if depth - 1 == 1:
                            r_next, c_next = r, c

                    board_path2[r][c] = 0

                turtles_new.append([r_next, c_next])

                if r_next != sr_turtles or c_next != sc_turtles:
                    answer[idx_turtles] += 1

        turtles = turtles_new

        # 2단계: 화산 압력 증가
        for idx in range(len(volcanoes)):
            volcanoes[idx][3] += 10  # 바다에 존재하는 모든 해저 화산의 마그마 압력이 각각 10씩 증가합니다.

        # 3단계: 화산 분출 및 연쇄 반응
        board_steam = create_board(N, 0)
        eruption = False
        check_volcano_again = True  # chained-reaction

        while check_volcano_again:
            check_volcano_again = False

            for idx in range(len(volcanoes)):
                sr, sc, fire, _ = volcanoes[idx]
                # 현재 마그마 압력이 각 화산의 분출 임계치(P) 이상인 화산은 뜨거운 열기를 분출합니다.
                if volcanoes[idx][2] <= volcanoes[idx][3] + board_steam[sr][sc]:
                    eruption = True
                    board_steam[sr][sc] += fire
                    # 방향으로 열기 분출
                    # 위쪽
                    local_fire = fire
                    for r in range(sr - 1, -1, -1):
                        local_fire = local_fire // 2
                        board_steam[r][sc] += local_fire
                    # 아래쪽
                    local_fire = fire
                    for r in range(sr + 1, N):
                        local_fire = local_fire // 2
                        board_steam[r][sc] += local_fire
                    # 왼쪽
                    local_fire = fire
                    for c in range(sc - 1, -1, -1):
                        local_fire = local_fire // 2
                        board_steam[sr][c] += local_fire
                    # 오른쪽
                    local_fire = fire
                    for c in range(sc + 1, N):
                        local_fire = local_fire // 2
                        board_steam[sr][c] += local_fire

                    check_volcano_again = True
                    volcanoes[idx][3] = 0  # 4단계: 환경 초기화

        if eruption:
            for idx in range(len(turtles)):
                r, c = turtles[idx]
                if board_steam[r][c] >= 20:
                    answer[idx_turtles] = -1
                    board_sanhocho[r][c] = 4

    for a in answer:
        print(a)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    solve()
