from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        # dp[i] 날짜에 최대 이익
        dp = [0 for _ in range(len(prices))]

        # 주식을 우선 buy해야 sell 할 수 있으므로,
        # 가장 빠른 날짜인 0에 주식을 샀다고 가정하고,
        # 주식을 가짱 빨리 팔수 있는 1일 부터 시작함
        for idx_sell in range(1, len(prices)):
            # 주식을 파는 날 기준으로, 주식을 살 수 있는 날을 모두 방문함
            for idx_buy in range(0, idx_sell):
                profit_tmp = prices[idx_sell] -prices[idx_buy]

                if idx_buy -2 >= 0:
                    profit_tmp += dp[idx_buy -2]

                dp[idx_sell] = max(dp[idx_sell], profit_tmp)

            dp[idx_sell] = max(dp[idx_sell], dp[idx_sell - 1])

        return dp[len(prices) -1]
