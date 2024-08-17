import sys
from typing import List


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    dt: List[int] = list(map(int, sys.stdin.readline().strip().split(' ')))
    answer = 0
    high = max(dt)
    low = 0

    while low <= high:
        mid = (low + high) // 2
        sm = sum_wood_length(dt, mid)

        if sm >= M:
            low = mid + 1
            answer = mid
        else:
            high = mid - 1

    print(answer)


def sum_wood_length(dt: List[int], h: int):
    rtn = 0

    for wood in dt:
        if wood > h:
            rtn += (wood - h)

    return rtn


if __name__ == '__main__':
    solve()
