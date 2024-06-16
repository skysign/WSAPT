from typing import List
import sys


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp: List[List[int]] = [[sys.maxsize for _ in range(amount + 1)] for _ in range(len(coins))]

        for i in range(len(coins)):
            dp[i][0] = 0

        for idx_coin in range(len(coins)):
            for idx in range(1, amount + 1):
                if idx_coin > 0:
                    dp[idx_coin][idx] = min(dp[idx_coin][idx], dp[idx_coin - 1][idx])

                idx_prev = idx - coins[idx_coin]
                if idx_prev >= 0:
                    dp[idx_coin][idx] = min(dp[idx_coin][idx], dp[idx_coin][idx_prev] + 1)

        if dp[len(coins) - 1][amount] == sys.maxsize:
            return -1

        return dp[len(coins) - 1][amount]
