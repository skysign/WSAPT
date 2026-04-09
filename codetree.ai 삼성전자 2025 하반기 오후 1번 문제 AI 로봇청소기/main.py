from collections import deque


def solve():
    N, K, L = map(int, input().split())
    dt = []
    robots = deque()

    for _ in range(N):
        dt.append(list(map(int, input().split())))

    for _ in range(K):
        r, c = list(map(int, input().split()))
        robots.append([r - 1, c - 1])

    for _ in range(L):
        robots_next = deque()

        # 1 청소기 이동
        while robots:
            robot_sr, robot_sc = robots.popleft()
            visited = [[False] * N for _ in range(N)]

            # 로봇이 있는 위치는 방문할 수 없도록, True로 표시함
            for r, c in robots + robots_next:
                visited[r][c] = True

            candidates = []
            queue = deque([[0, robot_sr, robot_sc]])
            visited[robot_sr][robot_sc] = True

            while queue:
                queue_next = deque()

                while queue:
                    depth, sr, sc = queue.popleft()
                    if dt[sr][sc] > 0:
                        candidates.append([sr, sc])
                    queue_next.append([depth, sr, sc])

                if len(candidates) > 0:
                    break

                drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]

                while queue_next:
                    depth, sr, sc = queue_next.popleft()
                    for dr, dc in drc:
                        nr, nc = sr + dr, sc + dc
                        if 0 <= nr < N and 0 <= nc < N and visited[nr][nc] == False:
                            if dt[nr][nc] >= 0: # 빈칸이거나 먼지가 있거나
                                queue.append([depth +1, nr, nc])
                                visited[nr][nc] = True

            if len(candidates) == 0:
                robots_next.append([robot_sr, robot_sc])
            else:
                candidates.sort()
                robots_next.append(candidates[0])

        robots = robots_next

        # 2 청소
        for r_r, r_c in robots:
            banghyang = [
                [[0, 1], [1, 0], [-1, 0], [0, 0]],  # 오른쪽
                [[0, 1], [1, 0], [0, -1], [0, 0]],  # 아래쪽
                [[1, 0], [0, -1], [-1, 0], [0, 0]],  # 왼쪽
                [[0, 1], [0, -1], [-1, 0], [0, 0]]  # 위쪽
            ]

            # 어느 방향을 청소해야 하는지 찾기
            candidates = []
            for idx_banghyang in range(len(banghyang)):
                local_candi = 0
                for bh_r, bh_c in banghyang[idx_banghyang]:
                    nr, nc = bh_r + r_r, bh_c + r_c
                    if 0 <= nr < N and 0 <= nc < N:
                        if dt[nr][nc] > 0:  # 이동할수 있는 칸이고, 먼지가 있는 칸일 때
                            local_candi += min(20, dt[nr][nc])

                candidates.append([local_candi * -1, idx_banghyang])

            candidates.sort()
            # idx_banghyang 방향이 먼지량이 가장큰 방향
            _, idx_banghyang = candidates[0]

            for bh_r, bh_c in banghyang[idx_banghyang]:
                nr, nc = bh_r + r_r, bh_c + r_c
                if 0 <= nr < N and 0 <= nc < N:
                    if dt[nr][nc] > 0:  # 먼지가 있는 칸일 때
                        dt[nr][nc] -= 20  # 격자마다 청소할 수 있는 최대 먼지량은 20입니닫.
                        if dt[nr][nc] < 0:
                            dt[nr][nc] = 0

        # 3. 먼지 축적
        for r in range(len(dt)):
            for c in range(len(dt[0])):
                if dt[r][c] > 0:
                    dt[r][c] += 5

        # 4. 먼지 확산
        tmp = [[0] * N for _ in range(N)]
        for r in range(len(dt)):
            for c in range(len(dt[0])):
                if dt[r][c] == 0:
                    drc = [[0, 1], [1, 0], [0, -1], [-1, 0]]
                    dust_0 = 0
                    for dr, dc in drc:
                        nr, nc = r + dr, c + dc
                        if 0 <= nr < N and 0 <= nc < N:
                            if dt[nr][nc] > 0:
                                dust_0 += dt[nr][nc]
                    tmp[r][c] = (dust_0 // 10)

        for r in range(len(dt)):
            for c in range(len(dt[0])):
                if tmp[r][c] > 0:
                    dt[r][c] += tmp[r][c]

        answer = 0
        for r in range(len(dt)):
            for c in range(len(dt[0])):
                if dt[r][c] > 0:
                    answer += dt[r][c]

        print(answer)

        if answer == 0:
            return


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    solve()
