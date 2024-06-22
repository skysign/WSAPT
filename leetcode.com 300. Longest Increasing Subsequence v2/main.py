from typing import List

class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        dp: List[int] = [0 for _ in range(len(nums))]
        dp[0] = 1

        for i in range(1, len(nums)):
            mx = 0
            for j in range(i):
                if nums[j] < nums[i]:
                    mx = max(mx, dp[j])

            dp[i] = mx + 1

        return max(dp)