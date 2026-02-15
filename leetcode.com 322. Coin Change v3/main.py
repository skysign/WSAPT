import sys
from typing import List


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount == 0:
            return 0

        coins = [0] + coins
        dp = [[sys.maxsize for _ in range(amount + 1)] for _ in range(len(coins))]
        for idx in range(len(coins)):
            dp[idx][0] = 0

        for idx_coin in range(1, len(coins)):
            coin = coins[idx_coin]
            for local_amount in range(1, amount + 1):
                dp[idx_coin][local_amount] = min(dp[idx_coin][local_amount], dp[idx_coin - 1][local_amount])

                if local_amount - coin >= 0:
                    if dp[idx_coin][local_amount - coin] != sys.maxsize:
                        dp[idx_coin][local_amount] = min(dp[idx_coin][local_amount], dp[idx_coin][local_amount - coin] + 1)

        return dp[len(coins) - 1][amount] if dp[len(coins) - 1][amount] != sys.maxsize else -1
