from typing import List
from collections import defaultdict
from itertools import product


def solution(infos: List[str], queries: List[str]):
    dict = defaultdict(list)

    for info in infos:
        cjp, bf, js, cp, score = info.split(' ')
        dict[cjp + bf + js + cp].append(int(score))

    for key in dict.keys():
        dict[key].sort()

    alls = [['cpp', 'java', 'python'], ['backend', 'frontend'], ['junior', 'senior'], ['chicken', 'pizza']]
    answer = []

    for query in queries:
        q = list(filter(lambda a: a != 'and', query.split(' ')))
        for idx in range(4):
            if q[idx] == '-':
                q[idx] = alls[idx]
            else:
                q[idx] = [q[idx]]

        score = int(q[4])
        qs = list(product(q[0], q[1], q[2], q[3]))
        keys = list(map(lambda a: ''.join(a), qs))

        local_answer = 0
        for key in keys:
            local_answer += binary_search(score, dict[key])

        answer.append(local_answer)

    return answer


def binary_search(target: int, dt: List[int]):
    begin, end = 0, len(dt) -1

    while begin <= end:
        mid = (begin + end) // 2
        if dt[mid] < target:
            begin = mid +1
        else:
            end = mid -1

    return len(dt) - begin