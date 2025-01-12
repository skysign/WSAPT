import sys


def solve():
    input = sys.stdin.readline
    board = []
    empties = []

    for idx_row in range(9):
        row = input().strip()
        board.append([0 for _ in range(9)])
        for idx_col in range(9):
            board[idx_row][idx_col] = int(row[idx_col])

            if board[idx_row][idx_col] == 0:
                empties.append([idx_row, idx_col])

    rec(board, 0, empties)

    for idx_row in range(9):
        print(''.join(list(map(str, board[idx_row]))))


def rec(board, idx_empty, empties):
    if len(empties) == idx_empty:
        return True

    idx_row, idx_col = empties[idx_empty]

    for number in range(1, 10):
        if False == can_place_row(board, idx_row, number):
            continue

        if False == can_place_col(board, idx_col, number):
            continue

        if False == can_place_3x3(board, idx_row, idx_col, number):
            continue

        board[idx_row][idx_col] = number
        if rec(board, idx_empty + 1, empties):
            return True
        board[idx_row][idx_col] = 0

    return False


def can_place_row(board, idx_row, number) -> bool:
    return number not in board[idx_row]


def can_place_col(board, idx_col, number) -> bool:
    return number not in [board[i][idx_col] for i in range(9)]


def can_place_3x3(board, idx_row, idx_col, number) -> bool:
    row = (idx_row // 3) * 3
    col = (idx_col // 3) * 3

    for i in range(row, row + 3):
        for j in range(col, col + 3):
            if board[i][j] == number:
                return False

    return True


if __name__ == '__main__':
    solve()
