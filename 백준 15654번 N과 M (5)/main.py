import sys
from typing import List

def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    dt: List[int] = list(map(int, sys.stdin.readline().strip().split(' ')))
    dt.sort()

    visited: List[bool] = [False for _ in range(len(dt))]
    my_perm(visited, dt, [], M, perm_found)


def perm_found(perms: List[int]):
    perms = list(map(lambda x: str(x) + ' ', perms))
    perm = ''.join(perms).strip()
    print(perm)


def my_perm(visited: List[bool], dt, perms, length, perm_found):
    if len(perms) == length:
        perm_found(perms)
        return

    for i in range(len(dt)):
        if visited[i]:
            continue

        visited[i] = True
        perms.append(dt[i])
        my_perm(visited, dt, perms, length, perm_found)
        perms.pop()
        visited[i] = False


if __name__ == '__main__':
    solve()
