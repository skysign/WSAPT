from typing import List


class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        sm = sum(nums)
        if sm % 2 == 1:
            return False

        l = sm // 2
        dp: List[bool] = [False] * (l + 1)
        dp[0] = True

        for num in nums:
            for i in range(l - 1, -1, -1):
                if dp[i] and i + num <= l:
                    dp[i + num] = True

        return dp[l]
