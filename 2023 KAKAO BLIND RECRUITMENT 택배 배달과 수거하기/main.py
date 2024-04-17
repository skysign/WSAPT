from collections import defaultdict
from typing import List


def solution(cap: int, n: int, deliveries: List[int], pickups: List[int]):
    dict_deliveries = defaultdict(int)
    dict_pickups = defaultdict(int)

    for i in range(0, n):
        if deliveries[i] > 0:
            dict_deliveries[i] = deliveries[i]
        if pickups[i] > 0:
            dict_pickups[i] = pickups[i]

    key_deliveries = list(dict_deliveries.keys())
    key_pickups = list(dict_pickups.keys())
    key_deliveries.sort(reverse=True)
    key_pickups.sort(reverse=True)

    answer = 0

    while len(key_deliveries) > 0 or len(key_pickups) > 0:
        local_cap = cap
        n_deliveries = 0
        while local_cap > 0 and len(key_deliveries) > 0:
            if local_cap >= dict_deliveries[key_deliveries[0]]:
                local_cap -= dict_deliveries[key_deliveries[0]]
                n_deliveries = max(n_deliveries, key_deliveries[0])
                key_deliveries.pop(0)
            else:
                dict_deliveries[key_deliveries[0]] -= local_cap
                local_cap = 0
                n_deliveries = max(n_deliveries, key_deliveries[0])

        local_cap = cap
        n_pickups = 0
        while local_cap > 0 and len(key_pickups) > 0:
            if local_cap >= dict_pickups[key_pickups[0]]:
                local_cap -= dict_pickups[key_pickups[0]]
                n_pickups = max(n_pickups, key_pickups[0])
                key_pickups.pop(0)
            else:
                dict_pickups[key_pickups[0]] -= local_cap
                local_cap = 0
                n_pickups = max(n_pickups, key_pickups[0])

        answer += ((max(n_deliveries, n_pickups) + 1) * 2)

    return answer
