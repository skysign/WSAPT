class Box:
    def __init__(self, k, h, w, c):
        self.k = k
        self.h = h
        self.w = w
        self.c = c
        self.sr, self.sc = 0, c

        self.rcs = []
        for r in range(h):
            for c in range(w):
                self.rcs.append([r, c])


def clear_box(board, block):
    for r in range(block.sr, block.sr + block.h):
        for c in range(block.sc, block.sc + block.w):
            board[r][c] = 0


def draw_box(board, block):
    for r, c in block.rcs:
        nr, nc = block.sr + r, block.sc + c
        board[nr][nc] = block.k


# 택배박스가 위에서 아래로 떨어지는 함수
def move_down(board, block, N):
    movable = True

    for sr in range(block.sr, N + 1):
        for r, c in block.rcs:
            nr, nc = sr + r, block.sc + c
            if board[nr][nc] != 0:
                movable = False
                break

        if movable:
            block.sr = sr
            continue
        else:
            break

    draw_box(board, block)


def is_left_zero(board, box, N):
    for r in range(box.sr, box.sr + box.h):
        if sum(board[r][1:box.c]) == 0:
            continue
        else:
            return False

    return True


def is_right_zero(board, box, N):
    for r in range(box.sr, box.sr + box.h):
        local_begin = box.c + box.w
        local_length = (N + 1)  # - (box.c + box.w)
        if sum(board[r][local_begin:local_length]) == 0:
            continue
        else:
            return False

    return True


def solve():
    N, M = map(int, input().split())
    board = []
    for _ in range(N):
        board.append([-1] + [0] * N + [-1])
    board.append([-1] * (N + 2))
    boxes = dict()
    orders = []

    for _ in range(M):
        deleted_k, h, w, c = map(int, input().split())
        boxes[deleted_k] = Box(deleted_k, h, w, c)
        move_down(board, boxes[deleted_k], N)
        orders.append(deleted_k)

    left_right = True

    while len(boxes) > 0:
        candidates = []
        # 택배 하차 (좌측)
        if left_right:
            left_right = False

            for key in orders:
                if is_left_zero(board, boxes[key], N):
                    candidates.append(key)

        # 택배 하차 (우측)
        else:
            left_right = True

            for key in orders:
                if is_right_zero(board, boxes[key], N):
                    candidates.append(key)

        candidates.sort()
        deleted_k = candidates[0]
        print(deleted_k)
        clear_box(board, boxes[deleted_k])
        orders.remove(deleted_k)

        del boxes[deleted_k]

        for k in orders:
            box = boxes[k]
            clear_box(board, box)
            move_down(board, box, N)


if __name__ == '__main__':
    solve()
