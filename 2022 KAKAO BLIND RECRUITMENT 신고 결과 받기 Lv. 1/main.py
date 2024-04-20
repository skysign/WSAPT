from typing import List
from collections import defaultdict


def solution(id_list: List[str], reports: List[str], k: int):
    dict = defaultdict(set)

    for report in reports:
        reporter, reported = report.split(' ')
        dict[reported].add(reporter)

    for key in list(dict.keys()):
        if len(dict[key]) < k:
            del dict[key]

    answer = [0 for _ in range(len(id_list))]
    for idx in range(len(id_list)):
        id = id_list[idx]

        for key in dict.keys():
            if id in dict[key]:
                answer[idx] += 1

    return answer
