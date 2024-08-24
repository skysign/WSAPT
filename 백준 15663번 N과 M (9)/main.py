import sys
from typing import List, Set


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    dts: List[int] = list(map(int, sys.stdin.readline().strip().split(' ')))
    visited: List[bool] = [False for _ in range(len(dts))]
    ans: Set = set()

    mycomb(dts, ans, [], 0, M, visited)

    ans: List[int] = list(ans)
    ans.sort()
    for item in ans:
        print(' '.join(map(str, item)))


def mycomb(dts: List[int], ans: Set, path: List[int], depth, depth_max, visited):
    if depth == depth_max:
        ans.add(tuple(path))
        return

    for idx in range(len(dts)):
        if visited[idx]:
            continue

        visited[idx] = True
        mycomb(dts, ans, path + [dts[idx]], depth + 1, depth_max, visited)
        visited[idx] = False


if __name__ == '__main__':
    solve()
