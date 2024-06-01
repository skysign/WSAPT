from typing import List


class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        dp = [10 ** 6 for _ in range(len(cost) + 1)]
        dp[0] = 0
        dp[1] = 0

        for i in range(len(dp) - 2):
            dp[i + 1] = min(dp[i + 1], cost[i] + dp[i])
            dp[i + 2] = min(dp[i + 2], cost[i] + dp[i])

        j = len(cost) - 1
        dp[j + 1] = min(dp[j + 1], cost[j] + dp[j])

        return dp[len(dp) - 1]
