import sys
from typing import List


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    dt: List[int] = [v for v in range(1, N + 1)]
    my_perm(0, dt, [], M, fn_found)


def fn_found(perms: List[int]):
    perms: List[str] = map(lambda x: str(x) + ' ', perms)
    perm = ''.join(perms).strip()
    print(perm)


def my_perm(bgn, dt, perms: List[int], length, fn_found):
    if len(perms) == length:
        fn_found(perms)
        return

    for i in range(bgn, len(dt)):
        perms.append(dt[i])
        my_perm(i, dt, perms, length, fn_found)
        perms.pop()


if __name__ == '__main__':
    solve()
