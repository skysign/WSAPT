import sys
from typing import List


def solve():
    N, M = map(int, sys.stdin.readline().strip().split(' '))
    dt: List[int] = [0]
    dt += map(int, sys.stdin.readline().strip().split(' '))
    prefixSum: List[int] = [0 for _ in range(len(dt))]

    for i in range(1, len(prefixSum)):
        prefixSum[i] = prefixSum[i - 1] + dt[i]

    for _ in range(M):
        fr, to = map(int, sys.stdin.readline().strip().split(' '))
        print(prefixSum[to] - prefixSum[fr - 1])


if __name__ == '__main__':
    solve()
