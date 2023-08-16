from typing import List

class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        coins.sort()
        coins = [-1] + coins

        dp = [[0 for _ in range(amount +1)] for _ in range(len(coins))]
        for idx in range(1, len(coins)):
            dp[idx][0] = 1

        for idx_coin in range(1, len(coins)):
            for idx_money in range(1, amount +1):
                coin = coins[idx_coin]
                if idx_money - coin >= 0:
                    dp[idx_coin][idx_money] = dp[idx_coin][idx_money -coin] +dp[idx_coin -1][idx_money]
                else:
                    dp[idx_coin][idx_money] = dp[idx_coin - 1][idx_money]

        return dp[len(coins) -1][amount]