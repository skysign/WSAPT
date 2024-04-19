from collections import defaultdict
from typing import List


def solution(today: str, terms: List, privacies: List[str]):
    today = ymd2int(today)
    dict_terms = defaultdict(int)
    for term in terms:
        yakkwan, yuhyo = term.split(' ')
        dict_terms[yakkwan] = int(yuhyo.split(' ')[0]) * 28

    for idx in range(len(privacies)):
        ymd, yakkwan = privacies[idx].split(' ')
        privacies[idx] = [idx + 1, (ymd2int(ymd) + dict_terms[yakkwan])]

    privacies = list(filter(lambda privacy: privacy[1] <= today, privacies))
    answer = list(map(lambda privacy: privacy[0], privacies))
    answer.sort()

    return answer


def ymd2int(ymd: str) -> int:
    y, m, d = ymd.split('.')
    y = int(y) * 12 * 28
    m = int(m[0]) * 10 + int(m[1])
    m *= 28
    d = int(d[0]) * 10 + int(d[1])
    return y + m + d
