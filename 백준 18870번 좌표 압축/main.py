import copy
import sys
from typing import List


def solve():
    N = int(sys.stdin.readline().strip())
    dt: List[int] = list(map(int, sys.stdin.readline().strip().split(' ')))
    dp: List[int] = [0 for _ in range(len(dt))]
    answer: List[int] = [0 for _ in range(len(dt))]
    dt_sort = copy.deepcopy(dt)
    dt_sort.sort()
    dict = {}

    for i in range(len(dt)):
        if i == 0:
            dp[i] = 0
            dict[dt_sort[i]] = dp[i]
        else:
            if dt_sort[i - 1] < dt_sort[i]:
                dp[i] = dp[i-1] + 1
            else:   # dt_sort[i - 1] == dt_sort[i]:
                dp[i] = dp[i-1]

            dict[dt_sort[i]] = dp[i]

    for i in range(len(dt)):
        answer[i] = dict[dt[i]]

    print(' '.join(list(map(str, answer))))

if __name__ == '__main__':
    solve()
