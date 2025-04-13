from typing import List


class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return 0

        nums = list(set(nums))
        nums.sort()

        dp = [0 for _ in range(len(nums))]

        for i in range(1, len(nums)):
            if nums[i - 1] + 1 == nums[i]:
                dp[i] = dp[i - 1] + 1

        return max(dp) + 1
