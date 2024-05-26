from typing import List


class Solution:
    def climbStairs(self, n: int) -> int:
        if n == 1:
            return 1

        dp: List[int] = [0 for _ in range(n + 1)]
        dp[0] = 1
        dp[1] = dp[0]

        for i in range(2, len(dp)):
            dp[i] = dp[i - 1] + dp[i - 2]

        return dp[n]
