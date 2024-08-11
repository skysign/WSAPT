import sys
from typing import List


def solve():
    cnt = int(sys.stdin.readline().strip())
    dt: List[int] = [0 for _ in range(11 + 1)]
    ins: List[int] = []

    for _ in range(cnt):
        n = int(sys.stdin.readline().strip())
        ins.append(n)

    mx = max(ins)
    dt[1] = 1
    dt[2] = 2
    dt[3] = 4  # 1+1+1+1 1+2 2+1 3

    for i in range(4, mx + 1):
        dt[i] = dt[i - 1] + dt[i - 2] + dt[i - 3]

    for n in ins:
        print(dt[n])


if __name__ == '__main__':
    solve()
