from typing import List


class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        mx = sum(nums)
        if mx < target:
            return 0

        dp: List[int] = [0] * (mx * 2 + 1)
        dp[mx + nums[0]] += 1
        dp[mx - nums[0]] += 1

        for n in nums[1:]:
            dp_local: List[int] = [0] * (mx * 2 + 1)
            for i in range(mx * 2 + 1):
                if dp[i] != 0:
                    dp_local[i + n] = dp_local[i + n] + dp[i]
                    dp_local[i - n] = dp_local[i - n] + dp[i]

            dp = dp_local

        return dp[mx + target]