import sys


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    dts = list(map(int, sys.stdin.readline().strip().split()))
    dts.sort()

    rec(dts, M, [])


def rec(dts, M, pathes):
    if len(pathes) == M:
        print(' '.join(map(str, pathes)))
        return

    for idx in range(0, len(dts)):
        if not dts[idx] in pathes:
            rec(dts, M, pathes + [dts[idx]])


if __name__ == '__main__':
    solve()
