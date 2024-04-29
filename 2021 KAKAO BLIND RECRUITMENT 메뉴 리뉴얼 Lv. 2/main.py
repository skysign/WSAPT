from typing import List
from itertools import combinations
from collections import defaultdict


def solution(orders: List[str], course: List[int]):
    orders = [list(order) for order in orders]
    for order in orders:
        order.sort()

    answer = []

    for length in course:
        candidate_menus = defaultdict(int)
        mx = 0

        for order in orders:
            if length <= len(order):
                for menu in list(combinations(order, length)):
                    candidate_menus[tuple(menu)] += 1

                mx = max(list(candidate_menus.values()))

        if mx <= 1:
            continue

        for key in candidate_menus:
            if candidate_menus[key] == mx:
                answer.append(''.join(list(key)))

    answer.sort()
    return answer
