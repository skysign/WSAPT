import copy
from collections import defaultdict
from typing import List, Dict


def solution(N: int, number: int):
    # X / N 의 값이 number의 최소값이 될 수 있음
    # 따라서, number * N + 1 이 dp의 최소 길이
    l = number * N + 1
    dp: List[int] = [9] * l

    dp[N] = 1
    dp[1] = min(dp[1], 2)  # N/N, N이 1인 경우도 고려해서, min()이 필요함

    # NN, NNN등의 숫자 처리필요함
    str = '{:d}'.format(N)
    cntN = 1

    while int(str) <= l:
        dp[int(str)] = min(dp[int(str)], cntN)
        str += '{:d}'.format(N)
        cntN += 1

    def do_plus():
        for i in range(l - 1, 0, -1):
            if dp[i] <= 8 and 1 <= i + N < l:
                dp[i + N] = min(dp[i + N], dp[i] + 1)

    def do_multiply():
        for i in range(l - 1, 0, -1):
            if dp[i] <= 8 and 1 <= i * N < l:
                dp[i * N] = min(dp[i * N], dp[i] + 1)

    def do_minus():
        for i in range(1, l):
            if dp[i] <= 8 and 1 <= i - N < l:
                dp[i - N] = min(dp[i - N], dp[i] + 1)

    def do_divide():
        for i in range(1, l):
            if dp[i] <= 8 and 1 <= i // N < l:
                dp[i // N] = min(dp[i // N], dp[i] + 1)

    for _ in range(8):
        do_plus()
        do_multiply()
        do_minus()
        do_divide()

    dict: Dict = defaultdict(lambda: 9)

    for i in range(1, l):
        if dp[i] > 8:
            continue
        else:
            dict[i] = dp[i]

    dict_local: Dict = copy.deepcopy(dict)
    li = list(dict.keys())
    lj = list(dict.keys())
    li.sort()
    lj.sort()

    for i in li:
        for j in lj:
            if i == j:
                continue

            vs = [i + j, i * j, i // j, i - j,
                  j + i, j * i, j // i, j - i]
            for v in vs:
                if 1 <= v < l:
                    dict_local[v] = min(dict_local[v], dict[i] + dict[j])
                    if dict_local[v] > 8:
                        del dict_local[v]

    dict = dict_local

    if dict[number] > 8:
        return -1

    return dict[number]
