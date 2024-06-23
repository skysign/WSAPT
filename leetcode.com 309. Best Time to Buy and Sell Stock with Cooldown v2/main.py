from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        l = len(prices)
        dp: List[int] = [0] * l

        for idx_sell in range(1, l):
            for idx_buy in range(0, idx_sell):
                profit = prices[idx_sell] - prices[idx_buy]

                if idx_buy - 2 > 0:
                    profit += dp[idx_buy - 2]

                dp[idx_sell] = max(dp[idx_sell], profit)

            dp[idx_sell] = max(dp[idx_sell], dp[idx_sell - 1])

        return dp[len(prices) - 1]
