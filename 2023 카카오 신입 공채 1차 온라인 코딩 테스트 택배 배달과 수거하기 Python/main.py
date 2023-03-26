def solution(cap, n, deliveries, pickups):
    answer = 0
    deliveries_remained_cap = 0
    pickups_remained_cap = 0

    for idx in range(n - 1, -1, -1):
        deliveries_remained_cap -= deliveries[idx]
        pickups_remained_cap -= pickups[idx]

        while deliveries_remained_cap < 0 or pickups_remained_cap < 0:
            deliveries_remained_cap += cap
            pickups_remained_cap += cap
            distance = idx + 1
            answer += (distance * 2)

    return answer
