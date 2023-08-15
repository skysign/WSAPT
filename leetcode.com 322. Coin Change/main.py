from typing import List

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        MAX_INT = 2 ** 31
        dp = [MAX_INT for _ in range(amount +1)]
        dp[0] = 0
        coins.sort()

        if amount == 0:
            return amount

        # dp[i] 는 i라는 amount보다 작거나 같은 값을 만들 수 있는 동전의 개수
        for idx_dp in range(1, amount +1):
            for coin in coins:
                idx_prev = idx_dp - coin
                if idx_prev >= 0:
                    dp[idx_dp] = min(dp[idx_dp], dp[idx_prev] +1)

        if dp[amount] == MAX_INT:
            return -1

        return dp[amount]