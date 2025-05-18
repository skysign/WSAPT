import sys
from typing import List


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        nums = [0] + nums
        dp = [-sys.maxsize for _ in range(len(nums))]

        for idx in range(1, len(nums)):
            if dp[idx-1] + nums[idx] > nums[idx]:
                dp[idx] = dp[idx-1] + nums[idx]
            else:
                dp[idx] = nums[idx]

        return max(dp)
