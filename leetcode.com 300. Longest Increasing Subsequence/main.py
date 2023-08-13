from typing import List

class Solution:
    def lengthOfLIS(self, dt: List[int]) -> int:
        dp = [1 for _ in range(len(dt))]
        lis = 1

        for idx_fr in range(1, len(dt)):
            for idx in range(idx_fr -1, -1, -1):
                if dt[idx_fr] > dt[idx] and dp[idx_fr] < dp[idx] + 1:
                    dp[idx_fr] = dp[idx] + 1
                    lis = max(lis, dp[idx_fr])

        return lis