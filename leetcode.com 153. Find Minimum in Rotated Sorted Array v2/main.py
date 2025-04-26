from typing import List


class Solution:
    def findMin(self, nums: List[int]) -> int:
        idx_lt, idx_rt = 0, len(nums) - 1

        while idx_lt < idx_rt:
            if nums[idx_lt] > nums[idx_rt] and idx_rt - idx_lt == 1:
                return nums[idx_rt]

            if nums[idx_lt] < nums[idx_rt] and idx_rt - idx_lt == 1:
                return nums[idx_lt]

            idx_mid = (idx_lt + idx_rt) // 2

            if nums[idx_lt] <= nums[idx_mid] > nums[idx_rt]:
                idx_lt = idx_mid
            elif nums[idx_lt] > nums[idx_mid] <= nums[idx_rt]:
                idx_rt = idx_mid
            else:   # nums[idx_lt] <= nums[idx_mid] <= nums[idx_rt]:
                idx_rt = idx_mid

        return nums[idx_lt]