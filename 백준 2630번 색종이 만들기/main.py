import sys
from typing import List


def solve():
    N: int = int(sys.stdin.readline().strip())
    dt = []

    for _ in range(N):
        dt.append(list(map(int, sys.stdin.readline().strip().split(' '))))

    white, blue = rec(dt, 0, 0, N)
    print(white)
    print(blue)


def rec(dt: List[List[int]], rs, cs, length):
    if is_one_color(dt, rs, cs, length):
        if dt[rs][cs] == 0:
            return 1, 0
        else:
            return 0, 1

    nl = length // 2
    w00, b00 = rec(dt, rs, cs, nl)
    w01, b01 = rec(dt, rs, cs + nl, nl)
    w10, b10 = rec(dt, rs + nl, cs, nl)
    w11, b11 = rec(dt, rs + nl, cs + nl, nl)

    return w00 + w01 + w10 + w11, b00 + b01 + b10 + b11


def is_one_color(dt: List[List[int]], rs, cs, length):
    color = dt[rs][cs]

    for row in range(rs, rs + length):
        for col in range(cs, cs + length):
            if color != dt[row][col]:
                return False

    return True


if __name__ == '__main__':
    solve()
