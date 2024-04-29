from typing import List
from collections import defaultdict


def solution(infos: List[str], queries: List[str]):
    dict = defaultdict(lambda: dict)
    leaves = []

    for data in infos:
        datas = data.split(' ')
        rec(datas, 0, dict, leaves)

    for leaf in leaves:
        leaf.sort()

    answer = []
    alls = [['cpp', 'java', 'python'], ['backend', 'frontend'], ['junior', 'senior'], ['chicken', 'pizza']]

    for query in queries:
        qss = []
        fields = list(filter(lambda a: a != 'and', query.split(' ')))
        for idx in range(len(fields)):
            q = fields[idx]
            if idx <= 3:
                if q == '-':
                    qss.append(alls[idx])
                else:
                    qss.append([q])
            else:
                qss.append(int(q))

        r = rec2(dict, qss, 0)
        answer.append(r)

    return answer

def rec2(dict, qss, depth):
    if depth == len(qss) -1:
        score = qss[len(qss) -1]
        n = binary_search(dict, score)
        return n

    qs = qss[depth]
    rtn = 0
    for key in qs:
        if key in dict.keys():
            rtn += rec2(dict[key], qss, depth +1)

    return rtn

def binary_search(l, score):
    begin, mid, end= 0, 0, len(l) -1

    while begin <= end:
        mid = (begin + end) // 2
        if l[mid] < score:
            begin = mid + 1
        else:
            end = mid - 1

    return len(l) - begin

def rec(datas: List[str], depth: int, dict, leaves):
    if depth == len(datas) - 1:
        dict.append(int(datas[len(datas) - 1]))
        return

    if depth <= len(datas) - 2:
        key = datas[depth]
        if not key in dict.keys():
            if depth == len(datas) - 2:
                dict[key] = []
                leaves.append(dict[key])
            else:
                dict[key] = defaultdict(lambda: dict)

        rec(datas, depth + 1, dict[key], leaves)
