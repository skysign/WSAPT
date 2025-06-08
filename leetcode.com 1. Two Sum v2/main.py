from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        dt = {}

        for idx1 in range(len(nums)):
            local_target = target - nums[idx1]

            if local_target in dt:
                return [idx1, dt[local_target]]
            else:
                dt[nums[idx1]] = idx1

        return []
