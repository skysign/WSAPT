from typing import List


class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        if amount == 0:
            return 1

        coins.sort()
        coins = [0] + coins
        dp: List[List[int]] = [[0] * (amount + 1) for _ in range(len(coins))]

        for row in range(1, len(coins)):
            dp[row][0] = 1

        for row in range(1, len(coins)):
            coin = coins[row]
            for col in range(1, amount + 1):
                dp[row][col] += dp[row - 1][col]

                if col - coin >= 0:
                    dp[row][col] += dp[row][col - coin]

        return dp[len(coins) - 1][amount]
