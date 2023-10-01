from typing import List
from collections import deque

def find_min_rotation(nums: List[int], idx_lt, idx_rt):
    mid = 0

    while idx_lt < idx_rt:
        mid = (idx_lt + idx_rt) // 2

        if nums[mid] > nums[mid + 1]:
            return mid + 1

        if nums[idx_lt] <= nums[mid] <= nums[idx_rt]:
            return idx_lt
        elif nums[idx_lt] <= nums[mid] >= nums[idx_rt]:
            idx_lt = mid
        elif nums[idx_lt] >= nums[mid] <= nums[idx_rt]:
            idx_rt = mid

    return mid

NOT_FOUND = -1 * 4 ** 10 -1

def binary_search(dt:List[int], idx_lt, idx_rt, target):
    while idx_lt <= idx_rt:
        idx_mid = (idx_lt + idx_rt) // 2

        if dt[idx_mid] == target:
            return idx_mid

        if target < dt[idx_mid]:
            idx_rt = idx_mid -1
        elif target > dt[idx_mid]:
            idx_lt = idx_mid +1

    return NOT_FOUND


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        rotated_count = find_min_rotation(nums, 0, len(nums)-1)
        dqueue = deque(nums)

        for i in range(rotated_count):
            item = dqueue.popleft()
            dqueue.append(item)

        rlt =  binary_search(dqueue, 0, len(nums)-1, target)

        if rlt == NOT_FOUND:
            return -1

        return (rlt + rotated_count) % len(nums)



