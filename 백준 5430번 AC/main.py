import sys
from collections import deque
from typing import Deque


def solve():
    T = int(sys.stdin.readline().strip())

    for _ in range(T):
        fs = sys.stdin.readline().strip()
        _ = int(sys.stdin.readline().strip())
        line = sys.stdin.readline().strip()
        line = line[1:-1]
        dts: Deque = deque(map(int, line.split(','))) if line != '' else deque([])

        error: bool = False
        reverse: bool = False

        for f in fs:
            if f == 'R':
                reverse = not reverse
            elif f == 'D':
                if len(dts) > 0:
                    if reverse:
                        dts.pop()
                    else:
                        dts.popleft()
                elif len(dts) == 0:
                    error = True
                    break

        if error:
            print('error')
        else:
            if reverse:
                dts.reverse()
            print('[' + ','.join(map(str, dts)) + ']')


if __name__ == '__main__':
    solve()
