from typing import List
from collections import defaultdict


def solution(fees: List[int], records: List[str]):
    dict_duration = defaultdict(int)
    dict_records = defaultdict(int)

    for record in records:
        hh_mm, license_plate, _ = record.split(' ')
        if license_plate in dict_records.keys():
            dict_duration[license_plate] += HH_MM(hh_mm) - dict_records[license_plate]
            del dict_records[license_plate]
        else:
            dict_records[license_plate] = HH_MM(hh_mm)

    for license_plate in list(dict_records.keys()):
        dict_duration[license_plate] += HH_MM('23:59') - dict_records[license_plate]
        del dict_records[license_plate]

    answer = []
    for license_plate in list(dict_duration.keys()):
        fee = get_fee(fees, dict_duration[license_plate])
        answer.append([license_plate, fee])

    answer.sort(key=lambda item: item[0])
    return list(map(lambda item: item[1], answer))


def get_fee(fees, duration):
    if duration <= fees[0]:
        return fees[1]

    fee = fees[1]
    duration -= fees[0]
    q, r = divmod(duration, fees[2])
    fee += q * fees[3]
    if r > 0:
        fee += fees[3]

    return fee


def HH_MM(v: str):
    hh, mm = v.split(':')
    return int(hh) * 60 + int(mm)
