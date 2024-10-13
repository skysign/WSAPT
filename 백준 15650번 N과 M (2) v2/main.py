import sys


def solve():
    N, M = map(int, sys.stdin.readline().strip().split())
    dts = [v for v in range(1, N + 1)]

    rec(dts, 0, M, [])


def rec(dts, idx_bgn, depth_max, pathes):
    if len(pathes) == depth_max:
        print(' '.join(map(str, pathes)))
        return

    for idx in range(idx_bgn, len(dts)):
        rec(dts, idx + 1, depth_max, pathes + [dts[idx]])


if __name__ == '__main__':
    solve()
