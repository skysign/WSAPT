drc = [[-1, 0], [0, 1], [1, 0], [0, -1]]

R = 0
C = 0


def solution(board, aloc, bloc):
    global R, C
    R = len(board)
    C = len(board[0])

    winner, max_depth = turn_A(board, aloc, bloc, 0)

    return max_depth


def turn_A(board, aloc, bloc, depth):
    ar, ac = aloc
    results = {True: [], False: []}
    no_move = True

    for dr, dc in drc:
        sr, sc = aloc
        nr = sr + dr
        nc = sc + dc

        if is_in(nr, nc) and board[nr][nc] == 1:
            no_move = False
            board[ar][ac] = 0
            if is_x_on_0(board, bloc):
                win_lose = True
                max_depth = depth + 1
                results[win_lose].append(max_depth)
                board[ar][ac] = 1
                continue

            win_lose, max_depth = turn_B(board, [nr, nc], bloc, depth + 1)
            results[not win_lose].append(max_depth)
            board[ar][ac] = 1

    if no_move:
        return False, depth

    if len(results[True]) > 0:
        return True, min(results[True])

    return False, max(results[False])


def turn_B(board, aloc, bloc, depth):
    br, bc = bloc
    results = {True: [], False: []}
    no_move = True

    for dr, dc in drc:
        sr, sc = bloc
        nr = sr + dr
        nc = sc + dc

        if is_in(nr, nc) and board[nr][nc] == 1:
            no_move = False
            board[br][bc] = 0
            if is_x_on_0(board, aloc):
                win_lose = True
                max_depth = depth + 1
                results[win_lose].append(max_depth)
                board[br][bc] = 1
                continue

            win_lose, max_depth = turn_A(board, aloc, [nr, nc], depth + 1)
            results[not win_lose].append(max_depth)
            board[br][bc] = 1

    if no_move:
        return False, depth

    if len(results[True]) > 0:
        return True, min(results[True])

    return False, max(results[False])


def is_x_on_0(board, loc):
    r, c = loc
    if board[r][c] == 0:
        return True

    return False


def is_in(r, c):
    global R, C
    if 0 <= r < R and 0 <= c < C:
        return True

    return False
