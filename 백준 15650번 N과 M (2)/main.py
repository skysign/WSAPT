import sys
from typing import List


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    dt: List[int] = [v for v in range(1, N + 1)]
    my_comb(0, dt, M, [], func_found)


def func_found(chosen: List[int]):
    dt = list(map(lambda x: str(x) + ' ', chosen))
    dt = ''.join(dt).strip()
    print(dt)


def my_comb(bgn, dt, l, chosen: List[int], func_found):
    if len(chosen) == l:
        func_found(chosen)
        return

    for i in range(bgn, len(dt)):
        chosen.append(dt[i])
        my_comb(i +1, dt, l, chosen, func_found)
        chosen.pop()


if __name__ == '__main__':
    solve()
