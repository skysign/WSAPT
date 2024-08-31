from typing import List
from collections import defaultdict


def solution(fees: List[int], records: List[List]):
    gibon_time, gibon_fee, danwi_time, danwi_fee = fees

    dt = {}
    parking_time = defaultdict(int)
    answer = defaultdict(int)

    for item in records:
        time, plate, inout = item.split(' ')

        if inout == 'IN':
            dt[plate] = time
        elif inout == 'OUT':
            time_in = dt[plate]
            time_in = HH_MM(time_in)
            time_out = time
            time_out = HH_MM(time_out)
            parking_time[plate] += (time_out - time_in)
            del dt[plate]

    keys = list(dt.keys())
    for plate in keys:
        time_in = dt[plate]
        time_in = HH_MM(time_in)
        time_out = '23:59'
        time_out = HH_MM(time_out)
        parking_time[plate] += (time_out - time_in)

    keys = list(parking_time.keys())
    for plate in keys:
        parking_fee = calculate_parking_fee(parking_time[plate], gibon_time, gibon_fee, danwi_time, danwi_fee)
        answer[plate] += parking_fee

    # keys = list(dt.keys())
    # for plate in keys:
    #     time_in = dt[plate]
    #     time_in = HH_MM(time_in)
    #     time_out = '23:59'
    #     time_out = HH_MM(time_out)
    #     parking_time = time_out - time_in
    #
    #     parking_fee = calculate_parking_fee(parking_time, gibon_time, gibon_fee, danwi_time, danwi_fee)
    #     answer[plate] += parking_fee
    #     del dt[plate]

    ans = []
    for plate in answer.keys():
        ans.append([plate, answer[plate]])

    ans.sort(key=lambda x: x[0])
    ans2 = list(map(lambda x: x[1], ans))

    return ans2


def calculate_parking_fee(parking_time: int, gibon_time, gibon_fee, danwi_time, danwi_fee):
    parking_fee = 0

    if parking_time > gibon_time:
        parking_time -= gibon_time
        parking_fee += gibon_fee

        quotient, remainder = divmod(parking_time, danwi_time)
        if remainder > 0:
            quotient += 1

        parking_fee += (quotient * danwi_fee)
    else:
        parking_fee += gibon_fee

    return parking_fee


def HH_MM(t: str):
    hour, minute = map(int, t.split(':'))
    return hour * 60 + minute
