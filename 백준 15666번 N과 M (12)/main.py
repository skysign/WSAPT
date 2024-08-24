import sys
from typing import List, Tuple, Set


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    dts: List[int] = list(map(int, sys.stdin.readline().strip().split(' ')))
    ans: Set[Tuple] = set()

    mycomb(dts, 0, M, ans, [])

    ans: List[Tuple] = list(ans)
    ans.sort()

    for item in ans:
        print(' '.join(map(str, item)))


def mycomb(dts: List[int], depth: int, depth_max: int, ans: Set[Tuple], path: List[int]):
    if depth == depth_max:
        path.sort()
        ans.add(tuple(path))
        return

    for idx in range(len(dts)):
        mycomb(dts, depth + 1, depth_max, ans, path + [dts[idx]])


if __name__ == '__main__':
    solve()
