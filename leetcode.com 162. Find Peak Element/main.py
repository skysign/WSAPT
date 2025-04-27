import sys
from typing import List


class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        nums = [-sys.maxsize] + nums + [-sys.maxsize]
        idx_lt, idx_rt = 1, len(nums) - 2

        while idx_lt <= idx_rt:
            idx_mid = (idx_lt + idx_rt) // 2

            if nums[idx_mid - 1] < nums[idx_mid] > nums[idx_mid + 1]:
                return idx_mid -1

            if nums[idx_mid - 1] > nums[idx_mid]:
                idx_rt = idx_mid - 1
            else:
                idx_lt = idx_mid + 1

        return idx_lt
