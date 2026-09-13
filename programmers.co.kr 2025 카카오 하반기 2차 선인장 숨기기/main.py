from collections import deque


def solution(m, n, h, w, drops):
    INF = 500000 + 1
    board = [[INF for _ in range(n)] for _ in range(m)]

    for idx in range(len(drops)):
        rain_r, rain_c = drops[idx]
        board[rain_r][rain_c] = idx + 1

    row_min = [[0 for _ in range(n - w + 1)] for _ in range(m)]

    for r in range(m):
        dq = deque()

        for c in range(n):
            while dq and board[r][dq[-1]] >= board[r][c]:
                dq.pop()

            dq.append(c)

            # 슬라이딩 윈도우가 오른쪽으로 한칸 이동하면서,
            # 슬라이딩 윈도우에 제일 왼쪽에 있는 값을 빼준다.
            if dq[0] <= c - w:
                dq.popleft()

            # 슬라이딩 윈도우의 모든칸이 board위에 올라오면
            if c >= w - 1:
                row_min[r][c - w + 1] = board[r][dq[0]]

    col_min = [[0 for _ in range(n - w + 1)] for _ in range(m - h + 1)]
    for c in range(n - w + 1):
        dq = deque()
        for r in range(m):
            while dq and row_min[dq[-1]][c] >= row_min[r][c]:
                dq.pop()

            dq.append(r)

            if dq[0] <= r - h:
                dq.popleft()

            if r >= h - 1:
                col_min[r - h + 1][c] = row_min[dq[0]][c]

    answer_rain = 0
    answer_rc = [0, 0]

    for r in range(m - h + 1):
        for c in range(n - w + 1):
            if col_min[r][c] == INF:
                return [r, c]

            if answer_rain < col_min[r][c]:
                answer_rain = col_min[r][c]
                answer_rc = [r, c]

    return answer_rc
