import sys
from typing import List
from functools import cmp_to_key

def solve():
    N = int(sys.stdin.readline().strip())
    dt: List[List[int]] = []

    for _ in range(N):
        fr, to = map(int, sys.stdin.readline().strip().split(' '))
        dt.append([fr, to])

    dt.sort(key=lambda x: (x[1], x[0]))

    end_time = 0
    answer: int = 0

    for fr, to in dt:
        if end_time <= fr:
            answer += 1
            end_time = to

    print(answer)

if __name__ == '__main__':
    solve()
