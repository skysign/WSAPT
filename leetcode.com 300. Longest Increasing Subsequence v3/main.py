import sys
from typing import List


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        nums = [-sys.maxsize] + nums
        dp = [0 for _ in range(len(nums))]

        for i in range(1, len(nums)):
            for j in range(i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)

        return max(dp)
