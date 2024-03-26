from typing import List


class Solution:
    def jump(self, nums: List[int]) -> int:
        dp = [10 ** 4 + 1 for _ in range(len(nums))]
        dp[0] = 0

        for idx in range(len(nums) - 1):
            jump = nums[idx]p

            for j in range(idx + 1, idx + jump + 1):
                if j < len(nums):
                    dp[j] = min(dp[j], dp[idx] + 1)

        return dp[len(nums) - 1]
