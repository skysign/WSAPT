import sys
from typing import List


class Solution:
    def jump(self, nums: List[int]) -> int:
        dp = [sys.maxsize for _ in range(len(nums))]
        dp[0] = 0

        for i in range(len(nums) - 1):
            for jump in range(1, nums[i] + 1):
                if i + jump < len(nums):
                    dp[i + jump] = min(dp[i + jump], dp[i] + 1)

        return dp[-1]
