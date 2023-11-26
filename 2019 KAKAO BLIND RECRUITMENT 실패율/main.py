# 2019 KAKAO BLIND RECRUITMENT 실패율
#
# 유튜브 문제 풀이: https://youtu.be/Zo8hECJa_ms?si=cB0vo11Izy5wCjSE
#
# 파이썬 소스: https://bit.ly/47wagqb
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42889

from collections import defaultdict
from functools import cmp_to_key

def solution(N, stages):
    dict = defaultdict(int)

    for stage in stages:
        dict[stage] += 1

    current_stage_users = len(stages)
    failrate_stage = []

    for stage in range(1, N+1, 1):
        if current_stage_users == 0:
            failrate_stage.append((0, stage))
        else:
            fail_rate = dict[stage] / current_stage_users
            failrate_stage.append((fail_rate, stage))
            current_stage_users -= dict[stage]

    failrate_stage.sort(key=lambda x: (-1 * x[0], x[1]), reverse=False)
    # def my_compare(a, b):
    #     if a[0] > b[0]:
    #         return -1
    #     elif a[0] < b[0]:
    #         return 1
    #     else:   # a[0] == b[0]
    #         if a[1] < b[1]:
    #             return -1
    #         elif a[1] > b[1]:
    #             return 1
    #
    #     return 0
    #
    # failrate_stage = sorted(failrate_stage, key=cmp_to_key(my_compare))

    return list(map(lambda x: x[1], failrate_stage))