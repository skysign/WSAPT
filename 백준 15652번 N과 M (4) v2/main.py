import sys


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    dts = [v for v in range(1, N + 1)]

    rec(dts, 0, M, [])


def rec(dts, idx_bgn, M, pathes):
    if len(pathes) == M:
        print(' '.join(map(str, pathes)))
        return

    for idx in range(idx_bgn, len(dts)):
        rec(dts, idx, M, pathes + [dts[idx]])


if __name__ == '__main__':
    solve()
