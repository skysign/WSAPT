# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 택배 배달과 수거하기 Python
#
# 유튜브 문제 풀이: https://youtu.be/5FX8K-bih9Q
#
# 파이썬 소스: http://bit.ly/40ycouk
#
# 문제 링크: http://bit.ly/3MaqmxT

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
