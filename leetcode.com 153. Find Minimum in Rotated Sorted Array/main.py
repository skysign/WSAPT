from typing import List

def binary_search(nums: List[int], idx_lt, idx_rt):
    mid = 0

    while idx_lt < idx_rt:
        mid = (idx_lt + idx_rt) // 2

        if nums[mid] > nums[mid + 1]:
            return nums[mid + 1]

        if nums[idx_lt] <= nums[mid] <= nums[idx_rt]:
            return nums[idx_lt]
        elif nums[idx_lt] <= nums[mid] >= nums[idx_rt]:
            idx_lt = mid
        elif nums[idx_lt] >= nums[mid] <= nums[idx_rt]:
            idx_rt = mid

    return nums[mid]


class Solution:
    def findMin(self, nums: List[int]) -> int:
        lt, rt = 0, len(nums) -1
        return binary_search(nums, lt, rt)