import copy
from typing import List


def solution(n: int, info: List[int]):
    answer = rec(0, n, info, [])

    if answer[0] <= 0:
        return [-1]

    return answer[1]


def rec(depth: int, remained_arrow: int, info: List[int], pathes: List[int]):
    if depth == len(info) or remained_arrow == 0:
        pathes += [0] * (len(info) - len(pathes))
        pathes[10] += remained_arrow
        return [jumsu(info, pathes), pathes]

    ans1 = [-1, [0] * 11]
    if remained_arrow - (info[depth] + 1) >= 0:
        ans1 = rec(depth + 1, remained_arrow - (info[depth] + 1), info, pathes + [(info[depth] + 1)])
    ans2 = rec(depth + 1, remained_arrow, info, pathes + [0])

    if ans1[0] > ans2[0]:
        return ans1
    elif ans1[0] < ans2[0]:
        return ans2

    return my_cmp(ans1, ans2)


def jumsu(shot_by_apeach, shot_by_ryan):
    score_apeach, score_ryan = 0, 0

    for idx in range(len(shot_by_apeach)):
        if shot_by_ryan[idx] > shot_by_apeach[idx]:
            score_ryan += (10 - idx)
        elif shot_by_apeach[idx] > 0:
            score_apeach += (10 - idx)

    return score_ryan - score_apeach


def my_cmp(ans1, ans2):
    shot1, shot2 = ans1[1], ans2[1]

    for idx in range(10, -1, -1):
        v1 = shot1[idx]
        v2 = shot2[idx]

        if v1 > v2:
            return ans1
        elif v1 < v2:
            return ans2

    return ans2
