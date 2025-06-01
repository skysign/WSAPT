from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:
        nums = [0, 0, 0] + nums
        dp = [0 for _ in range(len(nums))]

        for idx in range(3, len(nums)):
            dp[idx] = max(dp[idx - 3], dp[idx - 2]) + nums[idx]

        return max(dp)
