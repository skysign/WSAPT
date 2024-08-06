import sys
from typing import List

def solve():
    sys.stdin.readline().strip()
    dt:List[int] = list(map(int, sys.stdin.readline().strip().split(' ')))
    dt.sort()
    ans = 0
    l = len(dt)

    for i in range(l):
        ans += (l - i) * dt[i]

    print(ans)


if __name__ == '__main__':
    solve()