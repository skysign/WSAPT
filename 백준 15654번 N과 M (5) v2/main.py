import sys


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    dts = list(map(int, sys.stdin.readline().strip().split()))
    dts.sort()
    visited = [False for _ in range(N)]

    rec(dts, M, [], visited)


def rec(dts, M, pathes, visited):
    if len(pathes) == M:
        print(' '.join(map(str, pathes)))
        return

    for idx in range(0, len(dts)):
        if visited[idx]:
            continue

        visited[idx] = True
        rec(dts, M, pathes + [dts[idx]], visited)
        visited[idx] = False


if __name__ == '__main__':
    solve()
