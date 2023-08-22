from typing import List

class Solution:
    def rob(self, nums: List[int]) -> int:
        dp = [0 for _ in range(len(nums))]

        for idx in range(len(nums)):
            if idx == 0:
                dp[idx] = nums[idx]
            elif idx == 1:
                dp[idx] = max(nums[idx], nums[idx-1])
            else:
                dp[idx] = max(nums[idx] + dp[idx -2], dp[idx -1])

        return dp[len(nums) -1]