from collections import deque


def bfs(board, target, visited):
    drc = [[-1, 0], [1, 0], [0, 1], [0, -1]]
    queue = deque([[0, 0]])
    visited[0][0] = True

    while queue:
        r, c = queue.popleft()
        for dr, dc in drc:
            nr = r + dr
            nc = c + dc
            if 0 <= nr < len(board) and 0 <= nc < len(board[0]) and visited[nr][nc] == False:
                visited[nr][nc] = True
                if board[nr][nc] == target:
                    pass
                elif board[nr][nc] == ' ':
                    queue.append([nr, nc])


def solution(storage, requests):
    answer = 0
    board = [[' ' for _ in range(len(storage[0]) + 2)] for _ in range(len(storage) + 2)]

    for i in range(len(storage)):
        for j in range(len(storage[0])):
            board[i + 1][j + 1] = storage[i][j]

    for request in requests:
        if len(request) == 2:
            target = request[0]
            for i in range(1, len(board) - 1):
                for j in range(1, len(board[0]) - 1):
                    if board[i][j] == target:
                        board[i][j] = ' '

        else:
            target = request[0]
            visited = [[False for _ in range(len(storage[0]) + 2)] for _ in range(len(storage) + 2)]
            bfs(board, target, visited)
            for i in range(1, len(board) - 1):
                for j in range(1, len(board[0]) - 1):
                    if board[i][j] == target and visited[i][j]:
                        board[i][j] = ' '

    for i in range(1, len(board) - 1):
        for j in range(1, len(board[0]) - 1):
            if board[i][j] != ' ':
                answer += 1

    return answer
