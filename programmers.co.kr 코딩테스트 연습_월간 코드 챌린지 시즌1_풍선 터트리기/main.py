import sys
from typing import List


def solution(nums: List[int]):
    nums = [sys.maxsize] + nums + [sys.maxsize]
    l = len(nums)
    answer = 0
    mins_l2r = [sys.maxsize] * l
    mins_r2l = [sys.maxsize] * l

    for idx in range(1, l):
        mins_l2r[idx] = min(mins_l2r[idx - 1], nums[idx])

    for idx in range(l - 1 - 1, -1, -1):
        mins_r2l[idx] = min(mins_r2l[idx + 1], nums[idx])

    for idx in range(1, l - 1):
        if not (mins_l2r[idx - 1] < nums[idx] and mins_r2l[idx + 1] < nums[idx]):
            answer += 1

    return answer
