from typing import List

class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        dp = [-1 * (10 ** 9) -1] * len(nums)
        dp[0] = nums[0]

        for i in range(1, len(nums)):
            dp[i] = max(nums[i], dp[i-1] + nums[i])

        return max(dp)