import sys

def solution(target: int):
    bul = [50]
    singles = [i for i in range(1, 21)]
    bul_singles = bul + singles
    bul_singles.sort()
    doubles = [i * 2 for i in range(1, 21)]
    triples = [i * 3 for i in range(1, 21)]
    doubles_triples = doubles + triples
    doubles_triples.sort()


    dp = [[sys.maxsize, sys.maxsize] for _ in range(0, target + 1)]
    dp[0] = [0, 0]

    for idx_crnt in range(1, target + 1):
        for ib in bul_singles:
            idx_prev = idx_crnt - ib
            if idx_prev >= 0:
                dp[idx_crnt] = my_min([dp[idx_prev][0] + 1, dp[idx_prev][1] + 1], dp[idx_crnt])
            else:
                break

        for id in doubles_triples:
            idx_prev = idx_crnt - id
            if idx_prev >= 0:
                dp[idx_crnt] = my_min([dp[idx_prev][0] + 1, dp[idx_prev][1]], dp[idx_crnt])
            else:
                break

    return dp[target]

def my_min(one, two):
    if one[0] < two[0]:
        return one

    if one[0] > two[0]:
        return two

    if one[0] == two[0]:
        if one[1] > two[1]:
            return one
        elif one[1] < two[1]:
            return two

    return one
