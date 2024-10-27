import sys


def solve():
    board = [[0 for _ in range(9)] for _ in range(9)]
    empties = []

    for idx_row in range(9):
        line = sys.stdin.readline().strip()
        for idx_col in range(9):
            board[idx_row][idx_col] = int(line[idx_col])
            if board[idx_row][idx_col] == 0:
                empties.append([idx_row, idx_col])

    result = rec(board, 0, empties)

    for idx_row in range(len(board)):
        print(''.join(map(str, board[idx_row])))


def rec(board, idx_depth, empties):
    if idx_depth == len(empties):
        return True

    idx_row, idx_col = empties[idx_depth]

    for v in range(1, 10):
        if False == check_row(idx_row, board, v):
            continue

        if False == check_column(idx_col, board, v):
            continue

        if False == check_3x3(board, (idx_row // 3) * 3, (idx_col // 3) * 3, v):
            continue

        board[idx_row][idx_col] = v
        if rec(board, idx_depth + 1, empties):
            return True
        board[idx_row][idx_col] = 0

    return False


def check_row(idx_row, board, nv):
    for v in board[idx_row]:
        if v == 0:
            continue

        if v == nv:
            return False

    return True


def check_column(idx_col, board, nv):
    for idx_row in range(0, 9):
        v = board[idx_row][idx_col]

        if v == 0:
            continue

        if v == nv:
            return False

    return True


def check_3x3(board, row_bgn, col_bgn, nv):
    for idx_row in range(row_bgn, row_bgn + 3):
        for idx_col in range(col_bgn, col_bgn + 3):
            v = board[idx_row][idx_col]

            if v == 0:
                continue

            if v == nv:
                return False

    return True


if __name__ == '__main__':
    solve()