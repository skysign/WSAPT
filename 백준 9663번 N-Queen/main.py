import sys


def solve():
    N = int(sys.stdin.readline().strip())
    maps = [0 for _ in range(N)]
    answer = [0]
    place_queen(N, 0, maps, answer)
    print(answer[0])


def place_queen(N: int, row, maps, answer):
    if row == N:
        answer[0] += 1
        return

    for col in range(N):
        if False == attack(row, col, maps):
            maps[row] = col
            place_queen(N, row + 1, maps, answer)


def attack(row, col, maps):
    for row_prev in range(row):
        col_prev = maps[row_prev]
        # if row == row_prev:
        #     return True
        if col == col_prev:
            return True
        # 2개의 퀸을 지나가는 직성의 방정식의 기울기가
        # 1 또는 -1 인 경우에, 대각선으로 2개의 퀸이 서로를 공격할 수 있음
        if abs(row_prev - row) == abs(col_prev - col):
            return True

    return False

if __name__ == '__main__':
    solve()
